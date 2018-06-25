package ar.edu.itba.interfaces.service;

import ar.edu.itba.model.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface MatchService {

    List<Match> getUpcomingMatches(Team team, Date currentDate);

    void UserMatchEnd(Match match, User user);

    void saveMatches(List<Match> matches, User user);

    void fetchEvents(Match match);

    Match getUserMatch(List<Match> matches, User user);

    Match create(League league, Team home, Team away, Date day);

    Match create(long league,long home, long away, Date day);

    boolean save(Match match);

    Match findById(long id);

    List<Match> findByTeamId(long id);

    List<Match> findByTeamIdFromDate(long id, Date date);

    List<Match> findByLeagueId(long id);

    List<Match> findByLeagueIdAndDate(long id, Date date);

    List<Match> findByLeagueIdAndBeforeDate(long id, Date date);

    void getScores(Match match, Map<String, Integer> homeScores, Map<String, Integer> awayScores);

    void setTeamsAndFormations(List<Match> matches);

    void setTeams(List<Match> matches);
}
