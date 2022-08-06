package com.finalProject.kanbanboard.repository;

import com.finalProject.kanbanboard.model.BoardTask;
import com.finalProject.kanbanboard.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {

}
