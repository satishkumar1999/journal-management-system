package com.satish.journal.service;

import com.satish.journal.entity.User;
import com.satish.journal.repository.UserRepoistry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepoistry userRepoistry;

    private static final PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();// when you commented it then ave pd in pail text

    public  boolean saveNewUser(User userr){

        try{

            userr.setPassword(passwordEncoder.encode(userr.getPassword()));
            userr.setRoles(Arrays.asList("USER"));
            userRepoistry.save(userr);

            return true;

        }catch (Exception e){
            return  false;
        }

    }




    public void saveAdminUser(User userr) {
        userr.setPassword(passwordEncoder.encode(userr.getPassword()));
        userr.setRoles(Arrays.asList("USER","ADMIN"));
        userRepoistry.save(userr);



    }

    public void saveUser(User user){

        userRepoistry.save(user);

    }

    public List<User> getAll(){
        return userRepoistry.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userRepoistry.findById(id);

    }


    public User findByUserName(String username){
        return userRepoistry.findByUsername(username);

    }



}



