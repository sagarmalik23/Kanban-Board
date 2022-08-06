package com.finalProject.kanbanboard.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class BoardTask {
    @Id
    protected int taskId;
    protected String toDo;
    protected String priority;
    protected String dateAssign;
    protected String deadline;
    protected int taskStatus;
}
