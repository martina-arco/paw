package ar.edu.itba.persistence;

import ar.edu.itba.interfaces.dao.UserDao;
import ar.edu.itba.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserJdbcDao implements UserDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserJdbcDao.class);

    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    @Value("classpath:init.sql")
    private Resource initSql;

    private final static RowMapper<User> ROW_MAPPER = new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new User(rs.getLong("userid"), rs.getString("username"),
                            rs.getString("password"), rs.getString("mail"),
                            rs.getLong("team"), rs.getDate("currentDay"));
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
    public User create(String username, String password, String mail, Date currentDay) {
        final Map<String, Object> args = new HashMap<>();
        args.put("username", username);
        args.put("password", password);
        args.put("mail", mail);
        args.put("currentDay", currentDay);
        final Number userId = jdbcInsert.executeAndReturnKey(args);
        initDataSet(userId.longValue());
        return new User(userId.longValue(), username, password, mail, null, currentDay);
    }

    @Override
    public boolean save(User user) {
        jdbcTemplate.update("UPDATE users SET username = ?, password = ?, mail = ?, currentday = ?, team = ? " +
                "WHERE userid = ?", user.getUsername(), user.getPassword(), user.getMail(), user.getCurrentDay(),user.getTeam().getId(), user.getId());
        return true;
    }

    private boolean initDataSet(long userId) {
        StringBuilder script = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(initSql.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                script.append(line);
            }
        } catch (IOException e) {
            LOGGER.error("Failed to read init.sql", e);
            return false;
        }
        int index;
        while ((index = script.indexOf("?")) != -1) {
            script.replace(index, index + 1, Long.toString(userId));
        }

        try {
            ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(),
                    new InputStreamResource( new ByteArrayInputStream(script.toString().getBytes())));
        } catch (SQLException e) {
            LOGGER.error("Failed to get datasource", e);
            return false;
        }
        return true;
    }
}