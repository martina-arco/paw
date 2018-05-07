package ar.edu.itba.persistence;

import ar.edu.itba.interfaces.dao.ContractDao;
import ar.edu.itba.model.Contract;
import ar.edu.itba.model.Player;
import ar.edu.itba.model.Team;
import ar.edu.itba.model.utils.Date;
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
            int contractLength = rs.getInt("contractLength");

//            Player p = findById(playerid);
//            Team t = findById(teamid);
//
//            return new Contract(id, t, salary, contractLength);

            return null;
        }
    };

    @Autowired
    public ContractJdbcDao(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("contract")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public List<Contract> findByTeam(Team team) {
        final List<Contract> list = jdbcTemplate.query("SELECT * FROM contract WHERE id = ?", ROW_MAPPER, team.getId());
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public Contract create(Team team, Player p, int salary, Date length) {
        final Map<String, Object> args = new HashMap<>();

        args.put("team", team.getId());
        args.put("player", p.getId());
        args.put("salary", salary);
//        args.put("length", length);

        final Number id = jdbcInsert.executeAndReturnKey(args);
//        return new Contract(id.longValue(), team, salary, (Date)length);
        return null;
    }

    @Override
    public Contract findById(long id) {

        //ToDo

        return null;
    }
}
