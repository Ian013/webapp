package com.epam.training.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/*@Controller
public class MainController {
    @RequestMapping(value ="/welcome",method = RequestMethod.GET)
    public String greetingForm(@RequestParam(name = "name",required = false,defaultValue = "World")
                                           String name, Model model) {
        model.addAttribute("greeting", name);
        return "greeting";
    }
}
/*@GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

*/
