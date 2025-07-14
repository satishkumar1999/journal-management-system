package com.satish.journal.service;

import com.satish.journal.entity.Journal;
import com.satish.journal.entity.User;
import com.satish.journal.repository.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalService {


    @Autowired
    private JournalRepository journalRepository;
    @Autowired
    UserService userService;

    @Transactional
    public  void saveEntry(Journal journal, String username){
        try{

            User user=userService.findByUserName(username);
            journal.setDate(LocalDateTime.now());

            Journal saved= journalRepository.save(journal);
            user.getJournalEntries().add(saved);// matlab journal ka id user ke journalEntrise list me save hoga due to @dref
            userService.saveNewUser(user); //ye kya karega userservice saveentry funtion ko call karega
        }catch (Exception e){
            System.out.println("getting error"+e);
        }

    }

    public List<Journal> getAll(){
        return journalRepository.findAll();
    }

    public Optional<Journal> findById(ObjectId id){
        return journalRepository.findById(id);

    }


    public void deleteById(ObjectId myid, String username){
        User user=userService.findByUserName(username);
        boolean remove=user.getJournalEntries().removeIf(x->x.getId().equals(myid)); //  RAM se journallist  delete hua
        try {
            if (remove) {

                userService.saveUser(user); // User ko DB me update kiya with empty journalEntries
                journalRepository.deleteById(myid); // DB JOurnal_entrise se journalEntries ko delete kiya
            }
        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("an error occured while deleteing the entry",e);

        }
    }

}
