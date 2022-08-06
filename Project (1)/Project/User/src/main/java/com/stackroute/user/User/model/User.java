package com.stackroute.user.User.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    private String emailId;
    private String password;
    private String role;


}
