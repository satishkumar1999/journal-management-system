package com.satish.journal.repository;

import com.satish.journal.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepoistry extends MongoRepository<User, ObjectId> {

    User findByUsername(String username);

    void deleteByusername(String username);
}
