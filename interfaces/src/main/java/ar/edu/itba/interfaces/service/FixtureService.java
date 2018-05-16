package ar.edu.itba.interfaces.service;

import ar.edu.itba.model.League;
import ar.edu.itba.model.Match;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface FixtureService {
    Map<Date, List<Match>> generateFixture(League league, Date from);
}
