package com.epam.training.application.controller;

import com.epam.training.application.domain.User;
import com.epam.training.application.service.RoleService;
import com.epam.training.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class SecurityController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public SecurityController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public String loginPage(@RequestParam(value = "error",required = false) String error,
                            @RequestParam(value = "logout",	required = false) String logout,
                            Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid Credentials provided.");
        }
        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }

        return "loginPage";
    }

    @RequestMapping(value = "/registerPage", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registerPage";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm")@Valid User userForm,
                               BindingResult result,Model model) {
        if(result.hasErrors()) {
                return "registerPage";
        }
        if(userService.getAll()
                    .stream()
                    .anyMatch((user)->user.getEmail().equals(userForm.getEmail()))){
                model.addAttribute("error","Email already exists!");
                return "registerPage";
        }
        userService.saveOrUpdate(userForm);
        roleService.setRoleForUser(userService.getUserByEmail(userForm.getEmail()).getId()
                , 2);

        return "redirect:/loginPage";
    }
}
