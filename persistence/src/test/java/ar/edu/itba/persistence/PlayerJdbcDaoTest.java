package ar.edu.itba.persistence;

import ar.edu.itba.model.League;
import ar.edu.itba.model.Player;
import ar.edu.itba.model.Team;
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
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Sql("classpath:schema.sql")
public class PlayerJdbcDaoTest {

    private final String NAME = "Pepe";
    private final int AGE = 22;
    private final int FITNESS = 123;
    private final int VALUE = 435;
    private final int POTENTIAL = 23;
    private final int SKILLLEVEL = 32;
    private final int GOALKEEPING = 87;
    private final int DEFENDING = 56;
    private final int PASSING = 98;
    private final int FINISHING = 67;
    private final int SALARY = 18903;
    private final Date CONTRACT_EXPIRATION = new Date(2000, 8, 15);
    private final boolean YOUTH = true;

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
    private Team team;
    private League league;
    private User user;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(ds);
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "users", "league", "team", "player");
        user = userDao.create("c","","", new Date());
        league = leagueDao.create("", 0, user);
        team = teamDao.create("", league, null, null, null, null, 0,0,0,0);
    }

    @Test
    public void testCreate() {
        final Player player = playerDao.create(NAME, team, AGE, VALUE, POTENTIAL, SKILLLEVEL, GOALKEEPING, FINISHING,
                DEFENDING, PASSING, FITNESS, SALARY, CONTRACT_EXPIRATION, YOUTH);
        assertNotNull(player);
        assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "player"));
        assertEquals(NAME, player.getName());
        assertEquals(team, player.getTeam());
        assertEquals(AGE, player.getAge());
        assertEquals(VALUE, player.getValue());
        assertEquals(POTENTIAL, player.getPotential());
        assertEquals(SKILLLEVEL, player.getSkillLevel());
        assertEquals(GOALKEEPING, player.getGoalKeeping());
        assertEquals(FINISHING, player.getFinish());
        assertEquals(DEFENDING, player.getDefending());
        assertEquals(PASSING, player.getPassing());
        assertEquals(FITNESS, player.getFitness());
        assertEquals(SALARY, player.getSalary());
        assertEquals(CONTRACT_EXPIRATION, player.getContractExpiration());
        assertEquals(YOUTH, player.isYouth());
    }

    @Test
    public void testFindById() {
        final long id = playerDao.create(NAME, team, AGE, VALUE, POTENTIAL, SKILLLEVEL, GOALKEEPING, FINISHING,
                DEFENDING, PASSING, FITNESS, SALARY, CONTRACT_EXPIRATION, YOUTH).getId();
        Player player = playerDao.findById(id);
        assertNotNull(player);
        assertEquals(NAME, player.getName());
        assertEquals(team.getId(), player.getTeamId());
        assertEquals(AGE, player.getAge());
        assertEquals(VALUE, player.getValue());
        assertEquals(POTENTIAL, player.getPotential());
        assertEquals(SKILLLEVEL, player.getSkillLevel());
        assertEquals(GOALKEEPING, player.getGoalKeeping());
        assertEquals(FINISHING, player.getFinish());
        assertEquals(DEFENDING, player.getDefending());
        assertEquals(PASSING, player.getPassing());
        assertEquals(FITNESS, player.getFitness());
        assertEquals(SALARY, player.getSalary());
        assertEquals(CONTRACT_EXPIRATION, player.getContractExpiration());
        assertEquals(YOUTH, player.isYouth());
        assertEquals(id, player.getId());
    }

    @Test
    public void testFindAllByIdIn() {
        final long id = playerDao.create(NAME, team, AGE, VALUE, POTENTIAL, SKILLLEVEL, GOALKEEPING, FINISHING,
                DEFENDING, PASSING, FITNESS, SALARY, CONTRACT_EXPIRATION, YOUTH).getId();
        List<Long> ids = new LinkedList<>();
        ids.add(id);
        ids.add(7L);
        ids.add(54L);
        Player player = playerDao.findAllByIdIn(ids).get(0);
        assertNotNull(player);
        assertEquals(NAME, player.getName());
        assertEquals(team.getId(), player.getTeamId());
        assertEquals(AGE, player.getAge());
        assertEquals(VALUE, player.getValue());
        assertEquals(POTENTIAL, player.getPotential());
        assertEquals(SKILLLEVEL, player.getSkillLevel());
        assertEquals(GOALKEEPING, player.getGoalKeeping());
        assertEquals(FINISHING, player.getFinish());
        assertEquals(DEFENDING, player.getDefending());
        assertEquals(PASSING, player.getPassing());
        assertEquals(FITNESS, player.getFitness());
        assertEquals(SALARY, player.getSalary());
        assertEquals(CONTRACT_EXPIRATION, player.getContractExpiration());
        assertEquals(YOUTH, player.isYouth());
        assertEquals(id, player.getId());
    }

    @Test
    public void testFindAdultsByTeamId() {
        final long id = playerDao.create(NAME, team, AGE, VALUE, POTENTIAL, SKILLLEVEL, GOALKEEPING, FINISHING,
                DEFENDING, PASSING, FITNESS, SALARY, CONTRACT_EXPIRATION, false).getId();
        Player player = playerDao.findAdultsByTeamId(team.getId()).get(0);
        assertNotNull(player);
        assertEquals(NAME, player.getName());
        assertEquals(team.getId(), player.getTeamId());
        assertEquals(AGE, player.getAge());
        assertEquals(VALUE, player.getValue());
        assertEquals(POTENTIAL, player.getPotential());
        assertEquals(SKILLLEVEL, player.getSkillLevel());
        assertEquals(GOALKEEPING, player.getGoalKeeping());
        assertEquals(FINISHING, player.getFinish());
        assertEquals(DEFENDING, player.getDefending());
        assertEquals(PASSING, player.getPassing());
        assertEquals(FITNESS, player.getFitness());
        assertEquals(SALARY, player.getSalary());
        assertEquals(CONTRACT_EXPIRATION, player.getContractExpiration());
        assertEquals(false, player.isYouth());
        assertEquals(id, player.getId());
    }

    @Test
    public void testFindYouthByTeamId() {
        final long id = playerDao.create(NAME, team, AGE, VALUE, POTENTIAL, SKILLLEVEL, GOALKEEPING, FINISHING,
                DEFENDING, PASSING, FITNESS, SALARY, CONTRACT_EXPIRATION, true).getId();
        Player player = playerDao.findYouthByTeamId(team.getId()).get(0);
        assertNotNull(player);
        assertEquals(NAME, player.getName());
        assertEquals(team.getId(), player.getTeamId());
        assertEquals(AGE, player.getAge());
        assertEquals(VALUE, player.getValue());
        assertEquals(POTENTIAL, player.getPotential());
        assertEquals(SKILLLEVEL, player.getSkillLevel());
        assertEquals(GOALKEEPING, player.getGoalKeeping());
        assertEquals(FINISHING, player.getFinish());
        assertEquals(DEFENDING, player.getDefending());
        assertEquals(PASSING, player.getPassing());
        assertEquals(FITNESS, player.getFitness());
        assertEquals(SALARY, player.getSalary());
        assertEquals(CONTRACT_EXPIRATION, player.getContractExpiration());
        assertEquals(true, player.isYouth());
        assertEquals(id, player.getId());
    }

    @Test
    public void testDelete() {
        final Player player1 = playerDao.create(NAME, team, AGE, VALUE, POTENTIAL, SKILLLEVEL, GOALKEEPING, FINISHING,
                DEFENDING, PASSING, FITNESS, SALARY, CONTRACT_EXPIRATION, YOUTH);
        final Player player2 = playerDao.create(NAME, team, AGE, VALUE, POTENTIAL, SKILLLEVEL, GOALKEEPING, FINISHING, DEFENDING, PASSING,
                FITNESS, SALARY, CONTRACT_EXPIRATION, YOUTH);

        assertNotNull(player1);
        assertNotNull(player2);
        assertEquals(2, JdbcTestUtils.countRowsInTable(jdbcTemplate, "player"));

        playerDao.delete(player1);
        assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "player"));

        assertNotNull(playerDao.findById(player2.getId()));
    }
}