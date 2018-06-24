package ar.edu.itba.persistence.jdbc;

import ar.edu.itba.model.*;
import ar.edu.itba.persistence.TestConfig;
import ar.edu.itba.persistence.jdbc.*;
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
public class PlayerStatsJdbcDaoTest {

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
    private PlayerStatsJdbcDao playerStatsDao;

    private JdbcTemplate jdbcTemplate;
    private Player player;
    private Match match;
    private Team team;
    private League league;
    private User user;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(ds);
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "users", "league", "team", "player", "match", "playerStats");
        user = userDao.create("c","","", new Date());
        league = leagueDao.create("", 0, user);
        team = teamDao.create("", league, null, null, null, null, 0,0,0,0);
        player = playerDao.create("a", team, 0,0,0,0,0,0,0,0,0,0, new Date(), false);
        match = matchDao.create(league, team, team, new Date());
    }

    @Test
    public void testCreate() {
        final PlayerStats playerStats = playerStatsDao.create(player, match);
        assertNotNull(playerStats);
        assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "playerStats"));
        assertEquals(player, playerStats.getPlayer());
        assertEquals(match, playerStats.getMatch());
    }

    @Test
    public void testFindByMatchId() {
        final long id = playerStatsDao.create(player, match).getId();
        PlayerStats playerStats = playerStatsDao.findByMatchId(match.getId()).get(0);
        assertNotNull(playerStats);
        assertEquals(player.getId(), playerStats.getPlayerId());
        assertEquals(match.getId(), playerStats.getMatchId());
        assertEquals(id, playerStats.getId());
    }

    @Test
    public void testSave() {
        final long id = playerStatsDao.create(player, match).getId();
        PlayerStats playerStats = playerStatsDao.findByMatchId(match.getId()).get(0);
        assertNotNull(playerStats);

        
    }
}