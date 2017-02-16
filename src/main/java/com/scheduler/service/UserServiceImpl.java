package com.scheduler.service;

import com.scheduler.domain.Booking;
import com.scheduler.domain.User;
import com.scheduler.persistence.UserDao;
import com.scheduler.util.EMailSender;
import com.scheduler.util.SecurityRole;
import java.util.Collection;
import java.util.List;
import org.hibernate.Hibernate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserServiceImpl implements BeanFactoryAware, UserService{
	@Autowired
        private UserDao userDao;
	private EMailSender emailSender;

	@Override
	public void setBeanFactory(BeanFactory context) throws BeansException {
		emailSender = (EMailSender) context.getBean("emailSender");
	}

        @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
        @Override
	public void addUser(User user) {
		//Encode password, md5.
		PasswordEncoder encoder = new Md5PasswordEncoder();
		user.setPassword(encoder.encodePassword(user.getPassword(), null));
		
		//Set avatar as default.
		user.setAvatar("default.jpg");
		user.setGrantedAuthority(SecurityRole.ROLE_USER);

		userDao.create(user);
	}
        
        @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
        @Override
	public User updateUser(User editUser, Boolean addNewAvatar, Boolean revomeAvatar) {
		User user = userDao.get(editUser.getId());
            
                PasswordEncoder encoder = new Md5PasswordEncoder();
		user.setPassword(encoder.encodePassword(editUser.getPassword(), null));
                
                user.copyEditFields(editUser);
                
		if(addNewAvatar) {
                    user.setAvatar(user.getId() + ".jpg");
		} else if(revomeAvatar) {
                    user.setAvatar("default.jpg");
		}

		userDao.update(user);
                
                return user;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
        @Override
        public void initializeLazyCollection(User user) {
                User newUser = userDao.get(user.getId());
                Hibernate.initialize(newUser.getBookedByMe());
                Hibernate.initialize(newUser.getBookings());
                user.setBookedByMe(newUser.getBookedByMe());
                user.setBookings(newUser.getBookings());
	}        

	@Override
        public boolean isEmailAvailable(String email) {
		return userDao.isEmailAvailable(email);
	}
        
	@Override
        public List<User> findUsersByName(String username) {
		return userDao.findUsersByName(username);
	}

        @Override
	public User getUserByEmail(String email) {
		return userDao.findUserByEmail(email);
	}        

        @Override
	public User getUserById(Long id) {
		return userDao.get(id);
	}        
}
