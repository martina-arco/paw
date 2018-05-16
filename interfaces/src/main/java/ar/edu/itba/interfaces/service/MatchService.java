package ar.edu.itba.interfaces.service;

import ar.edu.itba.model.League;
import ar.edu.itba.model.Match;
import ar.edu.itba.model.Team;
import ar.edu.itba.model.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface MatchService {

    List<String> getUpcomingMatches(Team team, Date currentDate);

    void UserMatchEnd(Match match, User user);

    void FinishMatches(List<Match> matches);

    Match getUserMatch(List<Match> matches, Team userTeam);

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

}
