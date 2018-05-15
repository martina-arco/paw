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

    public SimulationServiceImplTest(){
        Map<Player,Point> map1 = new HashMap<>(), map2 = new HashMap<>();
        List<Player> sub1 = new ArrayList<>(), sub2 = new ArrayList<>();
        for(int i=0;i<36;i++){
            Player aux = new Player(0,0,"pepe"+i,i,10,80,80,20,70,70,70,100,10,new Date(),false);
            players.add(aux);
            if(i < 11)
                map1.put(aux,new Point(i/2+1,i/2+1));
            if(i >= 11 && i <18)
                sub1.add(aux);
            if(i >= 18 && i < 29)
                map2.put(aux,new Point((i-18)/2+1,(i-18)/2));
            if(i >= 29)
                sub2.add(aux);
        }
        f1 = new Formation(0,players.get(0),players.get(1),players.get(2),map1,sub1,50,50);
        f2 = new Formation(1,players.get(23),players.get(21),players.get(20),map2,sub2,50,50);
        for(Map.Entry entry : map1.entrySet()){
            System.out.println(entry.getValue());
        }
        System.out.println();
        for(Map.Entry entry : map2.entrySet()){
            System.out.println(entry.getValue());
        }
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