package ar.edu.itba.interfaces.service;

import ar.edu.itba.model.*;

import java.util.List;

public interface SimulationService {

    void simulateFixture(Long userId, List<Match> matches);

    void resimulate(Long userId, int minute, Match match, Team team, List<Player> in, List<Player> out);

    List<Match> getMatches(Long leagueId, User user);

    boolean started(Match match);
}
