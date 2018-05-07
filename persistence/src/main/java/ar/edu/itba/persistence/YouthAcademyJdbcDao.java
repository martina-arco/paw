package ar.edu.itba.persistence;

import ar.edu.itba.interfaces.dao.YouthAcademyDao;
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
public class YouthAcademyJdbcDao implements YouthAcademyDao {
    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    private static final RowMapper<YouthAcademy> ROW_MAPPER = new RowMapper<YouthAcademy>() {
        @Override
        public YouthAcademy mapRow(ResultSet rs, int rowNumber) throws SQLException {
            /*
            long id = rs.getInt("id");
            Team team = TeamJdbcDao.findById(rs.getInt("team"));
            List<Player> players = PlayerJdbcDao.findPlayersByYouthAcademyId(id);

            return new YouthAcademy(id, team, players);
            */
            return null;
        }
    };

    @Autowired
    public YouthAcademyJdbcDao(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("youthAcademy")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public YouthAcademy create(Team team, List<Player> players) {
        final Map<String, Object> args = new HashMap<>();
        args.put("team", team.getId());
        //args.put("players", players);
        final Number id = jdbcInsert.executeAndReturnKey(args);
        return new YouthAcademy(id.longValue(), team, players);
    }

    @Override
    public YouthAcademy findById(final long id) {
        final List<YouthAcademy> list = jdbcTemplate.query("SELECT * FROM youthAcademy WHERE id = ?", ROW_MAPPER, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);    }
}
