package com.satish.journal.service;

import com.satish.journal.entity.User;
import com.satish.journal.entity.UserPrincipal;
import com.satish.journal.repository.UserRepoistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepoistry userRepoistry;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepoistry.findByUsername(username);
        if (user == null) {
            System.out.println("User Not Found...");
            throw new UsernameNotFoundException("user not found!");
        }

        return new UserPrincipal(user);
    }
}
