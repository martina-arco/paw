package ar.edu.itba.persistence;

import ar.edu.itba.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import javax.print.attribute.standard.MediaSize;
import javax.sql.DataSource;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Sql("classpath:schema.sql")
public class TeamJdbcDaoTest {

    private final String NAME = "Boca";
    private final int FANCOUNT = 12331424;
    private final int FANTRUST = 99;
    private final int BOARDTRUST = 1231;
    private final int MONEY = 928522342;
    private final int NO_ID = 0;
    private final int SQL_NULL_INT = 0;

    @Autowired
    private DataSource ds;
    @Autowired
    private LeagueJdbcDao leagueDao;
    @Autowired
    private UserJdbcDao userDao;
    @Autowired
    private PlayerJdbcDao playerDao;
    @Autowired
    private TeamJdbcDao teamDao;
    @Autowired
    private StadiumJdbcDao stadiumDao;

    private JdbcTemplate jdbcTemplate;
    private League league;
    private User user;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(ds);
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "users", "league", "team", "stadium");
        user = userDao.create("c","","", new Date());
        league = leagueDao.create("", 0, user);
    }

    @Test
    public void testCreate() {
        final Team team = teamDao.create(NAME, league, null, null, null, null, FANCOUNT, FANTRUST, BOARDTRUST, MONEY);
        assertNotNull(team);
        assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "team"));
        assertEquals(NAME, team.getName());
        assertEquals(league, team.getLeague());
        assertEquals(SQL_NULL_INT, team.getFormationId());
        assertEquals(FANCOUNT, team.getFanCount());
        assertEquals(FANTRUST, team.getFanTrust());
        assertEquals(BOARDTRUST, team.getBoardTrust());
        assertEquals(MONEY, team.getMoney());
    }

    @Test
    public void testCreateById() {
        final Team team = teamDao.create(NAME, league.getId(), NO_ID, FANCOUNT, FANTRUST, BOARDTRUST, MONEY);
        assertNotNull(team);
        assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "team"));
        assertEquals(NAME, team.getName());
        assertEquals(NAME, team.getName());
        assertEquals(league.getId(), team.getLeagueId());
        assertEquals(SQL_NULL_INT, team.getFormationId());
        assertEquals(FANCOUNT, team.getFanCount());
        assertEquals(FANTRUST, team.getFanTrust());
        assertEquals(BOARDTRUST, team.getBoardTrust());
        assertEquals(MONEY, team.getMoney());
        assertEquals(MONEY, team.getMoney());
    }

    @Test
    public void testFindById() {
        final long id = teamDao.create(NAME, league, null, null, null, null, FANCOUNT, FANTRUST, BOARDTRUST, MONEY).getId();
        Team team = teamDao.findById(id);
        assertNotNull(team);
        assertEquals(NAME, team.getName());
        assertEquals(league.getId(), team.getLeagueId());
        assertEquals(SQL_NULL_INT, team.getFormationId());
        assertEquals(FANCOUNT, team.getFanCount());
        assertEquals(FANTRUST, team.getFanTrust());
        assertEquals(BOARDTRUST, team.getBoardTrust());
        assertEquals(MONEY, team.getMoney());
        assertEquals(id, team.getId());
    }

    @Test
    public void testFindAllByLeagueId() {
        final long id = teamDao.create(NAME, league, null, null, null, null, FANCOUNT, FANTRUST, BOARDTRUST, MONEY).getId();
        Team team = teamDao.findAllByLeagueId(league.getId()).get(0);
        assertNotNull(team);
        assertEquals(NAME, team.getName());
        assertEquals(league.getId(), team.getLeagueId());
        assertEquals(SQL_NULL_INT, team.getFormationId());
        assertEquals(FANCOUNT, team.getFanCount());
        assertEquals(FANTRUST, team.getFanTrust());
        assertEquals(BOARDTRUST, team.getBoardTrust());
        assertEquals(MONEY, team.getMoney());
        assertEquals(id, team.getId());
    }
}