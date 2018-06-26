package ar.edu.itba.persistence.hibernate;

import ar.edu.itba.model.League;
import ar.edu.itba.model.Match;
import ar.edu.itba.model.Team;
import ar.edu.itba.model.User;
import ar.edu.itba.model.utils.Point;
import ar.edu.itba.model.utils.simulation.MatchDeepStatus;
import ar.edu.itba.model.utils.simulation.MyTeam;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JPAConfiguration.class)
@Sql("classpath:schema.sql")
@Transactional
public class MatchStateHibernateDaoTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private MatchStateHibernateDao matchStateDao;

    @Autowired
    private UserHibernateDao userDao;

    @Autowired
    private LeagueHibernateDao leagueDao;

    @Autowired
    private MatchHibernateDao matchDao;

    @Autowired
    private TeamHibernateDao teamDao;

    private League league;
    private Team team1, team2;
    private Match match;
    private User user;
    private Point point;
    private MyTeam possession;
    private int minute;

    @Before
    public void setUp(){
        em.createQuery("DELETE FROM User").executeUpdate();
        user = userDao.create("c","","", new Date());
        league = leagueDao.create("", 0, user);
        team1 = teamDao.create("", league, null, null, null, null, 0,0,0,0);
        team2 = teamDao.create("", league, null, null, null, null, 0,0,0,0);
        match = matchDao.create(league, team1, team2, new Date());
    }

    @Test
    public void testCreate(){
        final MatchDeepStatus matchDeepStatus = matchStateDao.create(match);
        assertNotNull(matchDeepStatus);
        assertEquals(match.getId(),matchDeepStatus.getMatch().getId());
    }
}