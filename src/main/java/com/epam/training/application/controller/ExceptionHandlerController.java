package com.epam.training.application.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler({SQLIntegrityConstraintViolationException.class})
    public String sqlException(Model model){

        model.addAttribute("error","You already have this course!");
        return "redirect:/";
    }
}
