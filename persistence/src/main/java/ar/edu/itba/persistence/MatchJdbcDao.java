package ar.edu.itba.persistence;

import ar.edu.itba.interfaces.dao.MatchDao;
import ar.edu.itba.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by martina on 30/04/2018.
 */
public class MatchJdbcDao implements MatchDao{

    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    private final static RowMapper<Match> ROW_MAPPER = new RowMapper<Match>() {
        @Override
        public Match mapRow(ResultSet rs, int rowNum) throws SQLException {

            long id = rs.getInt("id");

//            Team home = findTeamById(rs.getInt(home));
//            Team away = findTeamById(rs.getInt(away));

            Integer homeScore = rs.getInt("homeScore");
            Integer awayScore = rs.getInt("awayScore");
            Integer homePoints = rs.getInt("homePoints");
            Integer awayPoints = rs.getInt("awayPoints");

            boolean played = homePoints == 0 && awayPoints == 0 ? false : true;

//            List<Event> events = dao.findByMatchId(id);
//
//            List<PlayerStats> stats = dao.findByMatchId(id);
//
//            Map<Player, PlayerStats> map = new HashMap<>();
//
//            for (PlayerStats stat: stats) {
//                map.put(stat.getPlayer(), stat);
//            }
//
//            return new Match(home, away, homeScore, awayScore, homePoints, awayPoints, stats, played, events);

            return null;
        }
    };

    @Autowired
    public MatchJdbcDao(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("match")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public List<Match> findByTeam(Team team) {
        final List<Match> list = jdbcTemplate.query("SELECT * FROM match WHERE home = ? OR away = ?", ROW_MAPPER, team.getId(), team.getId());
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
        args.put("homeScore", 0);
        args.put("awayScore", 0);
        args.put("league", home.getLeague().toString());
        args.put("homePoints", 0);
        args.put("awayPoints", 0);

        final Number matchId = jdbcInsert.executeAndReturnKey(args);

        Match match = new Match(matchId.longValue(), home, away, 0,0,0,0, new HashMap<Player, PlayerStats>(), false, new ArrayList<Event>());

//        DataSource ds;
//
//        for (Player p : home.getFormation().getPlayers()) {
//            PlayerStatsJdbcDao dao = new PlayerStatsJdbcDao(ds);
//            match.addStats(dao.create(p, match));
//        }
//
//        for (Player p : away.getFormation().getPlayers()) {
//            PlayerStatsJdbcDao dao = new PlayerStatsJdbcDao(ds);
//            match.addStats(dao.create(p, match));
//        }

        return match;
    }
}
