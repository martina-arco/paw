package ar.edu.itba.interfaces.service;

import ar.edu.itba.model.Event;
import ar.edu.itba.model.Match;
import ar.edu.itba.model.utils.MatchStatus;

import java.util.List;
import java.util.Map;

public interface SimulationService {

    void simulateFixture(Long userId, List<Match> matches);

    Map<Long, MatchStatus> getStatus(Long userId);

    void start(Long userId);

    List<Match> getMatches(Long userId);
}
