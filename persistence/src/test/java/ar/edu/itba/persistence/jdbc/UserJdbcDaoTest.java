package ar.edu.itba.persistence.jdbc;

import ar.edu.itba.model.User;
import ar.edu.itba.persistence.TestConfig;
import ar.edu.itba.persistence.jdbc.UserJdbcDao;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import javax.sql.DataSource;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Sql("classpath:schema.sql")
@Ignore("JDBC Dao test ignored")
public class UserJdbcDaoTest {
    private static final String PASSWORD = "Password";
    private static final String USERNAME = "Username";
    private static final String MAIL = "Mail";
    private static final Date CURRENTDAY = new Date(2000,8,15);

    @Autowired
    private DataSource ds;
    @Autowired
    private UserJdbcDao userDao;
    private JdbcTemplate jdbcTemplate;
    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(ds);
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "users");
    }

    @Test
    public void testCreate() {
        final User user = userDao.create(USERNAME, PASSWORD, MAIL, CURRENTDAY);
        assertNotNull(user);
        assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "users"));
        assertEquals(USERNAME, user.getUsername());
        assertEquals(PASSWORD, user.getPassword());
        assertEquals(MAIL, user.getMail());
        assertEquals(CURRENTDAY, user.getCurrentDay());
    }

    @Test
    public void testFindByUsername() {
        final long id = userDao.create(USERNAME, PASSWORD, MAIL, CURRENTDAY).getId();
        final User user = userDao.findByUsername(USERNAME);
        assertNotNull(user);
        assertEquals(USERNAME, user.getUsername());
        assertEquals(PASSWORD, user.getPassword());
        assertEquals(MAIL, user.getMail());
        assertEquals(CURRENTDAY, user.getCurrentDay());
        assertEquals(id, user.getId());
    }

    @Test
    public void testFindById() {
        final long id = userDao.create(USERNAME, PASSWORD, MAIL, CURRENTDAY).getId();
        final User user = userDao.findById(id);
        assertNotNull(user);
        assertEquals(USERNAME, user.getUsername());
        assertEquals(PASSWORD, user.getPassword());
        assertEquals(MAIL, user.getMail());
        assertEquals(CURRENTDAY, user.getCurrentDay());
        assertEquals(id, user.getId());
    }
}
