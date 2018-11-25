package com.epam.training.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
    @RequestMapping(value = "error",method = RequestMethod.GET)
    public String errorMap()    {
        return "error" ;
    }

}
