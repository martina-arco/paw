package ar.edu.itba.services;

import ar.edu.itba.interfaces.dao.MatchDao;
import ar.edu.itba.interfaces.dao.MatchStateDao;
import ar.edu.itba.interfaces.service.MatchService;
import ar.edu.itba.model.*;
import ar.edu.itba.model.MatchStatus;
import ar.edu.itba.model.utils.Point;
import ar.edu.itba.model.simulation.Grid;
import ar.edu.itba.model.simulation.GridNode;
import ar.edu.itba.model.simulation.NodeAtt;
import ar.edu.itba.model.simulation.SimulationNode;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static ar.edu.itba.model.simulation.MyTeam.AWAY;
import static ar.edu.itba.model.simulation.MyTeam.HOME;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DaoConfiguration.class, SimulationServiceTest.SimulationServiceConfig.class})
public class SimulationServiceTest {

    @Configuration
    static class SimulationServiceConfig {
        @Bean
        public SimulationServiceImpl simulationServiceImpl(){
            return new SimulationServiceImpl();
        }

        @Bean
        public MatchStateDao matchStateDao(){
            return mock(MatchStateDao.class);
        }

        @Bean
        public MatchDao matchDao(){
            return mock(MatchDao.class);
        }

        @Bean
        public MatchService matchService(){
            return mock(MatchService.class);
        }
    }

    @Autowired
    private SimulationServiceImpl simulationService;

    private List<Player> players;
    private Formation f1,f2;
    private Team t1,t2;
    private List<Event> events;
    private Grid grid;
    private GridNode gridNode;
    private SimulationNode sNode1, sNode2;
    private MatchStatus matchStatus;

    private Player dummy(){
        return new Player(0,0,"pepe",20,10,80,80,70,70,70,70,100,10,new Date(),false);
    }

    @Before
    public void setUp(){
        events = new ArrayList<>();
        players = new ArrayList<>();
        Map<Player,Point> map1 = new HashMap<>(), map2 = new HashMap<>();
        List<Player> sub1 = new ArrayList<>(), sub2 = new ArrayList<>();

        map1.put(dummy(),new Point(0,4));
        map1.put(dummy(),new Point(1,1));
        map1.put(dummy(),new Point(1,3));
        map1.put(dummy(),new Point(1,5));
        map1.put(dummy(),new Point(1,7));
        map1.put(dummy(),new Point(3,4));
        map1.put(dummy(),new Point(4,1));
        map1.put(dummy(),new Point(4,7));
        map1.put(dummy(),new Point(5,4));
        map1.put(dummy(),new Point(7,3));
        map1.put(dummy(),new Point(7,5));


        map2.put(dummy(),new Point(0,4));
        map2.put(dummy(),new Point(1,1));
        map2.put(dummy(),new Point(1,3));
        map2.put(dummy(),new Point(1,5));
        map2.put(dummy(),new Point(1,7));
        map2.put(dummy(),new Point(3,4));
        map2.put(dummy(),new Point(4,1));
        map2.put(dummy(),new Point(4,7));
        map2.put(dummy(),new Point(5,4));
        map2.put(dummy(),new Point(7,3));
        map2.put(dummy(),new Point(7,5));

        for(int i=0;i<7;i++){
            sub1.add(dummy());
            sub2.add(dummy());
        }

        f1 = new Formation(0,dummy(),dummy(),dummy(),map1,sub1,50,50);
        f2 = new Formation(1,dummy(),dummy(),dummy(),map2,sub2,50,50);

        t1 = new Team(0,"River",mock(League.class), mock(Stadium.class),f1,new ArrayList<Player>(), new ArrayList<Player>(),100,100,100,new ArrayList<Receipt>(),new ArrayList<BankLoan>(),100);
        t2 = new Team(1,"Boca",mock(League.class), mock(Stadium.class),f2,new ArrayList<Player>(), new ArrayList<Player>(),100,100,100,new ArrayList<Receipt>(),new ArrayList<BankLoan>(),100);


        grid = mock(Grid.class);
        gridNode = mock(GridNode.class);
        when(gridNode.getAtt(AWAY, NodeAtt.DEF)).thenReturn(95);
        when(gridNode.getAtt(HOME, NodeAtt.DEF)).thenReturn(95);
        when(gridNode.getAtt(HOME, NodeAtt.POSS)).thenReturn(80);
        when(gridNode.getAtt(AWAY, NodeAtt.POSS)).thenReturn(80);

        sNode1 = mock(SimulationNode.class);
        sNode2 = mock(SimulationNode.class);
        when(sNode1.getPossession()).thenReturn(HOME);
        when(sNode2.getPossession()).thenReturn(AWAY);
        matchStatus = mock(MatchStatus.class);
        when(matchStatus.getEvents()).thenReturn(events);
        when(matchStatus.getMinute()).thenReturn(7);
        when(sNode1.getNode()).thenReturn(gridNode);
        when(sNode2.getNode()).thenReturn(gridNode);

        when(grid.getMatch()).thenReturn(mock(Match.class));

        when(sNode1.getGrid()).thenReturn(grid);
        when(sNode2.getGrid()).thenReturn(grid);

        when(sNode1.getGrid().getRand()).thenReturn(new Random());
        when(sNode2.getGrid().getRand()).thenReturn(new Random());

        when(sNode1.whoDidIt()).thenReturn(dummy());
        when(sNode2.whoDidIt()).thenReturn(dummy());

        when(gridNode.getSNode(HOME)).thenReturn(sNode1);
        when(gridNode.getSNode(AWAY)).thenReturn(sNode2);
    }


    @Test
    public void disputeTest(){
        SimulationNode ret = simulationService.dispute(sNode1, matchStatus);
        assertEquals(events, matchStatus.getEvents());
        assertTrue(matchStatus.getEvents().size() == 0 || matchStatus.getEvents().size() == 1);
        if(matchStatus.getEvents().size() == 1){
            assertEquals(Event.Type.TACKLE, matchStatus.getEvents().get(0).getType());
        }
        assertTrue(ret.equals(sNode1) || ret.equals(sNode2));
    }

    @Test
    public void shotTest(){
        when(grid.kickOff(HOME)).thenReturn(sNode1);
        when(grid.kickOff(AWAY)).thenReturn(sNode1);
        when(grid.goalKick(AWAY)).thenReturn(sNode2);
        when(grid.goalKick(AWAY)).thenReturn(sNode2);
        when(sNode1.distanceToGoal()).thenReturn(1);
        when(sNode2.distanceToGoal()).thenReturn(1);
        when(sNode1.getOpGK()).thenReturn(dummy());
        when(sNode2.getOpGK()).thenReturn(dummy());
        when(gridNode.getAtt(HOME, NodeAtt.FIN)).thenReturn(90);
        when(gridNode.getAtt(AWAY, NodeAtt.FIN)).thenReturn(90);

        SimulationNode ret = simulationService.shot(sNode1, matchStatus);

        assertEquals(1, matchStatus.getEvents().size());
        assertEquals(events, matchStatus.getEvents());
        if(events.get(0).getType() == Event.Type.SAVE){
            assertEquals(sNode2, ret);
        } else {
            assertEquals(sNode1, ret);
        }
    }


}