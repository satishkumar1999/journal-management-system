package com.satish.journal.tests;

import com.satish.journal.entity.User;
import com.satish.journal.repository.UserRepoistry;
import com.satish.journal.service.UserDetailsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

public class UserDetailServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepoistry userRepoistry;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserByUserNameTest(){
        when(userRepoistry.findByUsername(ArgumentMatchers.anyString())).thenReturn(User.builder().username("vidya").password("vidu").roles(new ArrayList<>()).build());
        UserDetails user= userDetailsService.loadUserByUsername("vidya");
        Assertions.assertNotNull(user);
    }

}
