package ar.edu.itba.interfaces.dao;

import ar.edu.itba.model.League;
import ar.edu.itba.model.MatchDay;

import java.util.List;

public interface LeagueDao {

    League create(String name, List<MatchDay> fixture, MatchDay currentMatchDate);

    League findById(long id);
}
