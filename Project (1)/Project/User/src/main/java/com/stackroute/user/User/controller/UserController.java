package com.stackroute.user.User.controller;

import com.stackroute.user.User.model.User;
import com.stackroute.user.User.services.SecurityTokenGenerator;
import com.stackroute.user.User.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private SecurityTokenGenerator securityTokenGenerator;

    @GetMapping("/forgotPassword/{emailId}")
    public ResponseEntity<?> forgotPassword(@PathVariable String emailId){
        return new ResponseEntity<>(userService.forgotPassword(emailId),HttpStatus.OK);
    }

    // http://localhost:64200/user/signup
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.register(user), HttpStatus.OK);
    }

    // http://localhost:64200/user/login
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody User user) {

        User result = userService.authenticate(user.getEmailId(),user.getPassword());
        if(result!=null) {      //authentication success
            result.setPassword(null);
            return new ResponseEntity<>(securityTokenGenerator.generateToken(result),HttpStatus.OK);
        }
        else {                  //authentication failed
            return new ResponseEntity<>("Login failed", HttpStatus.NOT_FOUND);
        }
    }


    // http://localhost:64200/user/signup
    @PostMapping("/register")
    public ResponseEntity<?> registerUser1(@RequestBody User user) {
        if(user.getRole()==null) {
            user.setRole("ROLE_USER");              // setting default role as user.
        }
        User result = userService.register(user);
        result.setPassword(null);                   // showing password as null in response.
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
