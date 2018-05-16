package ar.edu.itba.persistence;

import ar.edu.itba.interfaces.dao.LeagueDao;
import ar.edu.itba.model.League;
import ar.edu.itba.model.Match;
import ar.edu.itba.model.MatchDay;
import ar.edu.itba.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class LeagueJdbcDao implements LeagueDao {
    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    @Autowired
    public LeagueJdbcDao(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("league")
                .usingGeneratedKeyColumns("leagueid");
    }

    private static final RowMapper<League> ROW_MAPPER = new RowMapper<League>() {
        @Override
        public League mapRow(ResultSet rs, int rowNumber) throws SQLException {
            long id = rs.getLong("leagueid");
            String name = rs.getString("name");
            int prize = rs.getInt("prize");

            return new League(id, name, prize);
        }
    };

    @Override
    public League create(String name, Map<Date, List<Match>> fixture, int prize, User user) {
        League league = create(name, prize, user);
        league.setFixture(fixture);
        return league;
    }

    @Override
    public League create(String name, int prize, User user) {
        return create(name, prize, user.getId());
    }

    @Override
    public League create(String name, int prize, long user) {
        final Map<String, Object> args = new HashMap<>();
        args.put("name", name);
        args.put("prize", prize);
        args.put("userid", user);

        final Number id = jdbcInsert.executeAndReturnKey(args);
        return new League(id.longValue(), name, prize);
    }

    @Override
    public League findById(long id) {
        final List<League> list = jdbcTemplate.query("SELECT * FROM league WHERE leagueid = ?", ROW_MAPPER, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<League> findAllByUserId(long id) {
        final List<League> list = jdbcTemplate.query("SELECT * FROM league WHERE userid = ?", ROW_MAPPER, id);
        return list;
    }
}
