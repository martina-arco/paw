package ar.edu.itba.persistence;

import ar.edu.itba.model.Formation;
import ar.edu.itba.model.League;
import ar.edu.itba.model.Match;
import ar.edu.itba.model.User;
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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Sql("classpath:schema.sql")
public class LeagueJdbcDaoTest {

    private final String NAME = "League";
    private final int PRIZE = 1234;
    private final Map<Date, List<Match>> FIXTURE = new HashMap<>();

    @Autowired
    private DataSource ds;
    @Autowired
    private LeagueJdbcDao leagueDao;
    @Autowired
    private UserJdbcDao userDao;

    private JdbcTemplate jdbcTemplate;
    private User user;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(ds);
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "users");
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "league");
        userDao.create("a","","", new Date());
        userDao.create("b","","", new Date());
        user = userDao.create("c","","", new Date());
    }

    @Test
    public void testCreate() {
        final League league = leagueDao.create(NAME, FIXTURE, PRIZE, user);
        assertNotNull(league);
        assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "league"));
        assertEquals(NAME, league.getName());
        assertEquals(FIXTURE, league.getFixture());
        assertEquals(PRIZE, league.getPrize());
    }

    @Test
    public void testFindById() {
        final long id = leagueDao.create(NAME, FIXTURE, PRIZE, user).getId();
        League league = leagueDao.findById(id);
        assertNotNull(league);
        assertEquals(NAME, league.getName());
        assertEquals(PRIZE, league.getPrize());
        assertEquals(id, league.getId());
    }

    @Test
    public void testFindAllByUserId() {
        final long id = leagueDao.create(NAME, FIXTURE, PRIZE, user).getId();
        League league = leagueDao.findAllByUserId(user.getId()).get(0);
        assertNotNull(league);
        assertEquals(NAME, league.getName());
        assertEquals(PRIZE, league.getPrize());
        assertEquals(id, league.getId());
    }
}