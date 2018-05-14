package ar.edu.itba.persistence;

import ar.edu.itba.model.Formation;
import ar.edu.itba.model.League;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import javax.sql.DataSource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Sql("classpath:schema.sql")
public class LeagueJdbcDaoTest {

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
        /*final League league = userDao.create(, PASSWORD, MAIL);
        assertNotNull(user);
        assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "users"));
        assertEquals(USERNAME, user.getUsername());
        assertEquals(PASSWORD, user.getPassword());*/
    }
}