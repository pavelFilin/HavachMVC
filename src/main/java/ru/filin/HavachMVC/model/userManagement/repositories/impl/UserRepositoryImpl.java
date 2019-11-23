package ru.filin.HavachMVC.model.userManagement.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.filin.HavachMVC.model.userManagement.entities.User;
import ru.filin.HavachMVC.model.userManagement.repositories.UserRepository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> getAll() {
        String getAllUsers = "SELECT * from usr";
        jdbcTemplate.query(getAllUsers, rs -> {
            while (rs.next()) {

            }
        });
    }

    @Override
    public User getById(long id) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void update(User obj) {

    }

    @Override
    public Long save(User obj) {
        return null;
    }
}
