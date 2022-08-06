package com.finalProject.kanbanboard.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document

public class User {
   @Id
   protected String emailId;
   protected String name;
   private byte[] image;
   protected List<BoardTask> boardTasks;


}
