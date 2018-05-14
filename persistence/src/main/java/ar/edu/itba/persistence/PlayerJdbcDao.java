package ar.edu.itba.persistence;

import ar.edu.itba.interfaces.dao.PlayerDao;
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

@Repository
public class PlayerJdbcDao implements PlayerDao{

    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    private final static RowMapper<Player> ROW_MAPPER = new RowMapper<Player>() {
        @Override
        public Player mapRow(ResultSet rs, int i) throws SQLException {

            int id = rs.getInt("id");
            int age	= rs.getInt("age");
            int value = rs.getInt("value");
            int potential = rs.getInt("potential");
            int skillLevel = rs.getInt("skillLevel");
            int goalkeeping = rs.getInt("player.goalKeeping");
            int finishing = rs.getInt("player.finishing");
            int defending = rs.getInt("defending	");
            int passing	= rs.getInt("player.passing");
            int fitness = rs.getInt("fitness");
            String name = rs.getString("name");
//            Contract contract = dao.findByPlayerId(id);
//
//            return new Player(id, name, age, value, potential, skillLevel, contract, goalkeeping, finishing, defending, passing, fitness);

            return null;
        }
    };

    @Autowired
    public PlayerJdbcDao(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("player")
                .usingGeneratedKeyColumns("playerId");
    }


    @Override
    public Player create(String name, Team team, int age, int value, int potential, int skillLevel, int goalkeeping, int finish,
                         int defending, int passing, int fitness, int salary, Date contractExpiration) {

        final HashMap<String, Object> args = new HashMap<>();

        args.put("name", name);
        args.put("age", age);
        args.put("value", value);
        args.put("potential", potential);
        args.put("skillLevel", skillLevel);
        args.put("goalKeeping", goalkeeping);
        args.put("finishing", finish);
        args.put("defending", defending);
        args.put("passing", passing);
        args.put("fitness", fitness);
        args.put("salary", salary);
        args.put("contractExpiration", contractExpiration);

        final Number id = jdbcInsert.executeAndReturnKey(args);

        return new Player(id.longValue(), team, name, age, value, potential, skillLevel, goalkeeping, finish, defending, passing, fitness, salary, contractExpiration);
    }

    @Override
    public Player findById(long id) {

        //ToDo

        return null;
    }
}
