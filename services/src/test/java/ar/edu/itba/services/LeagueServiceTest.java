package ar.edu.itba.services;

import ar.edu.itba.interfaces.dao.LeagueDao;
import ar.edu.itba.interfaces.dao.MatchDao;
import ar.edu.itba.interfaces.dao.TeamDao;
import ar.edu.itba.interfaces.service.LeagueService;
import ar.edu.itba.model.League;
import ar.edu.itba.model.Match;
import ar.edu.itba.model.Team;
import ar.edu.itba.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;
import java.util.Date;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class LeagueServiceTest {

    @Configuration
    static class LeagueServiceConfig {

        @Bean
        public LeagueService leagueService() {
            return new LeagueServiceImpl();
        }

        @Bean
        public MatchDao matchDao() {
            return mock(MatchDao.class);
        }

        @Bean
        public LeagueDao leagueDao() {
            return mock(LeagueDao.class);
        }

        @Bean
        public TeamDao teamDao() {
            return mock(TeamDao.class);
        }
    }

    @Autowired
    private LeagueService leagueService;

    @Autowired
    private MatchDao matchDao;

    @Autowired
    private TeamDao teamDao;

    private League league;
    private Date date;
    private Team t1, t2;
    private User user;
    private Match m1, m2, m3;

    @Before
    public void setUp() {
        league = new League(0,null,1);
        date = new Date();
        user = mock(User.class);
        List<Match> matches = new ArrayList<>();

        m1 = mock(Match.class);
        m2 = mock(Match.class);
        m3 = mock(Match.class);

        t1 = mock(Team.class);
        t2 = mock(Team.class);

        List<Team> teams = new ArrayList<>();

        teams.add(t1);
        teams.add(t2);


        when(m1.getHome()).thenReturn(t1);
        when(m2.getHome()).thenReturn(t1);
        when(m3.getHome()).thenReturn(t2);

        when(m1.getAway()).thenReturn(t2);
        when(m2.getAway()).thenReturn(t2);
        when(m3.getAway()).thenReturn(t1);

        when(m1.getHomePoints()).thenReturn(0);
        when(m2.getHomePoints()).thenReturn(0);
        when(m3.getHomePoints()).thenReturn(1);

        when(m1.getAwayPoints()).thenReturn(3);
        when(m2.getAwayPoints()).thenReturn(3);
        when(m3.getAwayPoints()).thenReturn(1);

        when(m1.getDay()).thenReturn(date);
        when(m2.getDay()).thenReturn(date);
        when(m3.getDay()).thenReturn(date);

        when(user.getCurrentDay()).thenReturn(date);

        matches.add(m1);
        matches.add(m2);
        matches.add(m3);

        when(matchDao.findByLeagueIdAndBeforeDate((long) 0, date)).thenReturn(matches);
        when(matchDao.findByLeagueIdAndFromDate((long) 0, date)).thenReturn(matches);
        when(teamDao.findAllByLeagueId(league.getId())).thenReturn(teams);
    }

    @Test
    public void getPoints() {
        Map<Team, Integer> points = leagueService.getTeamPoints(league, date);

        assertTrue(points.get(t1) == 1);
        assertTrue(points.get(t2) == 7);
        assertTrue(points.size() == 2);
    }

    @Test
    public void fillFixture() {
        leagueService.fillFixture(user, league);

        assertTrue(league.getFixture().get(date).contains(m1));
        assertTrue(league.getFixture().get(date).contains(m2));
        assertTrue(league.getFixture().get(date).contains(m3));
        assertTrue(league.getFixture().get(date).size() == 3);
    }

}
