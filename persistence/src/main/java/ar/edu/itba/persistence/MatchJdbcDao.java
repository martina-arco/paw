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
            return new Match(rs.getLong("matchid"), rs.getLong("home"), rs.getLong("away"), rs.getInt("homeScore"),
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
    public List<Match> findByTeamId(long id) {
        final List<Match> list = jdbcTemplate.query("SELECT * FROM match WHERE home = ? OR away = ?", ROW_MAPPER, id, id);
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public Match create(Team home, Team away) {
        final Map<String, Object> args = new HashMap<>();

        args.put("home", home.getName());
        args.put("away", away.getName());
        args.put("league", home.getLeague());

        final Number matchId = jdbcInsert.executeAndReturnKey(args);
        return new Match(matchId.longValue(), home, away, 0,0,0,0, null, false, null);
    }

    @Override
    public boolean save(Match match) {
        return false;
    }
}
