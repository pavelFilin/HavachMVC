package ru.filin.HavachMVC.controller.userManagement.profile.imp;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.filin.HavachMVC.controller.userManagement.profile.ProfileController;
import ru.filin.HavachMVC.model.userManagement.entities.User;

@Controller
@RequestMapping("/user/profile")
public class ProfileControllerImpl implements ProfileController {

    @Override
    @GetMapping
    public String getProfilePage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "userPages/profile";
    }
}
