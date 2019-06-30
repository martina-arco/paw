package ar.edu.itba.interfaces.dao;

import ar.edu.itba.model.Match;
import ar.edu.itba.model.MatchDeepStatus;

public interface MatchStateDao {

    MatchDeepStatus create(Match match);

    MatchDeepStatus findByMatch(Match match);

    MatchDeepStatus save(MatchDeepStatus matchDeepStatus);
}