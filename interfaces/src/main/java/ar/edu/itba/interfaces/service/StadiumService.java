package ar.edu.itba.interfaces.service;

import ar.edu.itba.model.Stadium;
import ar.edu.itba.model.Team;

import java.util.Set;

public interface StadiumService {
    Stadium findByTeam(Team team);
    void setStadium(Set<Team> teams);
}
