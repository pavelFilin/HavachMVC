package ru.filin.HavachMVC.service.userManagement;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.filin.HavachMVC.model.userManagement.entities.User;
import ru.filin.HavachMVC.service.BaseService;

public interface UserService extends BaseService<User>, UserDetailsService {
    User getByEmail(String email);
}
