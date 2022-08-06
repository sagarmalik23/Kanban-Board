package com.finalProject.kanbanboard.service;

import com.finalProject.kanbanboard.exception.UserAlreadyExistException;
import com.finalProject.kanbanboard.exception.UserNotFoundException;
import com.finalProject.kanbanboard.model.BoardTask;
import com.finalProject.kanbanboard.model.User;
import com.finalProject.kanbanboard.rabbitmq.Input;

import java.util.List;

public interface UserService {
    public abstract User addUser(Input user) throws UserAlreadyExistException;
    public abstract User getImage(String emailId);
    public abstract User addTaskForUser(String emailId, BoardTask boardTask) throws UserNotFoundException;
    public abstract List<BoardTask> deleteTaskForUser(String emailId, int taskId) throws UserNotFoundException;
    public abstract User increaseTaskStatusForUser(String emailId, BoardTask boardTask) throws UserNotFoundException;
    public abstract User decreaseTaskStatusForUser(String emailId, BoardTask boardTask) throws UserNotFoundException;
    public abstract List<BoardTask> getAllTasksForParticularUser(String emailId) throws UserNotFoundException;
    public abstract User updateBoardTaskForUser(String emailId,BoardTask boardTask) throws UserNotFoundException;
    public abstract BoardTask changeTaskPriorityForUser(String emailId, BoardTask boardTask, String priority) throws UserNotFoundException;

    public abstract User changeTaskStatusForUser(String emailId, BoardTask boardTask,int taskStatus) throws UserNotFoundException;
}