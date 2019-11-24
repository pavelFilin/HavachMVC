package ru.filin.HavachMVC.model.userManagement.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.filin.HavachMVC.model.userManagement.RoleConstant;
import ru.filin.HavachMVC.model.userManagement.entities.Role;
import ru.filin.HavachMVC.model.userManagement.entities.User;
import ru.filin.HavachMVC.model.userManagement.repositories.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> getAll() {
        String getAllUsers = "SELECT * " +
                "FROM usr u \n" +
                "INNER JOIN user_roles ur ON u.id=ur.user_id \n" +
                "INNER JOIN roles r on ur.role_id = r.id";

        Map<Long, User> users = new HashMap<>();

        jdbcTemplate.query(getAllUsers, (rs, rowNum) -> {
            User user = new User();
            while (rs.next()) {
                long id = rs.getLong("id");
                if (users.containsKey(id)) {
                    User userAlreadyExist = users.get(id);
                    List<Role> roles = userAlreadyExist.getRoles();
                    roles.add(new Role(rs.getLong("role_id"), rs.getString("name")));
                } else {
                    user.getRoles().add(new Role(rs.getLong("role_id"), rs.getString("name")));
                    user.setId(id);
                    user.setEmail(rs.getString("email"));
                    user.setFirstName(rs.getString("first_name"));
                    user.setSecondName(rs.getString("second_name"));
                }
            }
            return null;
        });

        return new ArrayList<>(users.values());
    }

    @Override
    public User getById(long id) {
        String getUserById = "SELECT * " +
                "FROM usr u \n" +
                "INNER JOIN user_roles ur ON u.id=ur.user_id \n" +
                "INNER JOIN roles r on ur.role_id = r.id where u.id = ?";

        final User user = new User();
        jdbcTemplate.queryForObject(getUserById, new Object[]{id}, (rs, rowNum) -> {
            while (rs.next()) {
                if (user.getId() != 0) {
                    user.getRoles().add(new Role(rs.getLong("role_id"), rs.getString("name")));
                } else {
                    user.getRoles().add(new Role(rs.getLong("role_id"), rs.getString("name")));
                    user.setId(rs.getLong("id"));
                    user.setEmail(rs.getString("email"));
                    user.setFirstName(rs.getString("first_name"));
                    user.setSecondName(rs.getString("second_name"));
                }
            }
            return null;
        });
        return user;
    }

    @Override
    public void delete(long id) {
        String deleteUser;
        return;
    }

    @Override
    public void update(User obj) {
        String updateUser;
        return;
    }

    @Override
    @Transactional
    public Long save(User obj) {
        String getIdByUser = "SELECT nextval(pg_get_serial_sequence('usr', 'id'))";

        long userId = jdbcTemplate.query(getIdByUser, rs -> {
            return rs.getLong(1);
        });

        String saveUser = "INSERT INTO " +
                "   usr (first_name, second_name, password, email) " +
                "VALUES (?,?,?,?); ";

        String saveRoles = "INSERT INTO " +
                "user_roles (user_id, role_id) " +
                "VALUES (" + userId + "?)";

        List<Role> roles = obj.getRoles();
        for (Role role : roles) {
            jdbcTemplate.update(saveRoles, new Object[]{RoleConstant.valueOf(role.getName()).getCode()});
        }

        return userId;
    }

    @Override
    public User getByEmail(String email) {
        String getUserById = "SELECT * " +
                "FROM usr u \n" +
                "INNER JOIN user_roles ur ON u.id=ur.user_id \n" +
                "INNER JOIN roles r on ur.role_id = r.id where u.id = ?";

        final User user = new User();
        jdbcTemplate.queryForObject(getUserById, new Object[]{email}, (rs, rowNum) -> {
            while (rs.next()) {
                if (user.getId() != 0) {
                    user.getRoles().add(new Role(rs.getLong("role_id"), rs.getString("name")));
                } else {
                    user.getRoles().add(new Role(rs.getLong("role_id"), rs.getString("name")));
                    user.setId(rs.getLong("id"));
                    user.setEmail(rs.getString("email"));
                    user.setFirstName(rs.getString("first_name"));
                    user.setSecondName(rs.getString("second_name"));
                }
            }
            return null;
        });
        return user;
    }
}
