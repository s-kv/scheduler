package com.scheduler.service;

import com.scheduler.domain.User;
import com.scheduler.persistence.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private Assembler assembler;
	
	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {
		
		User userEntity = userDao.findUserByEmail(email);
		if(userEntity == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return assembler.buildUserFromUserEntity(userEntity);
	}
}
