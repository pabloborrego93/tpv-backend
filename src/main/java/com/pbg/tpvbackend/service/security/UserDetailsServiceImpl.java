package com.pbg.tpvbackend.service.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pbg.tpvbackend.dao.security.UserDao;
import com.pbg.tpvbackend.model.security.CustomUserDetails;
import com.pbg.tpvbackend.model.security.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userDao.findByUsername(username);
		
        if (!user.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        
        return new CustomUserDetails(user.get());
	}

}
