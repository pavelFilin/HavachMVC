package ru.filin.HavachMVC.service.userManagement.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.filin.HavachMVC.model.userManagement.RoleConstant;
import ru.filin.HavachMVC.model.userManagement.entities.Role;
import ru.filin.HavachMVC.model.userManagement.entities.User;
import ru.filin.HavachMVC.model.userManagement.repositories.UserRepository;
import ru.filin.HavachMVC.service.userManagement.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User byEmail = getByEmail(email);
        return byEmail;
    }

    @Override
    public void updateRoles(long userId, Map<String, String> form) {
        User user = userRepository.getById(userId);

        Set<String> roles = Arrays.stream(RoleConstant.values())
                .map(RoleConstant::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(new Role(RoleConstant.valueOf(key).getCode(), key));
            }
        }

        userRepository.update(user);
    }
}
