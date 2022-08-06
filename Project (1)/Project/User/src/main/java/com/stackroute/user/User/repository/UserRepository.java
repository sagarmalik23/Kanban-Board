package com.stackroute.user.User.repository;

import com.stackroute.user.User.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    public abstract User findUserByEmailIdAndPassword(String emailId, String password);
}
