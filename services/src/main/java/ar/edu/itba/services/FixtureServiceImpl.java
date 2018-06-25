package ar.edu.itba.services;

import ar.edu.itba.interfaces.dao.MatchDao;
import ar.edu.itba.interfaces.dao.TeamDao;
import ar.edu.itba.interfaces.service.FixtureService;
import ar.edu.itba.model.League;
import ar.edu.itba.model.Match;
import ar.edu.itba.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
@Service
@Transactional
public class FixtureServiceImpl implements FixtureService {

    @Autowired
    private TeamDao teamDao;

    @Autowired
    private MatchDao matchDao;

    @Override
    public Map<Date, List<Match>> generateFixture(League league, Date from){
        List<Team> teams = teamDao.findAllByLeagueId(league.getId());
        Map<Date, List<Match>> map = new HashMap<>();
        int N = teams.size();

        Date firstHalf, secondHalf;

        firstHalf = from;
        secondHalf = advanceWeeks(from, N - 1);

        for (int i = 0; i < N - 1; i++) {
            List<Match> firstHalfList = new ArrayList<>();
            List<Match> secondHalfList = new ArrayList<>();

            for (int j = 0; j < N/2; j++) {
                Match first = matchDao.create(league, teams.get(j), teams.get(N/2 + j), firstHalf);
                Match second = matchDao.create(league, teams.get(j + N/2), teams.get(j), secondHalf);
                firstHalfList.add(first);
                secondHalfList.add(second);
            }

            map.put(firstHalf, firstHalfList);
            map.put(secondHalf, secondHalfList);

            firstHalf = nextWeek(firstHalf);
            secondHalf = nextWeek(secondHalf);

            teams = RRrotation(teams);

        }
        return map;
    }

    private List<Team> RRrotation(List<Team> teams){
        Team first[] = new Team[teams.size()/2], second[] = new Team[teams.size()/2];
        List<Team> ret = new ArrayList<>();
        first[0] = teams.get(0);

        for (int i = 1; i < teams.size()/2 - 1; i++) {
            first[i + 1] = teams.get(i);
            second[i - 1] = teams.get(i + teams.size()/2);
        }

        second[teams.size()/2 - 2] = teams.get(teams.size() - 1);
        first[1] = teams.get(teams.size()/2);
        second[teams.size()/2 - 1] = teams.get(teams.size()/2 - 1);

        for (int i = 0; i < teams.size()/2; i++) {
            ret.add(first[i]);
        }

        for (int i = 0; i < teams.size()/2; i++) {
            ret.add(second[i]);
        }

        return ret;
    }

    private Date advanceWeeks(Date date, int amount){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 7 * amount);
        return cal.getTime();
    }

    private Date nextWeek(Date date){
        return advanceWeeks(date, 1);
    }
}
