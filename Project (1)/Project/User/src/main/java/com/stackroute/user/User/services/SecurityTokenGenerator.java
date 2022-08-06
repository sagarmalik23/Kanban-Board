package com.stackroute.user.User.services;

import com.stackroute.user.User.model.User;

import java.util.Map;

public interface SecurityTokenGenerator {
     Map<String, String> generateToken(User user);
}
