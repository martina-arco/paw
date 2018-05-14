package ar.edu.itba.interfaces.dao;

import ar.edu.itba.model.League;
import ar.edu.itba.model.Match;
import ar.edu.itba.model.MatchDay;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface LeagueDao {

    League create(String name, Map<Date, List<Match>> fixture, int prize);

    League create(String name, int prize);

    League findById(long id);

    List<League> findAllByUserId(long id);
}
