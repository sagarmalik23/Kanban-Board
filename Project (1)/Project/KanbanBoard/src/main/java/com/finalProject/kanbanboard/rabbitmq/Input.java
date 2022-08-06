package com.finalProject.kanbanboard.rabbitmq;

import com.finalProject.kanbanboard.model.BoardTask;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Input {
    private String emailId;
    private String name;
    private String password;
    private List<BoardTask> boardTasks;
    private String role;
    private byte[] image;
}
