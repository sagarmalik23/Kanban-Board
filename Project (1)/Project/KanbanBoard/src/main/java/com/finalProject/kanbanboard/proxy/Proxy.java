package com.finalProject.kanbanboard.proxy;

import com.finalProject.kanbanboard.model.User;
import com.finalProject.kanbanboard.rabbitmq.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// http://localhost:64200/user/signup   [post]
@FeignClient(name="user-service" ,url="localhost:64200")
public interface Proxy {
    @PostMapping("/user/signup")
    public ResponseEntity<?> addUser(@RequestBody UserDTO user);
}

