package ru.filin.HavachMVC.controller.userManagement.profile.imp;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.filin.HavachMVC.controller.userManagement.profile.ProfileController;
import ru.filin.HavachMVC.model.userManagement.entities.User;
import ru.filin.HavachMVC.service.userManagement.UserService;

import java.util.List;

@Controller
@RequestMapping("/user/profile")
public class ProfileControllerImpl implements ProfileController {
    private UserService userService;

    public ProfileControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @GetMapping
    public String getProfilePage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "userPages/profile";
    }

    @Override
    @PostMapping
    public String updateUser(User user, @AuthenticationPrincipal User userFromDb, Model model) {
        if (!StringUtils.isEmpty(user.getFirstName())) {
            userFromDb.setFirstName(user.getFirstName());
        }

        if (!StringUtils.isEmpty(user.getLastName())) {
            userFromDb.setLastName(user.getLastName());
        }

        if (!StringUtils.isEmpty(user.getPassword())) {
            userFromDb.setPassword(user.getPassword());
        }

        userService.update(userFromDb);

        model.addAttribute("user", userFromDb);
        return "userPages/profile";
    }

    @Override
    @GetMapping("userlist")
    public String getUserList(Model model) {
        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        return "userPages/usersrolemanagment";
    }


}
