package ar.edu.itba.persistence;

import ar.edu.itba.interfaces.dao.StadiumDao;
import ar.edu.itba.model.Stadium;
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
public class StadiumJdbcDao implements StadiumDao {
    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    @Autowired
    public StadiumJdbcDao(DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("stadium")
                .usingGeneratedKeyColumns("stadiumid");
    }

    private static final RowMapper<Stadium> ROW_MAPPER = new RowMapper<Stadium>() {
        @Override
        public Stadium mapRow(ResultSet rs, int rowNumber) throws SQLException {

            long id = rs.getInt("stadiumid");
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
    public boolean save(Stadium stadium) {
        jdbcTemplate.update("UPDATE stadium SET lowclass = ?, lowclassprice = ?, mediumclass = ?, mediumclassprice = ?," +
                " highclass = ?, highclassprice = ?, name = ? WHERE stadiumid = ?", stadium.getLowClass(),
                stadium.getLowClassPrice(), stadium.getMediumClass(), stadium.getMediumClassPrice(), stadium.getHighClass(),
                stadium.getHighClassPrice(), stadium.getName(), stadium.getId());
        return true;
    }

    @Override
    public Stadium findById(long id) {
        final List<Stadium> list = jdbcTemplate.query("SELECT * FROM stadium WHERE stadiumid = ?", ROW_MAPPER, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
