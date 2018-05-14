package ar.edu.itba.interfaces.dao;

import ar.edu.itba.model.League;
import ar.edu.itba.model.MatchDay;

import java.util.List;

public interface LeagueDao {

    League create(String name, List<MatchDay> fixture, MatchDay currentMatchDate);

    boolean saveCurrentMatchDay(League league);

    League findById(long id);

    List<League> findAllByUserId(long id);
}
