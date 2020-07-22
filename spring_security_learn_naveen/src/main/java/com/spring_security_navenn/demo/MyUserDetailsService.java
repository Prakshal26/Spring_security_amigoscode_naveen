package com.spring_security_navenn.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {


    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        /*
        It will find the user based on the name from the JPA repository.
         */
        User user = repo.findByUsername(username);

        if(user == null)
            throw  new UsernameNotFoundException("User 404");

        /*
        If user is found then it will be send to UserPrinciple as it will register that user in
        spring security and allow him to login. Open UserPrinciple class and read.
         */
        return new UserPrinciple(user);

    }
}
