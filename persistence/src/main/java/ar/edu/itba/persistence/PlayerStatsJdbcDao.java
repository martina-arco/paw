package ar.edu.itba.persistence;

import ar.edu.itba.interfaces.dao.PlayerStatsDao;
import ar.edu.itba.model.EventType;
import ar.edu.itba.model.Match;
import ar.edu.itba.model.Player;
import ar.edu.itba.model.PlayerStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PlayerStatsJdbcDao implements PlayerStatsDao {

    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    private static final RowMapper<PlayerStats> ROW_MAPPER = new RowMapper<PlayerStats>() {
        @Override
        public PlayerStats mapRow(ResultSet rs, int rowNumber) throws SQLException {
            int matchId = rs.getInt("match");
            int playerId = rs.getInt("player");
            int performance = rs.getInt("performance");
            int saves = rs.getInt("saves");
            int passes = rs.getInt("passes");
            int tackles = rs.getInt("tackles");
            int assists = rs.getInt("assists");

//            Player p = PlayerJdbcDao.findById(playerId);
//            int scores = EventJdbcDao.findScoreByPlayerId(playerId);
//            int yellowCards = EventJdbcDao.findYellowCardsByPlayerId(playerId);
//            int redCards = EventJdbcDao.findRedCardsByPlayerId(playerId);

//           return new PlayerStats(p, saves, performance, passes, tackles, assists, scores, yellowCards, redCards);
            return null;
        }
    };

    @Autowired
    public PlayerStatsJdbcDao(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("player_stats")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public List<PlayerStats> findByMatch(Match m) {
        return findByMatchId(m.getId());
    }

    @Override
    public List<PlayerStats> findByMatchId(long id) {
        final List<PlayerStats> list = jdbcTemplate.query("SELECT * FROM playerStats WHERE match = ?", ROW_MAPPER, id);
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public void save(Match m, Player p, EventType type, int amount) {

        String typeS = null;

        for (PlayerStats stat : m.getStats()) {

            if(stat.getPlayer().equals(p)) {
                switch (type) {
                    case SAVE:
                        typeS = "saves";
                        stat.addSave(amount);
                        break;
                    case ASSIST:
                        typeS = "assists";
                        stat.addAssist(amount);
                        break;
                    case PASS:
                        typeS = "passes";
                        stat.addPass(amount);
                        break;
                    case TACKLE:
                        typeS = "tackles";
                        stat.addTackle(amount);
                        break;
                    default:
                        break;
                }
            }

        }

        if(typeS == null)
            return;

        jdbcTemplate.update("UPDATE player_stats SET ? = ? WHERE match = ? and player = ?", typeS, amount, m.getId(), p.getId());
    }

    @Override
    public PlayerStats create(Player p, Match m) {

        final Map<String, Object> args = new HashMap<>();

        args.put("match", m.getId());
        args.put("player", p.getId());
        args.put("performance", 0);
        args.put("saves", 0);
        args.put("passes", 0);
        args.put("tackles", 0);
        args.put("assists", 0);

        final Number playerStatsId = jdbcInsert.executeAndReturnKey(args);

        return new PlayerStats(playerStatsId.longValue(), p, 0, 0,0,0,0,0,0, 0);
    }
}
