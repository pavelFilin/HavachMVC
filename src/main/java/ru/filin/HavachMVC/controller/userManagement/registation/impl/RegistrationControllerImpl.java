package ru.filin.HavachMVC.controller.userManagement.registation.impl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.filin.HavachMVC.controller.userManagement.registation.RegistrationController;
import ru.filin.HavachMVC.model.userManagement.RoleConstant;
import ru.filin.HavachMVC.model.userManagement.entities.Role;
import ru.filin.HavachMVC.model.userManagement.entities.User;
import ru.filin.HavachMVC.service.userManagement.UserService;

@Controller
@RequestMapping("registration")
public class RegistrationControllerImpl implements RegistrationController {

    UserService userService;

    public RegistrationControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @GetMapping
    public String getRegistrationPage(Model model) {
        return "userPages/registration";
    }

    @PostMapping
    public String addNewUser(@RequestParam User user, Model model) {
        User userFromDB = userService.getByEmail(user.getEmail());
        if (userFromDB != null) {
            model.addAttribute("message", "User is exit");
        }

        user.getRoles().add(new Role(RoleConstant.USER.getCode(), RoleConstant.USER.name()));
        userService.save(user);
        return "redirect:/login";
    }
}
