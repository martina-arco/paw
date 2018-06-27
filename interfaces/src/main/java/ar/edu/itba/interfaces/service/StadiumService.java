package ar.edu.itba.interfaces.service;

import ar.edu.itba.model.Match;
import ar.edu.itba.model.Stadium;
import ar.edu.itba.model.Team;

import java.util.List;
import java.util.Set;

public interface StadiumService {
    Stadium findByTeam(Team team);
    void setStadium(Set<Team> teams);
    void setStadium(List<Match> mathes);
    boolean upgradeStadium(Team team, int lowClass, int mediumClass, int highClass);
}
