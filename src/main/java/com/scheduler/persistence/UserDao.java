package com.scheduler.persistence;

import com.scheduler.domain.User;
import java.util.List;

public interface UserDao extends AbstractDao<User>{
    public User findUserByEmail(String email);
    public List<User> findUsersByName(String name);
    public boolean isEmailAvailable(String username);
}
