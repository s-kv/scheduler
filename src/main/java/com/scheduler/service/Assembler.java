package com.scheduler.service;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("assembler")
public class Assembler {

	@Transactional(readOnly = true)
	User buildUserFromUserEntity(com.scheduler.domain.User userEntity) {
		String username = userEntity.getEmail();
		String password = userEntity.getPassword();
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		authorities.add(new GrantedAuthorityImpl(userEntity.getGrantedAuthority()));
		return new User(username, password, true, true, true, true, authorities);
	}
}
