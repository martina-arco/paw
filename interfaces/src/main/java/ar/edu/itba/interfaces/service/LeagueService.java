package ar.edu.itba.interfaces.service;

import ar.edu.itba.model.League;
import ar.edu.itba.model.User;

import java.util.List;

public interface LeagueService {
    List<League> findByUser(User user);
}
