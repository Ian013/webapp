package com.epam.training.application.controller;

import com.epam.training.application.domain.User;
import com.epam.training.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
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

        return "/loginPage";
    }

    @RequestMapping(value = "/registerPage", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "/registerPage";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registration(
                               @RequestAttribute("firstName") String firstName,
                               @RequestAttribute("lastName")String lastName,
                               @RequestAttribute("email")String email,
                               @RequestAttribute("password")String password,
                               @RequestAttribute("passwordConfirmation")String passConfirm
                               ) {
        if(password.equals(passConfirm)) {
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPassword(password);
            user.setEmail(email);

            userService.saveOrUpdate(user);
        }

        return "redirect:/welcome";
    }
}
