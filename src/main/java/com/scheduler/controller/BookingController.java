package com.scheduler.controller;

import com.scheduler.domain.Booking;
import com.scheduler.domain.User;
import com.scheduler.service.BookingService;
import com.scheduler.service.UserService;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.apache.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.ObjectError;

@Controller
public class BookingController {
        @Autowired
        private BookingService bookingService;
        @Autowired
        private UserService userService;
        
        private static final Logger log = Logger.getLogger(BookingController.class);
        public static final String DATE_FORMAT = "dd.MM.yyyy HH:mm";
        
        @InitBinder
        public void initBinder(WebDataBinder binder) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
            binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
        }        
    
	@RequestMapping("booking/{specId}/new")
	public String addBooking(@PathVariable Long specId, Model model) {
            model.addAttribute("booking", new Booking());
            
            TreeMap<String, String> allowTimesPerDay = bookingService.getAllowTimesPerDay(userService.getUserById(specId));
            
            if(allowTimesPerDay.firstEntry() != null)
                model.addAttribute("curDayAllowTimes", allowTimesPerDay.firstEntry().getValue());
            model.addAttribute("allowTimes", allowTimesPerDay);
            return "booking/new";
	}
        
        @RequestMapping(value = "booking/{specId}/new", method = RequestMethod.POST)
	public String addBookingPost(@PathVariable Long specId,
                                    @Valid Booking booking, 
                                    BindingResult bindingResult, 
                                    Model model, 
                                    HttpSession session) {
            if(bindingResult.hasErrors()) {
                return "booking/error";
            }
            
            booking.setClient((User)session.getAttribute("loggedUser"));
            booking.setSpec(userService.getUserById(specId));
            
            try {
                bookingService.addBooking(booking);
            }
            catch(DataIntegrityViolationException ex)
            {
                bindingResult.addError(new ObjectError("booking", "Выбранное время уже занято другим пользователем."));
                return "booking/new";
            }
            
            model.addAttribute("booking", booking);

            return "booking/success";
	}        
        
        @RequestMapping("booking/bookedByMe")
	public String bookedByMe(Model model, HttpSession session) {
            User loggedUser = (User) session.getAttribute("loggedUser");
            userService.initializeLazyCollection(loggedUser);
            model.addAttribute("bookings", loggedUser.getBookedByMe());
            model.addAttribute("pageHeader", "Мои записи на приём");
            model.addAttribute("specInfo", true);
            return "booking/list";
	}
        
        @RequestMapping("booking/bookedToMe")
	public String bookedToMe(Model model, HttpSession session) {
            User loggedUser = (User) session.getAttribute("loggedUser");
            userService.initializeLazyCollection(loggedUser);
            model.addAttribute("bookings", loggedUser.getBookings());
            model.addAttribute("pageHeader", "Записи ко мне на приём");
            model.addAttribute("specInfo", false);
            return "booking/list";
	}        
}
