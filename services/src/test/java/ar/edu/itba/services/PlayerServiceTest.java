package ar.edu.itba.services;

import ar.edu.itba.interfaces.dao.*;
import ar.edu.itba.interfaces.service.FormationService;
import ar.edu.itba.interfaces.service.LeagueService;
import ar.edu.itba.interfaces.service.PlayerService;
import ar.edu.itba.model.League;
import ar.edu.itba.model.Player;
import ar.edu.itba.model.Team;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class PlayerServiceTest {

    @Configuration
    static class PlayerServiceConfig {

        @Bean
        public PlayerService playerService() {
            return new PlayerServiceImpl();
        }

        @Bean
        public PlayerDao playerDao() {
            return mock(PlayerDao.class);
        }

        @Bean
        public TeamDao teamDao() {
            return mock(TeamDao.class);
        }

    }

    @Autowired
    private PlayerService playerService;

    @Autowired
    private PlayerDao playerDao;

    @Autowired
    private TeamDao teamDao;


    private Team team;
    private Player player1, player2, player3;

    @Before
    public void setUp() {
        List<Player> players = new ArrayList<>();
        player1 = mock(Player.class);
        player2 = mock(Player.class);
        player3 = mock(Player.class);

        players.add(player1);
        players.add(player2);
        players.add(player3);

        team = new Team(1,null,null,null,null,players,null,0,0,0,null,null,0);

        when(player1.getTeam()).thenReturn(team);
        when(player2.getTeam()).thenReturn(team);
        when(player3.getTeam()).thenReturn(team);
    }

    @Test
    public void retire() {
        playerService.retire(player1);

        assertTrue(team.getPlayers().size() == 2);
        assertTrue(!team.getPlayers().contains(player1));
    }
}
