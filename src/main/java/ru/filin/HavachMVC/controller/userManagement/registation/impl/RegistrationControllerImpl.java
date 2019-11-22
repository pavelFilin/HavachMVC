package ru.filin.HavachMVC.controller.userManagement.registation.impl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.filin.HavachMVC.controller.userManagement.registation.RegistrationController;

@Controller
@RequestMapping("registration")
public class RegistrationControllerImpl implements RegistrationController {
    @Override
    @GetMapping
    public String getRegistrationPage(Model model) {
        return "userPages/registration";
    }
}
