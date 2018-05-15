package ar.edu.itba.interfaces.service;

import ar.edu.itba.model.League;
import ar.edu.itba.model.Team;
import ar.edu.itba.model.User;
import ar.edu.itba.model.utils.Date;

import java.util.List;
import java.util.Map;

public interface LeagueService {
    List<League> findByUser(User user);
    Map<Team, Integer> getTeamPoints(League league, java.util.Date currentDate);
}
