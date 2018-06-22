package ar.edu.itba.interfaces.dao;

import ar.edu.itba.model.Match;
import ar.edu.itba.model.utils.simulation.MatchDeepStatus;

public interface MatchStateDao {

    MatchDeepStatus findByMatch(Match match);

    boolean save(MatchDeepStatus matchDeepStatus);
}
