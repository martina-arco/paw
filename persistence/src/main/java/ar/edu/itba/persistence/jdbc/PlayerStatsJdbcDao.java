package ar.edu.itba.persistence.jdbc;

import ar.edu.itba.interfaces.dao.PlayerStatsDao;
import ar.edu.itba.model.Event;
import ar.edu.itba.model.Match;
import ar.edu.itba.model.Player;
import ar.edu.itba.model.PlayerStats;
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


public class PlayerStatsJdbcDao implements PlayerStatsDao {

    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    private static final RowMapper<PlayerStats> ROW_MAPPER = new RowMapper<PlayerStats>() {
        @Override
        public PlayerStats mapRow(ResultSet rs, int rowNumber) throws SQLException {
            long id = rs.getLong("playerstatsid");
            int matchId = rs.getInt("match");
            int playerId = rs.getInt("player");
            int performance = rs.getInt("performance");
            int saves = rs.getInt("saves");
            int passes = rs.getInt("passes");
            int tackles = rs.getInt("tackles");
            int assists = rs.getInt("assists");
            int scores = rs.getInt("scores");
            int yellowCards = rs.getInt("yellowCards");
            int redCards = rs.getInt("redCards");

            return new PlayerStats(id, matchId, playerId, saves, performance, passes, assists, scores, yellowCards, redCards, tackles);
        }
    };

    /*private static final ResultSetExtractor<List<PlayerStats>> RESULT_SET_EXTRACTOR = new ResultSetExtractor<List<PlayerStats>>() {
        @Override
        public List<PlayerStats> extractData(ResultSet rs) throws SQLException, DataAccessException {
            List<PlayerStats> playerStatsList = new LinkedList<>();
            PlayerStats currPlayerStats = null;
            while (rs.next()) {
                long id = rs.getLong("playerstatsid");
                if(currPlayerStats == null) {
                    currPlayerStats = ROW_MAPPER.mapRow(rs, 0);
                } else if (currPlayerStats.getId() != id) {
                    //set values
                    playerStatsList.add(currPlayerStats);

                    currPlayerStats = ROW_MAPPER.mapRow(rs, 0);
                }

                String type = rs.getString("type");
                if(type != null) {

                }
            }
            return playerStatsList;
        }
    };*/

    @Autowired
    public PlayerStatsJdbcDao(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("playerstats")
                .usingGeneratedKeyColumns("playerstatsid");
    }

    @Override
    public List<PlayerStats> findByMatchId(long id) {
        final List<PlayerStats> list = jdbcTemplate.query("SELECT playerStats.*, " +
                "sum(CASE WHEN type = ? THEN 1 ELSE 0 END) AS scores, sum(CASE WHEN type = ? THEN 1 ELSE 0 END) AS yellowCards, " +
                "sum(CASE WHEN type = ? THEN 1 ELSE 0 END) AS redCards FROM playerStats " +
                "LEFT JOIN event ON playerstats.match = event.match AND player = player1 WHERE playerstats.match = ? " +
                "GROUP BY playerstats.match, player", ROW_MAPPER, Event.Type.AWAYSCORE.toString(), Event.Type.HOMESCORE.toString(), Event.Type.YELLOW_CARD.toString(),
                Event.Type.RED_CARD.toString(), id);
        //final List<PlayerStats> list = jdbcTemplate.query("SELECT * FROM playerStats LEFT JOIN event ON playerstats.match = event.match AND player = player1 WHERE playerstats.match = ? ORDER BY playerstats.match, player", ROW_MAPPER, id);
        return list;
    }

    @Override
    public boolean save(PlayerStats playerStats) {
        jdbcTemplate.update("UPDATE playerstats SET match = ?, player = ?, performance = ?, saves = ?, passes = ?, " +
                "tackles = ?, assists = ? WHERE playerstatsid = ?", playerStats.getMatchId(), playerStats.getPlayerId(),
                playerStats.getPerformance(), playerStats.getSaves(), playerStats.getPasses(), playerStats.getTackles(), playerStats.getAssists(), playerStats.getId());
        return true;
    }

    @Override
    public PlayerStats create(Player p, Match m) {
        PlayerStats playerStats = create(p.getId(),m.getId());
        playerStats.setPlayer(p);
        playerStats.setMatch(m);
        return playerStats;
    }

    @Override
    public PlayerStats create(long p, long m) {
        final Map<String, Object> args = new HashMap<>();

        args.put("match", m);
        args.put("player", p);
        args.put("performance", 0);
        args.put("saves", 0);
        args.put("passes", 0);
        args.put("tackles", 0);
        args.put("assists", 0);

        final Number playerStatsId = jdbcInsert.executeAndReturnKey(args);

        return new PlayerStats(playerStatsId.longValue(), m, p, 0, 0,0,0,0,0,0, 0);
    }
}
