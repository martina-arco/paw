package ar.edu.itba.persistence;

import ar.edu.itba.interfaces.dao.EventDao;
import ar.edu.itba.interfaces.dao.MatchDao;
import ar.edu.itba.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class MatchJdbcDao implements MatchDao{

    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    private final static RowMapper<Match> ROW_MAPPER = new RowMapper<Match>() {
        @Override
        public Match mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Match(rs.getLong("matchid"), rs.getLong("home"), rs.getLong("away"),
                    rs.getLong("league"), rs.getDate("day"), rs.getInt("homeScore"),
                    rs.getInt("awayScore"), rs.getInt("homePts"), rs.getInt("awayPts"),
                    rs.getBoolean("played"));
        }
    };

    @Autowired
    public MatchJdbcDao(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("match")
                .usingGeneratedKeyColumns("matchid");
    }

    @Override
    public Match findById(long id) {
        final List<Match> list = jdbcTemplate.query("SELECT * FROM match WHERE matchid = ?", ROW_MAPPER, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Match> findByTeamId(long id) {
        final List<Match> list = jdbcTemplate.query("SELECT * FROM match WHERE home = ? OR away = ?", ROW_MAPPER, id, id);
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<Match> findByTeamIdFromDate(long id, Date date) {
        final List<Match> list = jdbcTemplate.query("SELECT * FROM match WHERE (home = ? OR away = ?) AND day >= ?", ROW_MAPPER, id, id, date);
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<Match> findByLeagueId(long id) {
        final List<Match> list = jdbcTemplate.query("SELECT * FROM match WHERE league = ?", ROW_MAPPER, id);
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<Match> findByLeagueIdAndDate(long id, Date date) {
        final List<Match> list = jdbcTemplate.query("SELECT * FROM match WHERE league = ? AND day = ?", ROW_MAPPER, id, date);
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public Match create(League league, Team home, Team away, Date day) {
        Match match = create(league.getId(), home.getId(), away.getId(), day);
        match.setLeague(league);
        match.setHome(home);
        match.setAway(away);
        return match;
    }

    @Override
    public Match create(long league, long home, long away, Date day) {
        final Map<String, Object> args = new HashMap<>();

        args.put("home", home);
        args.put("away", away);
        args.put("league", league);
        args.put("day", day);

        final Number matchId = jdbcInsert.executeAndReturnKey(args);
        return new Match(matchId.longValue(), home, away, league, day, 0,0,0,0, false);
    }

    @Override
    public boolean save(Match match) {
        jdbcTemplate.update("UPDATE match SET day = ?, home = ?, away = ?, league = ?, played = ?, homescore = ?, " +
                "awayscore = ?, homepts = ?, awaypts = ? WHERE matchid = ?", match.getDay(), match.getHomeId(), match.getAwayId(),
                match.getLeagueId(), match.isPlayed(), match.getHomeScore(), match.getAwayScore(), match.getHomePoints(),
                match.getAwayPoints(), match.getId());

        return true;
    }
}
