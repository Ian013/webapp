package com.epam.training.application.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLDataException;
import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(SQLDataException.class)
    public String sqlDataExceptionHandle(){
        return "error";
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public String emptyResultSetExceptionHandler(Model model){
        model.addAttribute("error","No such course");
        return "redirect:/";
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public String sqlException(Model model){
        model.addAttribute("error","You already have this course!");
        return "redirect:/";
    }
}
