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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Sql("classpath:schema.sql")
public class EventJdbcDaoTest {

    private final Event.Type TYPE = Event.Type.SCORE;
    private final int MINUTE = 1234;

    @Autowired
    private DataSource ds;
    @Autowired
    private LeagueJdbcDao leagueDao;
    @Autowired
    private UserJdbcDao userDao;
    @Autowired
    private PlayerJdbcDao playerDao;
    @Autowired
    private MatchJdbcDao matchDao;
    @Autowired
    private TeamJdbcDao teamDao;
    @Autowired
    private EventJdbcDao eventDao;

    private JdbcTemplate jdbcTemplate;
    private Player player1;
    private Player player2;
    private Match match;
    private Team team;
    private League league;
    private User user;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(ds);
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "users", "league", "team", "player", "match", "event");
        user = userDao.create("c","","", new Date());
        league = leagueDao.create("", 0, user);
        team = teamDao.create("", league, null, null, null, null, 0,0,0,0);
        player1 = playerDao.create("a", team, 0,0,0,0,0,0,0,0,0,0, new Date(), false);
        player2 = playerDao.create("b", team, 0,0,0,0,0,0,0,0,0,0, new Date(), false);
        match = matchDao.create(league, team, team, new Date());
    }

    @Test
    public void testCreate() {
        final Event event = eventDao.create(match, player1, player2, TYPE, MINUTE);
        assertNotNull(event);
        assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "event"));
        assertEquals(player1, event.getP1());
        assertEquals(player2, event.getP2());
        assertEquals(TYPE, event.getType());
        assertEquals(MINUTE, event.getMinute());
    }

    @Test
    public void testFindAllByUserId() {
        final long id = eventDao.create(match, player1, player2, TYPE, MINUTE).getId();
        Event event = eventDao.findByMatchId(match.getId()).get(0);
        assertNotNull(event);
        assertEquals(player1.getId(), event.getP1Id());
        assertEquals(player2.getId(), event.getP2Id());
        assertEquals(TYPE, event.getType());
        assertEquals(MINUTE, event.getMinute());
        assertEquals(id, event.getId());
    }
}