package ru.filin.HavachMVC.configuration;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class HchJdbcUserDetailsManager extends JdbcUserDetailsManager {

    @Override
    protected List<UserDetails> loadUsersByUsername(String username) {
        List<UserDetails> query = this.getJdbcTemplate().query("SELECT email, password, active from usr where email=?", new String[]{username}, new RowMapper<UserDetails>() {
            @Override
            public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
                String userName = rs.getString(1);
                String password = rs.getString(2);
                boolean enabled = rs.getBoolean(3);
                boolean accLocked = false;
                boolean accExpired = false;
                boolean credsExpired = false;

                return new User(userName, password, enabled, !accExpired, !credsExpired, !accLocked, AuthorityUtils.NO_AUTHORITIES);
            }
        });
        return query;
    }

//    private class UserRowMapper<UserDetails>  implements RowMapper<UserDetails>{
//
//        @Override
//        public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
//            return null;
//        }
//    }
}
