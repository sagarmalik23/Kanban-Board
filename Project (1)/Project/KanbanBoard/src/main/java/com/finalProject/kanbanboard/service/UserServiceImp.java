package com.finalProject.kanbanboard.service;

import com.finalProject.kanbanboard.exception.UserAlreadyExistException;
import com.finalProject.kanbanboard.exception.UserNotFoundException;
import com.finalProject.kanbanboard.model.BoardTask;
import com.finalProject.kanbanboard.model.User;
import com.finalProject.kanbanboard.proxy.Proxy;
import com.finalProject.kanbanboard.rabbitmq.Input;
import com.finalProject.kanbanboard.rabbitmq.Producer;
import com.finalProject.kanbanboard.rabbitmq.UserDTO;
import com.finalProject.kanbanboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Producer producer;

    @Autowired
    private Proxy proxy;

    @Override
    public User getImage(String emailId) {
        if (userRepository.findById(emailId).isPresent()){
            return  userRepository.findById(emailId).get();
        }
        return null;

    }

    @Override
    public User addUser(Input input) throws UserAlreadyExistException {
        User user = new User(input.getEmailId(), input.getName(), input.getImage(), input.getBoardTasks());

        if (userRepository.findById(user.getEmailId()).isEmpty()) {
            User result = userRepository.insert(user);  //sends user to mongodb

            UserDTO userDTO = new UserDTO(input.getEmailId(), input.getPassword(), input.getRole());
//            producer.sendMessageToMq(userDTO);  //sends user to queue
            ResponseEntity<?> responseEntity= proxy.addUser(userDTO);
            return result;
        } else {
            throw new UserAlreadyExistException();
        }
    }

    @Override
    public User changeTaskStatusForUser(String emailId, BoardTask boardTask,int taskStatus) throws UserNotFoundException {

        List<BoardTask> boardTasks = getAllTasksForParticularUser(emailId);
        User user = userRepository.findById(emailId).get();
        for (int i = 0; i < boardTasks.size(); i++) {
            if (boardTasks.get(i).getTaskId() == boardTask.getTaskId()) {
                BoardTask status = boardTasks.get(i);
                user.getBoardTasks().get(i).setTaskStatus(taskStatus);
                userRepository.save(user);
                return user;
            }
        }
        return null;
    }



    @Override
    public User addTaskForUser(String emailId, BoardTask boardTask) throws UserNotFoundException {
        if (userRepository.findById(emailId).isPresent()) {
            User user = userRepository.findById(emailId).get();
            List<BoardTask> boardTasks = getAllTasksForParticularUser(emailId);
            int lastTask=boardTasks.size()-1;
            if(boardTasks.size()==0) {
                boardTask.setTaskId(0);
            }
            else{
                BoardTask boardTaskLast=boardTasks.get(lastTask);
                boardTask.setTaskId(boardTaskLast.getTaskId()+1);
            }
            user.getBoardTasks().add(boardTask);
            return userRepository.save(user);
        } else {
            throw new UserNotFoundException();
        }

    }

    @Override
    public List<BoardTask> deleteTaskForUser(String emailId, int taskId) throws UserNotFoundException {

        List<BoardTask> boardTasks = getAllTasksForParticularUser(emailId);
        User user = userRepository.findById(emailId).get();
        for (int i = 0; i < boardTasks.size(); i++) {
            if (boardTasks.get(i).getTaskId() == taskId) {
                BoardTask status = boardTasks.get(i);
                user.getBoardTasks().remove(i);
                userRepository.save(user);
                return user.getBoardTasks();
            }
        }
        return null;
    }

    @Override
    public User increaseTaskStatusForUser(String emailId, BoardTask boardTask) throws UserNotFoundException {

        List<BoardTask> boardTasks = getAllTasksForParticularUser(emailId);
        User user = userRepository.findById(emailId).get();
        for (int i = 0; i < boardTasks.size(); i++) {
            if (boardTasks.get(i).getTaskId() == boardTask.getTaskId()) {
                BoardTask status = boardTasks.get(i);
                user.getBoardTasks().get(i).setTaskStatus(status.getTaskStatus() + 1);
                userRepository.save(user);
                return user;
            }
        }
        return null;
    }

    @Override
    public User decreaseTaskStatusForUser(String emailId, BoardTask boardTask) throws UserNotFoundException {
        List<BoardTask> boardTasks = getAllTasksForParticularUser(emailId);
        User user = userRepository.findById(emailId).get();
        for (int i = 0; i < boardTasks.size(); i++) {
            if (boardTasks.get(i).getTaskId() == boardTask.getTaskId()) {
                BoardTask status = boardTasks.get(i);
                user.getBoardTasks().get(i).setTaskStatus(status.getTaskStatus() - 1);
                userRepository.save(user);
                return user;
            }
        }
        return null;
    }

    @Override
    public List<BoardTask> getAllTasksForParticularUser(String emailId) throws UserNotFoundException {
        if (userRepository.findById(emailId).isPresent()) {
            User user = userRepository.findById(emailId).get();
            return user.getBoardTasks();
        } else {
            throw new UserNotFoundException();
        }
    }


    @Override
    public User updateBoardTaskForUser(String emailId,BoardTask boardTask) throws UserNotFoundException {
        List<BoardTask> boardTasks = getAllTasksForParticularUser(emailId);
        User result = userRepository.findById(emailId).get();
//        user.getBoardTasks().get(i).setTaskStatus(status.getTaskStatus() + 1);
        for (int i = 0; i < boardTasks.size(); i++) {
            if (boardTasks.get(i).getTaskId() == boardTask.getTaskId()) {
                BoardTask status = boardTasks.get(i);
                result.getBoardTasks().get(i).setToDo(boardTask.getToDo());
                result.getBoardTasks().get(i).setPriority(boardTask.getPriority());
               // result.getBoardTasks().get(i).setDateAssign(boardTask.getDateAssign());
                result.getBoardTasks().get(i).setDeadline(boardTask.getDeadline());
              //  result.getBoardTasks().get(i).setTaskStatus(boardTask.getTaskStatus());

                userRepository.save(result);
                return result;
            }
        }
        return null;
    }
    @Override
    public BoardTask changeTaskPriorityForUser(String emailId, BoardTask boardTask, String priority) throws UserNotFoundException {
        List<BoardTask> boardTasks = getAllTasksForParticularUser(emailId);
        User user = userRepository.findById(emailId).get();
        for (int i = 0; i < boardTasks.size(); i++) {
            if (boardTasks.get(i).getTaskId() == boardTask.getTaskId()) {
                BoardTask status = boardTasks.get(i);
                user.getBoardTasks().get(i).setPriority(priority);
                userRepository.save(user);
                return user.getBoardTasks().get(i);
            }
        }
        return null;
    }
}
