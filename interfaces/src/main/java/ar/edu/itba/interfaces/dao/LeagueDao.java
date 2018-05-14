package ar.edu.itba.interfaces.dao;

import ar.edu.itba.model.League;
import ar.edu.itba.model.Match;
import ar.edu.itba.model.MatchDay;
import ar.edu.itba.model.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface LeagueDao {

    League create(String name, Map<Date, List<Match>> fixture, int prize, User user);

    League create(String name, int prize, User user);

    League findById(long id);

    List<League> findAllByUserId(long id);
}
