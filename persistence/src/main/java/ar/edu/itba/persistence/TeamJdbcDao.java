package ar.edu.itba.persistence;

import ar.edu.itba.interfaces.dao.LeagueDao;
import ar.edu.itba.interfaces.dao.TeamDao;
import ar.edu.itba.model.*;
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
public class TeamJdbcDao implements TeamDao {
    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    @Autowired
    public TeamJdbcDao(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("team")
                .usingGeneratedKeyColumns("teamid");
    }

    private static final RowMapper<Team> ROW_MAPPER = new RowMapper<Team>() {
        @Override
        public Team mapRow(ResultSet rs, int rowNumber) throws SQLException {
            long id = rs.getLong("teamid");
            String name = rs.getString("name");
            long league = rs.getLong("league");
            long stadium = rs.getLong("stadium");
            long formation = rs.getLong("formation");
            int fanTrust = rs.getInt("fanTrust");
            int boardTrust = rs.getInt("boardTrust");
            int money = rs.getInt("money");

            return new Team(id, name, league, stadium, formation, fanTrust, boardTrust, money);
        }
    };

    @Override
    public Team create(String name, League league, Stadium stadium, Formation formation, List<Player> players, List<Player> youthAcademy, Integer fanTrust, Integer boardTrust, Integer money) {
        final Map<String, Object> args = new HashMap<>();
        args.put("name", name);
        args.put("league", league.getId());
        args.put("stadium", stadium.getId());
        args.put("formation", formation.getId());
        args.put("fanTrust", fanTrust);
        args.put("boardTrust", boardTrust);
        args.put("money", money);

        final Number id = jdbcInsert.executeAndReturnKey(args);
        return new Team(id.longValue(), name, league, stadium, formation, players, youthAcademy, fanTrust, boardTrust, null, null, money);
    }

    @Override
    public boolean save(Team team) {
        return false;
    }

    @Override
    public Team findById(final long id) {
        final List<Team> list = jdbcTemplate.query("SELECT * FROM team WHERE teamid = ?", ROW_MAPPER, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Team> findAllByLeagueId(long id) {
        final List<Team> list = jdbcTemplate.query("SELECT * FROM team WHERE league = ?", ROW_MAPPER, id);
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }
}
