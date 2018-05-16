package ar.edu.itba.services;

import ar.edu.itba.interfaces.dao.TeamDao;
import ar.edu.itba.interfaces.service.FixtureService;
import ar.edu.itba.model.League;
import ar.edu.itba.model.Match;
import ar.edu.itba.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class FixtureServiceImpl implements FixtureService {

    @Autowired
    private TeamDao teamDao;

    @Override
    public Map<Date, List<Match>> generateFixture(League league, Date from){
        List<Team> teams = teamDao.findAllByLeagueId(league.getId());
        Map<Date, List<Match>> map = new HashMap<>();

        Date aux = (Date) from.clone();
        for(Team t1 : teams){
            map.put(aux,new ArrayList<>());
            for(Team t2 : teams){
                if(!t1.equals(t2)){
                    map.get(aux).add(new Match(0,t1,t2,league,aux,0,0,0,0,null,false,null));
                }
            }
            aux = nextWeek(aux);
        }

        return map;
    }

    private Date nextWeek(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 7);
        return cal.getTime();
    }
}
