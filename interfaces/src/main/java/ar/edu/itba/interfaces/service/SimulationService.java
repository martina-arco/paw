package ar.edu.itba.interfaces.service;

import ar.edu.itba.model.Event;
import ar.edu.itba.model.League;
import ar.edu.itba.model.Match;

import java.util.List;
import java.util.Map;

public interface SimulationService {

    void simulateFixture(List<Match> matches);

    Map<Integer, List<Event>> getEvents();
}
