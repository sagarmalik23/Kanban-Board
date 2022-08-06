package com.finalProject.kanbanboard.repository;

import com.finalProject.kanbanboard.model.BoardTask;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BoardTaskRepository extends MongoRepository<BoardTask,Integer> {
}
