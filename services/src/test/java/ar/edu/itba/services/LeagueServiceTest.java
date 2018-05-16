package ar.edu.itba.services;

import ar.edu.itba.interfaces.dao.LeagueDao;
import ar.edu.itba.interfaces.dao.MatchDao;
import ar.edu.itba.interfaces.service.LeagueService;
import ar.edu.itba.model.League;
import ar.edu.itba.model.Match;
import ar.edu.itba.model.Team;
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
    }

    @Autowired
    private LeagueService leagueService;

    @Autowired
    private MatchDao matchDao;

    private League league;
    private Date date;
    private Team home, away;

    @Before
    public void setUp() {
        league = mock(League.class);
        date = mock(Date.class);
        List<Match> matches = new ArrayList<>();

        Match m1 = mock(Match.class);
        Match m2 = mock(Match.class);
        Match m3 = mock(Match.class);

        home = mock(Team.class);
        away = mock(Team.class);

        when(league.getId()).thenReturn((long) 0);

        when(m1.getHome()).thenReturn(home);
        when(m2.getHome()).thenReturn(home);
        when(m3.getHome()).thenReturn(home);

        when(m1.getAway()).thenReturn(away);
        when(m2.getAway()).thenReturn(away);
        when(m3.getAway()).thenReturn(away);

        when(m1.getHomePoints()).thenReturn(0);
        when(m2.getHomePoints()).thenReturn(0);
        when(m3.getHomePoints()).thenReturn(1);

        when(m1.getAwayPoints()).thenReturn(3);
        when(m2.getAwayPoints()).thenReturn(3);
        when(m3.getAwayPoints()).thenReturn(1);

        matches.add(m1);
        matches.add(m2);
        matches.add(m3);

        when(matchDao.findByLeagueIdAndBeforeDate((long) 0, date)).thenReturn(matches);
    }

    @Test
    public void getPoints() {
        Map<Team, Integer> points = leagueService.getTeamPoints(league, date);

        assertTrue(points.get(home) == 1);
        assertTrue(points.get(away) == 7);
    }

}
