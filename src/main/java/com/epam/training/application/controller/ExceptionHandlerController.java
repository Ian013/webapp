package com.epam.training.application.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLDataException;
import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(SQLDataException.class)
    public String sqlDataExceptionHandle(){
        return "error";
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public String emptyResultSetExceptionHandler(RedirectAttributes ra){
        ra.addFlashAttribute("flashError","No such course!");
        return "redirect:/";
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public String sqlException( RedirectAttributes ra){
        ra.addFlashAttribute("flashError","You already have this course!");
        return "redirect:/";
    }
}
