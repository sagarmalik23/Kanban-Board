package com.finalProject.kanbanboard.rabbitmq;

public class UserDTO {
    private String emailId;
    private String password, role;

    public UserDTO() {
    }

    public UserDTO(String emailId, String password, String role) {
        this.emailId = emailId;
        this.password = password;
       this.role = role;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}




