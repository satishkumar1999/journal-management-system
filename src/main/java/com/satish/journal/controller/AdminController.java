package com.satish.journal.controller;


import com.satish.journal.entity.User;
import com.satish.journal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    private UserService userService;

    @GetMapping("/all-user")
    public ResponseEntity<?> getAllUsers(){

        List<User> all=userService.getAll();

        if (all !=null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);

    }

    @PostMapping("/creat-admin-user")
    public void createUser(@RequestBody User user){
        userService.saveAdminUser(user);

    }


}
