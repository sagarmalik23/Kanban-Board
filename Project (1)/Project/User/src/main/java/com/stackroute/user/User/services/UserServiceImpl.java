package com.stackroute.user.User.services;

import com.stackroute.user.User.model.User;
import com.stackroute.user.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserServices {



    @Autowired
    private UserRepository userRepository;

    @Override
    public User forgotPassword(String emailId){
        if(userRepository.findById(emailId).isPresent()){
            return userRepository.findById(emailId).get();
        }
        else {
            return null;
        }
    }
    @Override
    public User register(User user) {
        //if user already exists
        User user1 = userRepository.save(user);
        return user1;
    }

    @Override
    public User authenticate(String emailId, String password) {
        User user = userRepository.findUserByEmailIdAndPassword(emailId, password);
        if(user!=null){
            return user;
        }
        return null;
    }
}
