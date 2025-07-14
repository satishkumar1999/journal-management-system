package com.satish.journal.controller;


import com.satish.journal.entity.User;
import com.satish.journal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicControler {


    @Autowired
    private UserService userService;

    @GetMapping("/health")
    public String HealthCheak(){
        return "ok";

    }

    @PostMapping("/creat")
    public void  createMeth(@RequestBody User user){
        userService.saveUser(user);


    }




}
