package com.scheduler.persistence;

import com.scheduler.domain.User;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class HbnUserDao extends AbstractHbnDao<User> implements UserDao{
        @Override
	public User findUserByEmail(String email)
	{
		Query query = getSession().createQuery("from users where email = :email");
		query.setString("email", email);
		@SuppressWarnings("unchecked")
		List<User> users = query.list();
		if(!users.isEmpty()) {
			return users.get(0);
		} 
		return null;
	}
        
        @Override
	public List<User> findUsersByName(String name)
	{
		Query query = getSession().createQuery("from users where fullName like :name");
		query.setString("name", "%" + name + "%");
		@SuppressWarnings("unchecked")
		List<User> users = query.list();
                
                return users;
	} 
        
        @Override
	public boolean isEmailAvailable(String email) {
		
		Query query = getSession().createQuery("from users where email = :email");
		query.setString("email", email);
		@SuppressWarnings("unchecked")
		List<User> users = query.list();
		return users.isEmpty();
	}        
}
