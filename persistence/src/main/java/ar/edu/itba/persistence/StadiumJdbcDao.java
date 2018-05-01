package ar.edu.itba.persistence;

import ar.edu.itba.interfaces.dao.StadiumDao;
import ar.edu.itba.model.Stadium;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StadiumJdbcDao implements StadiumDao {
    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    public StadiumJdbcDao(DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("stadium")
                .usingGeneratedKeyColumns("id");
    }

    private static final RowMapper<Stadium> ROW_MAPPER = new RowMapper<Stadium>() {
        @Override
        public Stadium mapRow(ResultSet rs, int rowNumber) throws SQLException {

            long id = rs.getInt("id");
            String name = rs.getString("name");
            int lowClass = rs.getInt("lowClass");
            int lowClassPrice = rs.getInt("lowClassPrice");
            int mediumClass = rs.getInt("mediumClass");
            int mediumClassPrice = rs.getInt("mediumClassPrice");
            int highClass = rs.getInt("highClass");
            int highClassPrice = rs.getInt("highClassPrice");
            return new Stadium(id, name, lowClass, lowClassPrice, mediumClass, mediumClassPrice, highClass, highClassPrice);
        }
    };

    @Override
    public Stadium create(String name, int lowClass, int lowClassPrice, int mediumClass, int mediumClassPrice, int highClass, int highClassPrice) {
        final Map<String, Object> args = new HashMap<>();
        args.put("name", name);
        args.put("lowClass", lowClass);
        args.put("lowClassPrice", lowClassPrice);
        args.put("mediumClass", mediumClass);
        args.put("mediumClassPrice", mediumClassPrice);
        args.put("highClass", highClass);
        args.put("highClassPrice", highClassPrice);
        final Number id = jdbcInsert.executeAndReturnKey(args);
        return new Stadium(id.longValue(), name, lowClass, lowClassPrice, mediumClass, mediumClassPrice, highClass, highClassPrice);
    }

    @Override
    public Stadium findById(long id) {
        final List<Stadium> list = jdbcTemplate.query("SELECT * FROM stadium WHERE id = ?", ROW_MAPPER, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
