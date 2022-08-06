//package com.stackroute.user.User.input;
//
//import com.stackroute.user.User.model.User;
//import com.stackroute.user.User.services.UserServices;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class Consumer {
//    @Autowired
//    private UserServices userService;
//
//    @RabbitListener(queues = "user_queue")
//    public void getUserDTOAndAddToDb(UserDTO userDTO){
//        User user =new User();
//        user.setEmailId(userDTO.getEmailId());
//        user.setPassword(userDTO.getPassword());
//        user.setRole("ROLE_USER");
//        userService.register(user);
//    }
//}
