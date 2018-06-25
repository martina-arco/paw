package ar.edu.itba.interfaces.service;

import ar.edu.itba.model.*;
import ar.edu.itba.model.DTOs.MatchDTO;
import ar.edu.itba.model.utils.MatchStatus;

import java.util.List;
import java.util.Map;

public interface SimulationService {

    void simulateFixture(Long userId, List<Match> matches);

    void resimulate(Long userId, int minute, Match match, Team team, List<Player> in, List<Player> out);

    List<MatchDTO> getMatches(Long leagueId, User user);

    boolean started(Match match);
}
