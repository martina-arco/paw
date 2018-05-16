package ar.edu.itba.interfaces.service;

import ar.edu.itba.model.Stadium;
import ar.edu.itba.model.Team;

public interface StadiumService {
    Stadium findByTeam(Team team);
}
