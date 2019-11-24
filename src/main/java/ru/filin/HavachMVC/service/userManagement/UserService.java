package ru.filin.HavachMVC.service.userManagement;

import ru.filin.HavachMVC.model.userManagement.entities.User;
import ru.filin.HavachMVC.service.BaseService;

public interface UserService extends BaseService<User> {
    User getByEmail(String email);
}
