package ru.filin.HavachMVC.controller.userManagement.profile;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import ru.filin.HavachMVC.model.userManagement.entities.User;

public interface ProfileController {
    String getProfilePage(@AuthenticationPrincipal User user, Model model);
}
