package com.stackroute.user.User.services;

import com.stackroute.user.User.model.User;

public interface UserServices {
    User register(User user);
    User authenticate(String emailId, String password);

    User forgotPassword(String emailId);


}
