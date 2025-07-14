package com.satish.journal.controller;


import com.satish.journal.entity.Journal;
import com.satish.journal.entity.User;
import com.satish.journal.service.JournalService;
import com.satish.journal.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalController {


    @Autowired
    private JournalService journalService;
    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<?> getAllJouranalEnteriseOfUser(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        User user=userService.findByUserName(username);
        List<Journal> all=user.getJournalEntries();
        if(all !=null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>("nothing",HttpStatus.NOT_FOUND);
    }




    @PostMapping
    public ResponseEntity<Journal> createEntry(@RequestBody Journal myEntry){
        try{
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            String username=authentication.getName();
            myEntry.setDate(LocalDateTime.now());
            journalService.saveEntry(myEntry,username);
            return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }


    }





    @GetMapping("id/{myId}")
    public ResponseEntity<Journal> getJournalEntryById(@PathVariable ObjectId myId){

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        User user=userService.findByUserName(username);
        List<Journal> collect=  user.getJournalEntries().stream().filter(x->x.getId().equals(myId)).collect(Collectors.toList());

        if(!collect.isEmpty()) {
            Optional<Journal> journalEntry = journalService.findById(myId);
            if (journalEntry.isPresent()) {
                return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);

            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }






    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteJournalEntryBtyId(@PathVariable ObjectId myId){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        journalService.deleteById(myId,username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }











    @PutMapping("id/{myid}")
    public ResponseEntity<?> updateJournalEntryById(@PathVariable ObjectId myid,@RequestBody Journal newEntry){

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        newEntry.setDate(LocalDateTime.now());

        User user=userService.findByUserName(username);
        List<Journal> collect=  user.getJournalEntries().stream().filter(x->x.getId().equals(myid)).collect(Collectors.toList());

        if(!collect.isEmpty()) {
            Optional<Journal> journalEntry = journalService.findById(myid);
            if (journalEntry.isPresent()) {

                Journal old=journalEntry.get();

                old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("")? newEntry.getTitle() : old.getTitle());
                old.setContent(newEntry.getContent() !=null && !newEntry.getContent().equals("")? newEntry.getContent(): old.getContent());

                journalService.saveEntry(old,username);

                // ✅ Step 2: Update user's journalEntries list
                user.getJournalEntries().removeIf(x -> x.getId().equals(myid));
                user.getJournalEntries().add(old);
                userService.saveUser(user); // save updated user


                return new ResponseEntity<>(old,HttpStatus.OK);



            }


        }


//        journalService.saveEntry(old);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }




}
