package com.myspring.auth.advice;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class HandlerControllerAdvice {

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public String handlerException() {
        return "you do not have right";
    }
}
