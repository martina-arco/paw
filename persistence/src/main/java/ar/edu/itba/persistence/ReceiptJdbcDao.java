package ar.edu.itba.persistence;

import ar.edu.itba.interfaces.dao.ReceiptDao;
import ar.edu.itba.model.Receipt;
import ar.edu.itba.model.Team;
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
public class ReceiptJdbcDao implements ReceiptDao {

    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    private static final RowMapper<Receipt>  ROW_MAPPER = new RowMapper<Receipt>() {
        @Override
        public Receipt mapRow(ResultSet rs, int i) throws SQLException {

            int id = rs.getInt("id");
            int amount = rs.getInt("amount");
            String type = rs.getString("type");

            return new Receipt(id, amount, Receipt.Type.valueOf(type));
        }
    };

    @Autowired
    public ReceiptJdbcDao(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("receipt")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public List<Receipt> findAllbyTeamId(long id) {
        final List<Receipt> list = jdbcTemplate.query("SELECT * FROM receipt WHERE team = ?", ROW_MAPPER, id);
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public Receipt create(Team team, int amount, Receipt.Type type) {

        final Map<String, Object> args = new HashMap<>();

        args.put("amount", amount);
        args.put("type", type.toString());
        args.put("team", team.getId());

        final Number id = jdbcInsert.executeAndReturnKey(args);
        Receipt r = new Receipt(id.longValue(), amount, type);
        team.addReceipt(r);

        return r;
    }
}
