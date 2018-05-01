package ar.edu.itba.persistence;

import ar.edu.itba.interfaces.dao.LeagueDao;
import ar.edu.itba.model.League;
import ar.edu.itba.model.MatchDay;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeagueJdbcDao implements LeagueDao {
    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    public LeagueJdbcDao(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("league")
                .usingGeneratedKeyColumns("id");
    }

    private static final RowMapper<League> ROW_MAPPER = new RowMapper<League>() {
        @Override
        public League mapRow(ResultSet rs, int rowNumber) throws SQLException {
            /*
            long id = rs.getInt("id");
            String name = rs.getString("name");
            List<MatchDay> fixture;
            MatchDay currentMatchDate = MatchDayJdbcDao.findById(rs.getInt("currentMatchDate"));
            return new League(id, name, fixture, currentMatchDate);
            */
            return null;
        }
    };

    @Override
    public League create(String name, List<MatchDay> fixture, MatchDay currentMatchDate) {
        final Map<String, Object> args = new HashMap<>();
        args.put("name", name);
        //args.put("fixture", fixture);
        args.put("currentMatchDate", currentMatchDate.getId());
        final Number id = jdbcInsert.executeAndReturnKey(args);
        return new League(id.longValue(), name, fixture, currentMatchDate);
    }

    @Override
    public League findById(long id) {
        final List<League> list = jdbcTemplate.query("SELECT * FROM league WHERE id = ?", ROW_MAPPER, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
