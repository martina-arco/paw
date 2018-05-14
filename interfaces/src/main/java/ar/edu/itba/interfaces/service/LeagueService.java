package ar.edu.itba.interfaces.service;

import ar.edu.itba.model.League;

public interface LeagueService {
    League findByUserId(long userid);
}
