package ar.edu.itba.persistence;

import ar.edu.itba.interfaces.dao.UserDao;
import ar.edu.itba.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserJdbcDao implements UserDao {

    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    private final static RowMapper<User> ROW_MAPPER = new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new User(rs.getString("username"), rs.getInt("userid"));
                }
            };

    @Autowired
    public UserJdbcDao(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("users")
                .usingGeneratedKeyColumns("userid");
    }

    @Override
    public User findById(final long id) {
        final List<User> list = jdbcTemplate.query("SELECT * FROM users WHERE userid = ?", ROW_MAPPER, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public User findByUsername(String username) {
        final List<User> list = jdbcTemplate.query("SELECT * FROM users WHERE username = ?", ROW_MAPPER, username);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public User create(final String username) {
        final Map<String, Object> args = new HashMap<>();
        args.put("username", username); // la key es el nombre de la columna
        final Number userId = jdbcInsert.executeAndReturnKey(args);
        return new User(username, userId.longValue());
    }

    @Override
    public User create(String username, String password) {
        final Map<String, Object> args = new HashMap<>();
        args.put("username", username);
        args.put("password", password);
        final Number userId = jdbcInsert.executeAndReturnKey(args);
        return new User(username, password, userId.longValue());
    }


}