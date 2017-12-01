package com.pbg.tpvBackEnd.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pbg.tpvBackEnd.dao.security.UserDao;
import com.pbg.tpvBackEnd.jwt.JwtUserFactory;
import com.pbg.tpvBackEnd.model.security.User;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No se ha encontrado el usuario '%s'.", username));
        } else {
            return JwtUserFactory.create(user);
        }
    }
}
