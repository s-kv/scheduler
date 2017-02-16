package com.scheduler.service;

import com.scheduler.domain.Booking;
import com.scheduler.domain.User;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;

public interface UserService {

    public void addUser(User user);

    public User updateUser(User editUser, Boolean addNewAvatar, Boolean revomeAvatar);
    
    public void initializeLazyCollection(User user);
    
    public boolean isEmailAvailable(String email);
    
    public List<User> findUsersByName(String username);
    
    public User getUserByEmail(String email);

    public User getUserById(Long id);
}
