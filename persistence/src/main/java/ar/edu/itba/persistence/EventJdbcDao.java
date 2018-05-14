package ar.edu.itba.persistence;

import ar.edu.itba.interfaces.dao.EventDao;
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
public class EventJdbcDao implements EventDao {

    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    private final static RowMapper<Event> ROW_MAPPER = new RowMapper<Event>() {
        @Override
        public Event mapRow(ResultSet rs, int rowNumber) throws SQLException {

            long id = rs.getInt("eventid");
            Event.Type type = Event.Type.valueOf(rs.getString("type"));
            int minute = rs.getInt("minute");
            long p1 = rs.getLong("player1");
            long p2 = rs.getLong("player2");

            return new Event(id, p1, p2, type, minute);
        }
    };

    @Autowired
    public EventJdbcDao(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("event")
                .usingGeneratedKeyColumns("eventid");
    }

    @Override
    public List<Event> findByMatchId(long id) {
        final List<Event> list = jdbcTemplate.query("SELECT * FROM event WHERE match = ?", ROW_MAPPER, id);
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }


    @Override
    public Event create(Match match, Player p1, Player p2, Event.Type type, int minute) {
        final Map<String, Object> args = new HashMap<>();

        args.put("match", match.getId());
        args.put("player1", p1.getId());
        args.put("player2", p2 == null ? null : p2.getId());
        args.put("type", type.toString());
        args.put("minute", minute);

        final Number eventId = jdbcInsert.executeAndReturnKey(args);

        return new Event(eventId.longValue(), p1, p2, type, minute);
    }
}
