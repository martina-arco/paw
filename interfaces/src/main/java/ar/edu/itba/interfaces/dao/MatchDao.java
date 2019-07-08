package ar.edu.itba.interfaces.dao;

import ar.edu.itba.model.League;
import ar.edu.itba.model.Match;
import ar.edu.itba.model.Team;

import java.util.Date;
import java.util.List;

public interface MatchDao {

    Match create(League league,Team home, Team away, Date day);

    Match create(long league,long home, long away, Date day);

    boolean save(Match match);

    Match findById(long id);

    List<Match> findByTeamId(long id);

    List<Match> findByTeamIdFromDate(long id, Date date);

    List<Match> findByLeagueId(long id);

    List<Match> findByLeagueIdAndDate(long id, Date date);

    List<Match> findByLeagueIdAndBeforeDate(long id, Date date);

    List<Match> findByLeagueIdAndFromDate(long id, Date date);

    //Match findFirstByLeagueIdAndAfterDate(long id, Date date);
}
