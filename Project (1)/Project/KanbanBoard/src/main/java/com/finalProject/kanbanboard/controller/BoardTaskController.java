package com.finalProject.kanbanboard.controller;

import com.finalProject.kanbanboard.exception.BoardTaskNotFoundException;
import com.finalProject.kanbanboard.model.BoardTask;
import com.finalProject.kanbanboard.service.BoardTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boardTask")
public class BoardTaskController {
    @Autowired
    private BoardTaskService boardTaskService;

//http://localhost:64000/boardTask/addTask
    @PostMapping("/addTask")
    public ResponseEntity<?> saveTask(@RequestBody BoardTask boardTask){
        return new ResponseEntity<>(boardTaskService.addTask(boardTask), HttpStatus.CREATED);
    }

//    http://localhost:64000/boardTask/deleteTask/
    @DeleteMapping("/deleteTask/{taskId}")
    public ResponseEntity<?> deleteBoardTask(@PathVariable int taskId,@RequestBody BoardTask boardTask) throws BoardTaskNotFoundException {
        return new ResponseEntity<>(boardTaskService.deleteTask(taskId,boardTask),HttpStatus.OK);
    }


//http://localhost:64000/boardTask/getAllTask
    @GetMapping("/getAllTask")
    public ResponseEntity<?> getTask(){
        return new ResponseEntity<>(boardTaskService.getAllTask(),HttpStatus.OK);
    }




    //    http://localhost:64000/boardTask/update/
    @PutMapping("/update/{taskId}")
    public ResponseEntity<?> updateBoardTask(@RequestBody BoardTask boardTask){
        return new ResponseEntity<>(boardTaskService.updateTask(boardTask),HttpStatus.OK);
    }
}
