package ar.edu.itba.services;

import ar.edu.itba.interfaces.dao.*;
import ar.edu.itba.interfaces.service.*;
import ar.edu.itba.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class MatchServiceTest {

    @Configuration
    static class MatchServiceConfig {
        @Bean
        public MatchService matchService() {
            return new MatchServiceImpl();
        }
        @Bean
        public StadiumDao stadiumDao() {
            return mock(StadiumDao.class);
        }
        @Bean
        public ReceiptDao receiptDao() {
            return mock(ReceiptDao.class);
        }
        @Bean
        public MatchDao matchDao() {
            return mock(MatchDao.class);
        }
        @Bean
        public TeamDao teamDao() {
            return mock(TeamDao.class);
        }
        @Bean
        public UserDao userDao() {
            return mock(UserDao.class);
        }
        @Bean
        public LeagueDao leagueDao() {
            return mock(LeagueDao.class);
        }
        @Bean
        public EventDao eventDao() {
            return mock(EventDao.class);
        }
        @Bean
        public LeagueService leagueService() {
            return mock(LeagueService.class);
        }
        @Bean
        public TeamService teamService() {
            return mock(TeamService.class);
        }
        @Bean
        public AiService aiService() {
            return mock(AiService.class);
        }
        @Bean
        public StadiumService stadiumService() {
            return mock(StadiumService.class);
        }
    }

    @Autowired
    private MatchService matchService;

    @Autowired
    private StadiumDao stadiumDao;

    private User user;
    private Team home1, away1, home2, away2;
    private Match m1, m2;
    private Event event;
    private Stadium stadium;
    private List<Match> matches;

    @Before
    public void setUp() {
        m1 = mock(Match.class);
        home1 = mock(Team.class);
        away1 = mock(Team.class);
        m2 = mock(Match.class);
        home2 = mock(Team.class);
        away2 = mock(Team.class);
        user = mock(User.class);
        stadium = mock(Stadium.class);
        event = mock(Event.class);

        when(m1.getHome()).thenReturn(home1);
        when(m1.getHomeId()).thenReturn((long)0);
        when(user.getTeamId()).thenReturn((long)0);
        when(home1.getId()).thenReturn((long)0);
        when(home1.getMoney()).thenReturn(10000);
        when(stadiumDao.findByTeamId((long)0)).thenReturn(stadium);
        when(stadium.calculateMatchEarnings()).thenReturn(10000);

        when(m1.getAway()).thenReturn(away1);
        when(m1.getAwayId()).thenReturn((long)1);

        matches = new ArrayList<Match>();
        matches.add(m1);
        matches.add(m2);

        when(m2.getHomeId()).thenReturn((long)2);
        when(m1.getAwayId()).thenReturn((long)3);

        List<Event> events = new LinkedList<Event>();
        events.add(event);
        when(m1.getEvents()).thenReturn(events);
        Player p1 = mock(Player.class);
        Player p2 = mock(Player.class);
        when(event.getP1()).thenReturn(p1);
        when(event.getP2()).thenReturn(p2);
        when(event.getType()).thenReturn(Event.Type.SCORE);
        when(event.getMinute()).thenReturn(2);
    }

    @Test
    public void addEarnings() {
        matchService.saveMatches(matches, user);
        Match match = matchService.getUserMatch(matches, user);
        assertTrue(match.equals(m1));
        assertTrue(home1.getMoney() == 10000);
    }
}
