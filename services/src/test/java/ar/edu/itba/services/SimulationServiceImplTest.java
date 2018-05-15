package ar.edu.itba.services;

import ar.edu.itba.interfaces.service.SimulationService;
import ar.edu.itba.model.Formation;
import ar.edu.itba.model.Match;
import ar.edu.itba.model.Player;
import ar.edu.itba.model.Team;
import ar.edu.itba.model.utils.Point;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class SimulationServiceImplTest {

    List<Player> players = new ArrayList<>();
    Formation f1,f2;

    public Player dummy(){
        return new Player(0,0,"pepe",20,10,80,80,70,70,70,70,100,10,new Date(),false);
    }

    public SimulationServiceImplTest(){
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
    }


    @Test
    public void influenceTest(){
        SimulationServiceImpl ss = new SimulationServiceImpl();
        Match match = new Match(0,0,1,0,new Date(),0,0,0,0,false);
        List<Match> list = new ArrayList<>();
        list.add(match);
        ss.playMatch(f1,f2);
    }


}