package ar.edu.itba.interfaces.service;

import ar.edu.itba.model.League;
import ar.edu.itba.model.Match;

import java.util.List;

public interface SimulationService {
    public void simulateFixture(List<Match> matches);
}
