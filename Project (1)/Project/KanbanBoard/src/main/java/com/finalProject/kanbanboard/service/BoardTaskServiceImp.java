package com.finalProject.kanbanboard.service;

import com.finalProject.kanbanboard.exception.BoardTaskNotFoundException;
import com.finalProject.kanbanboard.model.BoardTask;
import com.finalProject.kanbanboard.repository.BoardTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BoardTaskServiceImp implements BoardTaskService {
    @Autowired
    private BoardTaskRepository boardTaskRepository;
    @Override
    public BoardTask addTask(BoardTask boardTask) {
        return boardTaskRepository.save(boardTask);
    }

    @Override
    public Boolean deleteTask(int taskId ,BoardTask boardTask) throws BoardTaskNotFoundException {
        if(boardTaskRepository.findById(taskId).isPresent()){
            boardTaskRepository.deleteById(taskId);
            return true;
        }else{
            throw new BoardTaskNotFoundException();
        }
    }

    @Override
    public BoardTask updateTask(BoardTask boardTask) {
        if(boardTaskRepository.findById(boardTask.getTaskId()).isPresent()){
            return boardTaskRepository.save(boardTask);
        }
        return null;
    }

    @Override
    public List<BoardTask> getAllTask() {
        return boardTaskRepository.findAll();
    }
}
