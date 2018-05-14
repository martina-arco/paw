package ar.edu.itba.interfaces.dao;

import ar.edu.itba.model.Match;
import ar.edu.itba.model.Team;

import java.util.List;

/**
 * Created by martina on 01/05/2018.
 */
public interface MatchDao {

    Match create(Team home, Team away);

    boolean save(Match match);

    List<Match> findByTeam(Team team);
}
