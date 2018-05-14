package ar.edu.itba.persistence;

import ar.edu.itba.interfaces.dao.ContractDao;
import ar.edu.itba.model.Contract;
import ar.edu.itba.model.Player;
import ar.edu.itba.model.Team;
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

@Deprecated
@Repository
public class ContractJdbcDao implements ContractDao{

    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    private final static RowMapper<Contract> ROW_MAPPER = new RowMapper<Contract>() {
        @Override
        public Contract mapRow(ResultSet rs, int i) throws SQLException {

            int id = rs.getInt("id");
            int playerid = rs.getInt("player");
            int teamid = rs.getInt("team");
            int salary = rs.getInt("salary");
            Date expiration = rs.getDate("expiration");

            return new Contract(salary, id, expiration, playerid, teamid);
        }
    };

    @Autowired
    public ContractJdbcDao(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("contract")
                .usingGeneratedKeyColumns("contractid");
    }

    @Override
    public List<Contract> findByTeam(Team team) {
        final List<Contract> list = jdbcTemplate.query("SELECT * FROM contract WHERE team = ?", ROW_MAPPER, team.getId());
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public Contract create(Team team, Player player, int salary, Date expiration) {
        final Map<String, Object> args = new HashMap<>();

        args.put("team", team.getId());
        args.put("player", player.getId());
        args.put("salary", salary);
        args.put("expiration", expiration);

        final Number id = jdbcInsert.executeAndReturnKey(args);
        return new Contract(id.longValue(), salary, expiration, player, team);
    }

    @Override
    public Contract findById(long id) {
        final List<Contract> list = jdbcTemplate.query("SELECT * FROM contract WHERE contractid = ?", ROW_MAPPER, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
