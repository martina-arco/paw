package ar.edu.itba.interfaces.service;

import ar.edu.itba.model.Team;

import java.util.Date;
import java.util.List;

public interface MatchService {

    List<String> getUpcomingMatches(Team team, Date currentDate);
}
