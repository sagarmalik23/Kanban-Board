package com.finalProject.kanbanboard.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.CONFLICT, reason="User all ready exist")
public class UserAlreadyExistException extends Exception{
}
