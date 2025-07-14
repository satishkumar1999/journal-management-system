package com.satish.journal.tests;

import com.satish.journal.entity.User;
import com.satish.journal.repository.UserRepoistry;
import com.satish.journal.service.UserService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepoistry userRepoistry;

    @Autowired
    private UserService userService;

    //   @Test
    @ParameterizedTest
    @ArgumentsSource(UserArgumentProvider.class)
    public  void testSaveNewUser(User user){
        assertNotNull(userService.saveNewUser(user));

    }

}
