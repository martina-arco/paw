package ar.edu.itba.persistence;

import ar.edu.itba.interfaces.dao.EventDao;
import ar.edu.itba.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by martina on 01/05/2018.
 */
public class EventJdbcDao implements EventDao {

    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    private final static RowMapper<Event> ROW_MAPPER = new RowMapper<Event>() {
        @Override
        public Event mapRow(ResultSet rs, int rowNumber) throws SQLException {

            long id = rs.getInt("id");
            String type =  rs.getString("eventType");
            int minute = rs.getInt("minute");

//            Player p1 = dao.getPlayerById(rs.getInt("player1"));
//            Player p2 = dao.getPlayerById(rs.getInt("player2"));
//
//            return new Event(id, p1, p2, EventType.valueOf(type), minute);
            return null;
        }
    };

    @Autowired
    public EventJdbcDao(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("event")
                .usingGeneratedKeyColumns("id");
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
    public Event create(Match match, Player p1, Player p2, EventType eventType, int minute) {
        final Map<String, Object> args = new HashMap<>();

        args.put("match", match.getId());
        args.put("player1", p1.getId());
        args.put("player2", p2 == null ? null : p2.getId());
        args.put("eventType", eventType.toString());
        args.put("minute", minute);

        final Number eventId = jdbcInsert.executeAndReturnKey(args);

        return match.addEvent(eventId.longValue(), p1, p2, eventType, minute);
    }
}
