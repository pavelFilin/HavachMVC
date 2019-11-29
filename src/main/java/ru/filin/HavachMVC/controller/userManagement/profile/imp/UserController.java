package ru.filin.HavachMVC.controller.userManagement.profile.imp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.filin.HavachMVC.model.userManagement.entities.User;
import ru.filin.HavachMVC.service.userManagement.UserService;

import java.util.Map;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public String userEditPage(@PathVariable long id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        model.addAttribute("user", user);
        return "userPages/edituser";
    }

    @PostMapping
    public String userSave(
            @RequestParam long id,
            @RequestParam Map<String, String> form
    ) {
        userService.updateRoles(id, form);
        return "redirect:/userPages/profile";
    }}
