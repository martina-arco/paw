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

import javax.sql.DataSource;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Sql("classpath:schema.sql")
public class MatchJdbcDaoTest {

    private static final Date DATE = new Date(2000,8,15);
    private static final Date FUTURE = new Date(2000,8,16);
    private static final Date PAST = new Date(2000,8,14);

    @Autowired
    private DataSource ds;
    @Autowired
    private LeagueJdbcDao leagueDao;
    @Autowired
    private UserJdbcDao userDao;
    @Autowired
    private MatchJdbcDao matchDao;
    @Autowired
    private TeamJdbcDao teamDao;

    private JdbcTemplate jdbcTemplate;
    private Team home;
    private Team away;
    private League league;
    private User user;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(ds);
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "users", "league", "team", "match");
        user = userDao.create("c","","", new Date());
        league = leagueDao.create("", 0, user);
        home = teamDao.create("a", league, null, null, null, null, 0,0,0,0);
        away = teamDao.create("b", league, null, null, null, null, 0,0,0,0);
    }

    @Test
    public void testCreate() {
        final Match match = matchDao.create(league, home, away, DATE);
        assertNotNull(match);
        assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "match"));
        assertEquals(league, match.getLeague());
        assertEquals(home, match.getHome());
        assertEquals(away, match.getAway());
        assertEquals(DATE, match.getDay());
    }

    @Test
    public void testFindById() {
        final long id = matchDao.create(league, home, away, DATE).getId();
        Match match = matchDao.findById(id);
        assertNotNull(match);
        assertEquals(league.getId(), match.getLeagueId());
        assertEquals(home.getId(), match.getHomeId());
        assertEquals(away.getId(), match.getAwayId());
        assertEquals(DATE, match.getDay());
        assertEquals(id, match.getId());
    }

    @Test
    public void testFindByTeamId() {
        final long id = matchDao.create(league, home, away, DATE).getId();
        Match match = matchDao.findByTeamId(home.getId()).get(0);
        assertNotNull(match);
        assertEquals(league.getId(), match.getLeagueId());
        assertEquals(home.getId(), match.getHomeId());
        assertEquals(away.getId(), match.getAwayId());
        assertEquals(DATE, match.getDay());
        assertEquals(id, match.getId());
    }

    @Test
    public void testFindByTeamIdFromDate() {
        final long id = matchDao.create(league, home, away, DATE).getId();
        Match match = matchDao.findByTeamIdFromDate(home.getId(), DATE).get(0);
        assertNotNull(match);
        assertEquals(league.getId(), match.getLeagueId());
        assertEquals(home.getId(), match.getHomeId());
        assertEquals(away.getId(), match.getAwayId());
        assertEquals(DATE, match.getDay());
        assertEquals(id, match.getId());

        match = matchDao.findByTeamIdFromDate(home.getId(), PAST).get(0);
        assertNotNull(match);
        assertEquals(league.getId(), match.getLeagueId());
        assertEquals(home.getId(), match.getHomeId());
        assertEquals(away.getId(), match.getAwayId());
        assertEquals(DATE, match.getDay());
        assertEquals(id, match.getId());
    }

    @Test
    public void testFindByLeagueId() {
        final long id = matchDao.create(league, home, away, DATE).getId();
        Match match = matchDao.findByLeagueId(league.getId()).get(0);
        assertNotNull(match);
        assertEquals(league.getId(), match.getLeagueId());
        assertEquals(home.getId(), match.getHomeId());
        assertEquals(away.getId(), match.getAwayId());
        assertEquals(DATE, match.getDay());
        assertEquals(id, match.getId());
    }

    @Test
    public void testFindByLeagueIdAndDate() {
        final long id = matchDao.create(league, home, away, DATE).getId();
        Match match = matchDao.findByLeagueIdAndDate(league.getId(), DATE).get(0);
        assertNotNull(match);
        assertEquals(league.getId(), match.getLeagueId());
        assertEquals(home.getId(), match.getHomeId());
        assertEquals(away.getId(), match.getAwayId());
        assertEquals(DATE, match.getDay());
        assertEquals(id, match.getId());
    }

    @Test
    public void testFindByLeagueIdAndBeforeDate() {
        final long id = matchDao.create(league, home, away, DATE).getId();
        Match match = matchDao.findByLeagueIdAndBeforeDate(league.getId(), FUTURE).get(0);
        assertNotNull(match);
        assertEquals(league.getId(), match.getLeagueId());
        assertEquals(home.getId(), match.getHomeId());
        assertEquals(away.getId(), match.getAwayId());
        assertEquals(DATE, match.getDay());
        assertEquals(id, match.getId());
    }

    @Test
    public void testSave() {
        final long id = matchDao.create(league, home, away, DATE).getId();
        Match match = matchDao.findById(id);
        assertNotNull(match);

        League league2 = league = leagueDao.create("", 0, user);
        Team home2 = teamDao.create("a", league, null, null, null, null, 0,0,0,0);
        Team away2 = teamDao.create("b", league, null, null, null, null, 0,0,0,0);
        Date date2 = new Date(2012, 11, 7);
        boolean played = true;
        int homeScore = 4;
        int awayScore = 2;

        match.setLeague(league2);
        match.setHome(home2);
        match.setAway(away2);
        match.setDay(date2);
        match.setPlayed(played);
        match.setHomeScore(homeScore);
        match.setAwayScore(awayScore);
        match.finish();
        int homePoints = match.getHomePoints();
        int awayPoints = match.getAwayPoints();


        matchDao.save(match);
        match = matchDao.findById(id);

        assertEquals(league2.getId(), match.getLeagueId());
        assertEquals(home2.getId(), match.getHomeId());
        assertEquals(away2.getId(), match.getAwayId());
        assertEquals(date2, match.getDay());
        assertEquals(played, match.isPlayed());
        assertEquals(homeScore, match.getHomeScore());
        assertEquals(awayScore, match.getAwayScore());
        assertEquals(homePoints, match.getHomePoints());
        assertEquals(awayPoints, match.getAwayPoints());
    }
}