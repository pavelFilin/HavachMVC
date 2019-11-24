package ru.filin.HavachMVC.service.userManagement.impl;

import org.springframework.stereotype.Service;
import ru.filin.HavachMVC.model.userManagement.entities.User;
import ru.filin.HavachMVC.model.userManagement.repositories.UserRepository;
import ru.filin.HavachMVC.service.userManagement.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public User getById(long id) {
        return userRepository.getById(id);
    }

    @Override
    public void delete(long id) {
        userRepository.delete(id);
    }

    @Override
    public void update(User obj) {
        userRepository.update(obj);
    }

    @Override
    public Long save(User obj) {
        return userRepository.save(obj);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }
}
