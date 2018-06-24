package ar.edu.itba.interfaces.service;

import ar.edu.itba.model.Event;
import ar.edu.itba.model.Match;
import ar.edu.itba.model.Player;
import ar.edu.itba.model.Team;
import ar.edu.itba.model.utils.MatchStatus;

import java.util.List;
import java.util.Map;

public interface SimulationService {

    void simulateFixture(Long userId, List<Match> matches);

    void resimulate(Long userId, int minute, Match match, Team team, List<Player> in, List<Player> out);

    Map<Long, MatchStatus> getStatus(Long userId);

    List<Match> getMatches(Long userId);

    boolean started(Match match);
}
