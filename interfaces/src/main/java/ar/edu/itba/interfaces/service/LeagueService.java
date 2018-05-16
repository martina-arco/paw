package ar.edu.itba.interfaces.service;

import ar.edu.itba.model.League;
import ar.edu.itba.model.Match;
import ar.edu.itba.model.Team;
import ar.edu.itba.model.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface LeagueService {
    List<League> findByUser(User user);
    Map<Team, Integer> getTeamPoints(League league, Date currentDate);
    List<Match> findMatchesForDate(League league, Date date);
}
