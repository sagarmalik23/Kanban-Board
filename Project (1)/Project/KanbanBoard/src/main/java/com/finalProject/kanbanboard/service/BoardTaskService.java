package com.finalProject.kanbanboard.service;

import com.finalProject.kanbanboard.exception.BoardTaskNotFoundException;
import com.finalProject.kanbanboard.model.BoardTask;

import java.util.List;

public interface BoardTaskService {
    public abstract BoardTask addTask(BoardTask boardTask);
    public abstract Boolean deleteTask(int taskId,BoardTask boardTask) throws BoardTaskNotFoundException;
    public abstract BoardTask updateTask(BoardTask boardTask);
    public abstract List<BoardTask> getAllTask();
}
