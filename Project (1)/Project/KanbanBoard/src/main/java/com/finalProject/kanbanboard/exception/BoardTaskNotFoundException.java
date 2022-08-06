package com.finalProject.kanbanboard.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND, reason="task not found")
public class BoardTaskNotFoundException extends Exception{
}
