package ar.edu.itba.persistence;

import ar.edu.itba.model.League;
import ar.edu.itba.model.Receipt;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Sql("classpath:schema.sql")
public class ReceiptJdbcDaoTest {

    private final int AMOUNT = 41231;
    private final Receipt.Type TYPE = Receipt.Type.MATCHINCOME;

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
    private ReceiptJdbcDao receiptDao;

    private JdbcTemplate jdbcTemplate;
    private Team team;
    private League league;
    private User user;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(ds);
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "users", "league", "team", "receipt");
        user = userDao.create("c","","", new Date());
        league = leagueDao.create("", 0, user);
        team = teamDao.create("", league, null, null, null, null, 0,0,0,0);
    }

    @Test
    public void testCreate() {
        final Receipt receipt = receiptDao.create(team, AMOUNT, TYPE);
        assertNotNull(receipt);
        assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "receipt"));
        assertEquals(AMOUNT, receipt.getAmount());
        assertEquals(TYPE, receipt.getType());
    }

    @Test
    public void testFindAllByTeamId() {
        final long id = receiptDao.create(team, AMOUNT, TYPE).getId();
        Receipt receipt = receiptDao.findAllByTeamId(team.getId()).get(0);
        assertNotNull(receipt);
        assertEquals(AMOUNT, receipt.getAmount());
        assertEquals(TYPE, receipt.getType());
        assertEquals(id, receipt.getId());
    }
}