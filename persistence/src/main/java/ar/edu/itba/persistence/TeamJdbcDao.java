package ar.edu.itba.persistence;

import ar.edu.itba.interfaces.dao.TeamDao;
import ar.edu.itba.model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeamJdbcDao implements TeamDao {
    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    private static final RowMapper<Team> ROW_MAPPER = new RowMapper<Team>() {
        @Override
        public Team mapRow(ResultSet rs, int rowNumber) throws SQLException {
            /*
            long teamId = rs.getInt("id");
            String name = rs.getString("name");
            League league = LeagueJdbcDao.findById(rs.getInt("league"));
            Stadium stadium = StadiumJdbcDao.findById(rs.getInt("stadium"));
            Formation formation = FormationJdbcDao.findById(rs.getInt("formation"));
            List<Player> players;
            YouthAcademy youthAcademy = YouthAcademyJdbcDao.findById(rs.getInt("youthAcademy"));
            int fanTrust = rs.getInt("fanTrust");
            int boardTrust = rs.getInt("boardTrust");
            List<Receipt> finance;
            int money = rs.getInt("money");

            return new Team(teamId, name, league, stadium, formation, players, youthAcademy, fanTrust, boardTrust, finance, money);
            */
            return null;
        }
    };

    public TeamJdbcDao(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("team")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Team create(String name, League league, Stadium stadium, Formation formation, List<Player> players, YouthAcademy youthAcademy, Integer fanTrust, Integer boardTrust, List<Receipt> finance, Integer money) {
        final Map<String, Object> args = new HashMap<>();
        args.put("name", name);
        args.put("league", league.getId());
        args.put("stadium", stadium.getId());
        args.put("formation", formation.getId());
        //args.put("players", players);
        args.put("youthAcademy", youthAcademy.getId());
        args.put("fanTrust", fanTrust);
        args.put("boardTrust", boardTrust);
        //args.put("finance", finance);
        args.put("money", money);
        final Number id = jdbcInsert.executeAndReturnKey(args);
        return new Team(id.longValue(), name, league, stadium, formation, players, youthAcademy, fanTrust, boardTrust, finance, money);
    }

    @Override
    public Team findById(final long id) {
        final List<Team> list = jdbcTemplate.query("SELECT * FROM team WHERE id = ?", ROW_MAPPER, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
