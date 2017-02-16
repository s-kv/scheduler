package com.scheduler.controller;

import static com.scheduler.controller.BookingController.DATE_FORMAT;
import com.scheduler.domain.User;
import com.scheduler.service.UserService;
import com.scheduler.util.ImageUploadException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RequestMapping("users")
public class UserController {

	@Autowired
	private UserService userService;
        
        /*public static final String TIME_FORMAT = "HH:mm";
        
        @InitBinder
        public void initBinder(WebDataBinder binder) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_FORMAT);
            binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
        }*/         
        
	@RequestMapping("users/login")
	public String showLoginPage() {
            return "login";
	}        
        
	@RequestMapping("users/registration")
	public String showRegistrationForm(Model model) {
            model.addAttribute("user", new User());

            return "registerNewUser";
	}
	
	@RequestMapping(value = "users/registration", method = RequestMethod.POST)
	public String registerUser(@Valid User user, BindingResult bindingResult, Model model) {
            if(bindingResult.hasErrors()) {
                    return "registerNewUser";
            }

            if(!userService.isEmailAvailable(user.getEmail())) {
                    bindingResult.addError(new FieldError(bindingResult.getObjectName(), "email", "Указанная почта уже используется."));
                    return "registerNewUser";
            }
            userService.addUser(user);
            model.addAttribute("username", user.getFullName());

            return "registrationCompleted";
	}
        
	@RequestMapping("logged/home")
	public String showHome(Model model, HttpSession session) {
            String loggedEmail = SecurityContextHolder.getContext().getAuthentication().getName();
            User loggedUser = userService.getUserByEmail(loggedEmail);
            session.setAttribute("loggedUser", loggedUser);

            return "logged/home";
	}
	        
	@RequestMapping(value = "users/search", method = RequestMethod.GET)
	public String showSearchResults(Model model, @RequestParam String query) {
            List<User> users = userService.findUsersByName(query);
            model.addAttribute("users", users);

            return "users/search";
	}
        
	@RequestMapping(value = "users/{id}", method = RequestMethod.GET)
	public String showUserProfile(@PathVariable Long id, Model model, HttpSession session) {
            User user = userService.getUserById(id);
            if(user != null) {
                model.addAttribute("user", user);
                /*Spitter spitterMe = (Spitter) session.getAttribute("loggedSpitter");

                model.addAttribute("followAlready", 
                                spitterServiceImpl.isSpitterFollowedBy(spitterMe, username)?true:false);*/

                return "users/view";
            } else {
                return "users/noProfile";
            }
	}        

	@RequestMapping(value = "logged/edit", method = RequestMethod.GET) 
	public String showEdit(Model model, HttpSession session) {
            User loggedUser = (User) session.getAttribute("loggedUser");
            User editUser = new User().copyEditFields(loggedUser);

            model.addAttribute("editUser", editUser);

            return "logged/edit";
	}

	@RequestMapping(value = "logged/edit", method = RequestMethod.POST)
	public String editUser(@ModelAttribute("editUser") @Valid User editUser, BindingResult bindingResult,
			@RequestParam(required=false) MultipartFile image,
                        @RequestParam(required=false) Boolean removeAvatar,
                        @RequestParam(required=false) String password,
                        Model model,
                        HttpSession session) {
		
            User loggedUser = (User) session.getAttribute("loggedUser");
            
            if(bindingResult.hasErrors()) {
                return "logged/edit";
            } else {
                PasswordEncoder encoder = new Md5PasswordEncoder();
                if(!encoder.encodePassword(password, null).equals(loggedUser.getPassword()))
                {
                    bindingResult.addError(new FieldError(bindingResult.getObjectName(), "password", "Неверный пароль. Изменение профиля отклонено."));
                    return "logged/edit";                    
                }
                else
                    editUser.setPassword(password);
            }
            
            String realPath = session.getServletContext().getRealPath("/resources");

            try {
                if(!image.isEmpty()) {
                    validateImage(image);

                    saveImage(loggedUser.getId() + ".jpg", image, realPath);
                }
            } catch(ImageUploadException e) {
                bindingResult.addError(new FieldError(bindingResult.getObjectName(), "image", e.getMessage()));
                return "logged/edit";
            }
            
            editUser.setId(loggedUser.getId());
            loggedUser = userService.updateUser(editUser, !image.isEmpty(), removeAvatar!=null?removeAvatar:false);

            return "redirect:/logged/home";
	}
        
        @ExceptionHandler(MaxUploadSizeExceededException.class)
        public ModelAndView resolveException(HttpServletRequest request,
                HttpServletResponse response, 
                HttpSession session,
                Exception exception)
        {        
            Map<String, Object> model = new HashMap<>();
            if (exception instanceof MaxUploadSizeExceededException)
            {
                model.put("errors", exception.getMessage());
                User loggedUser = (User) session.getAttribute("loggedUser");
                User editUser = new User().copyEditFields(loggedUser);

                model.put("editUser", editUser);
                model.put("imageException", "Превышен максимально допустимый размер файла");

                return new ModelAndView("logged/edit", model);
            }

            return null;
        }

	private void saveImage(String filename, MultipartFile image, String realPath) throws ImageUploadException {
            try {
                File file = new File(realPath + "/avatars/" + filename);
                FileUtils.writeByteArrayToFile(file, image.getBytes());
            } catch(IOException e) {
                throw new ImageUploadException("Ошибка при загрузке изображения", e);
            }
	}

	private void validateImage(MultipartFile image) throws ImageUploadException {
            if(!image.getContentType().equals("image/jpeg")) {
                throw new ImageUploadException("Изображение может быть только формата JPG.");
            }
	}

        
}
