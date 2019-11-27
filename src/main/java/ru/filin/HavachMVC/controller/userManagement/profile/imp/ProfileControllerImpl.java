package ru.filin.HavachMVC.controller.userManagement.profile.imp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.filin.HavachMVC.controller.userManagement.profile.ProfileController;

@Controller
@RequestMapping("/user/profile")
public class ProfileControllerImpl implements ProfileController {

    @Override
    @GetMapping
    public String getProfilePage(Model model) {
        return "userPages/profile";
    }
}
