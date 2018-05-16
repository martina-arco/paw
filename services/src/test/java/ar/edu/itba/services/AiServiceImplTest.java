package ar.edu.itba.services;

import ar.edu.itba.interfaces.service.AiService;
import ar.edu.itba.model.Formation;
import ar.edu.itba.model.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AiServiceImplTest {
    private List<Player> players;
    private AiService formationService;

    @Before
    public void setUp() throws Exception {
        formationService = new AiServiceImpl();
        players = new ArrayList<>();

        for (int i = 0; i < 11; i++) {
            Player aux = mock(Player.class);
            when(aux.getAge()).thenReturn(20);
            when(aux.getId()).thenReturn(Long.valueOf(i));
            when(aux.getPassing()).thenReturn(50);
            when(aux.getDefending()).thenReturn(50);
            when(aux.getFitness()).thenReturn(80);
            when(aux.getGoalKeeping()).thenReturn(20);
            when(aux.getFinish()).thenReturn(20);

            if(i == 0) {
                when(aux.getAge()).thenReturn(21);
                when(aux.getGoalKeeping()).thenReturn(80);
            } else if( i > 0 && i < 5){
                when(aux.getDefending()).thenReturn(80 + i);
            } else if( i >= 5 && i < 9 ) {
                when(aux.getPassing()).thenReturn(80 + i);
            } else if (i >= 9 && i < 11){
                when(aux.getFinish()).thenReturn(80 + i);
            }

            players.add(aux);
        }
    }

    @Test
    public void getFormation() {
        Formation formation = formationService.getFormation(players);
        assertTrue(formation.getCaptain().getId() == 0);
        assertTrue( formation.getFreeKickTaker().getId() == 8);
        assertTrue(formation.getPenaltyTaker().getId() == 10);
    }
}