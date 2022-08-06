package com.finalProject.kanbanboard.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalProject.kanbanboard.exception.UserAlreadyExistException;
import com.finalProject.kanbanboard.exception.UserNotFoundException;
import com.finalProject.kanbanboard.model.BoardTask;
import com.finalProject.kanbanboard.model.User;
import com.finalProject.kanbanboard.rabbitmq.Input;
import com.finalProject.kanbanboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("/userBoard")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/adduser")
        public ResponseEntity<?> saveUser(@RequestParam("image") MultipartFile image, @RequestParam("input") String userInput) throws UserAlreadyExistException, IOException {
            ObjectMapper mapper=new ObjectMapper();
            System.out.println(userInput.toString());
            Input input=mapper.readValue(userInput,Input.class);
            input.setBoardTasks(new ArrayList<>());
            input.setImage(image.getBytes());
            input.setRole("ROLE_USER");
            return new ResponseEntity<>(userService.addUser(input),HttpStatus.OK);
    }

    @GetMapping("/getprofile/{emailId}")
    public ResponseEntity<?> getImage(@PathVariable String emailId){
        return new ResponseEntity<>(userService.getImage(emailId),HttpStatus.OK);
    }

    @PostMapping("/task/addTask/{emailId}")
    public ResponseEntity<?> saveTaskForUser(@RequestBody BoardTask boardTask, @PathVariable String emailId) throws UserNotFoundException {
        return new ResponseEntity<>(userService.addTaskForUser(emailId,boardTask),HttpStatus.OK);
    }

    @DeleteMapping("/task/deleteTask/{emailId}/{taskId}")
    public ResponseEntity<?> deleteTaskForUser(@PathVariable String emailId ,@PathVariable int taskId) throws UserNotFoundException {
        return new ResponseEntity<>(userService.deleteTaskForUser(emailId,taskId),HttpStatus.OK);
    }

    @PutMapping("/task/increaseStatus/{emailId}")
    public ResponseEntity<?> increaseTaskStatusForAUser(@RequestBody BoardTask boardTask,@PathVariable String emailId) throws UserNotFoundException {
        return new ResponseEntity<>(userService.increaseTaskStatusForUser(emailId, boardTask),HttpStatus.OK);
    }


//http://localhost:64000/userBoard/decreaseStatus/
    @PutMapping("/task/decreaseStatus/{emailId}")
    public ResponseEntity<?> decreaseTaskStatusForAUser(@RequestBody BoardTask boardTask,@PathVariable String emailId) throws UserNotFoundException {
        return new ResponseEntity<>(userService.decreaseTaskStatusForUser(emailId, boardTask),HttpStatus.OK);
    }

    @GetMapping("/task/getAllTaskForParticularUser/{emailId}")
    public ResponseEntity<?> getAllTaskForAParticularUser(@PathVariable String emailId) throws UserNotFoundException {
        return new ResponseEntity<>(userService.getAllTasksForParticularUser(emailId),HttpStatus.OK);
    }

    @PutMapping("/task/updateUser/{emailId}/{taskId}")
    public  ResponseEntity<?> updateUser(@PathVariable String emailId, @RequestBody BoardTask boardTask) throws UserNotFoundException {
        return  new ResponseEntity<>(userService.updateBoardTaskForUser(emailId, boardTask),HttpStatus.OK);
    }

    @PutMapping("/task/priority/{emailId}/{priority}")
    public ResponseEntity<?> changeTaskPriority(@RequestBody BoardTask boardTask,@PathVariable String emailId,String priority) throws UserNotFoundException {
        return new ResponseEntity<>(userService.changeTaskPriorityForUser(emailId, boardTask, priority),HttpStatus.OK);
    }

    @PutMapping("/task/changeStatus/{emailId}/{status}")
    public ResponseEntity<?> changeTaskStatusForAUser(@RequestBody BoardTask boardTask,@PathVariable String emailId,@PathVariable int status) throws UserNotFoundException {
        return new ResponseEntity<>(userService.changeTaskStatusForUser(emailId, boardTask,status),HttpStatus.OK);
    }
}
