package ar.edu.itba.persistence;

import ar.edu.itba.interfaces.dao.PlayerDao;
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

@Repository
public class PlayerJdbcDao implements PlayerDao{

    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    private final static RowMapper<Player> ROW_MAPPER = new RowMapper<Player>() {
        @Override
        public Player mapRow(ResultSet rs, int i) throws SQLException {

            long id = rs.getLong("playerid");
            long team = rs.getLong("team");
            String name = rs.getString("name");
            int age	= rs.getInt("age");
            int fitness = rs.getInt("fitness");
            int value = rs.getInt("player.value");
            int potential = rs.getInt("potential");
            int skillLevel = rs.getInt("skillLevel");
            int goalkeeping = rs.getInt("goalKeeping");
            int defending = rs.getInt("defending	");
            int passing	= rs.getInt("passing");
            int finishing = rs.getInt("finishing");
            int salary = rs.getInt("finishing");
            Date contractExpiration = rs.getDate("contractExpiration");
            boolean youth = rs.getBoolean("youth");

            return new Player(id, team, name, age, value, potential, skillLevel, goalkeeping, finishing, defending, passing, fitness, salary, contractExpiration, youth);
        }
    };

    @Autowired
    public PlayerJdbcDao(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("player")
                .usingGeneratedKeyColumns("playerid");
    }


    @Override
    public Player create(String name, Team team, int age, int value, int potential, int skillLevel, int goalkeeping, int finish,
                         int defending, int passing, int fitness, int salary, Date contractExpiration, boolean youth) {

        final HashMap<String, Object> args = new HashMap<>();

        args.put("team", team.getId());
        args.put("name", name);
        args.put("age", age);
        args.put("player.value", value);
        args.put("potential", potential);
        args.put("skillLevel", skillLevel);
        args.put("goalKeeping", goalkeeping);
        args.put("finishing", finish);
        args.put("defending", defending);
        args.put("passing", passing);
        args.put("fitness", fitness);
        args.put("salary", salary);
        args.put("contractExpiration", contractExpiration);
        args.put("youth", youth);

        final Number id = jdbcInsert.executeAndReturnKey(args);

        return new Player(id.longValue(), team, name, age, value, potential, skillLevel, goalkeeping, finish, defending, passing, fitness, salary, contractExpiration, youth);
    }

    @Override
    public boolean save(Player player) {
        jdbcTemplate.update("UPDATE player SET team = ?, name = ?, age = ?, fitness = ?, value = ?, potential = ?, " +
                "skilllevel = ?, goalkeeping = ?, defending = ?, passing = ?, finishing = ?, salary = ?, " +
                "contractexpiration = ?, youth = ? WHERE playerid = ?", player.getTeamId(), player.getName(), player.getAge(),
                player.getFitness(), player.getValue(), player.getPotential(), player.getSkillLevel(), player.getGoalKeeping(),
                player.getDefending(), player.getPassing(), player.getFinish(), player.getSalary(), player.getContractExpiration(),
                player.isYouth(), player.getId());
        return true;
    }

    @Override
    public Player findById(long id) {
        final List<Player> list = jdbcTemplate.query("SELECT * FROM player WHERE playerid = ?", ROW_MAPPER, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Player> findAllByIdIn(List<Long> ids) {
        final List<Player> list = jdbcTemplate.query("SELECT * FROM player WHERE playerid IN ?", ROW_MAPPER, ids);
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<Player> findAllByTeamId(long id) {
        final List<Player> list = jdbcTemplate.query("SELECT * FROM player WHERE team = ? AND youth = FALSE", ROW_MAPPER, id);
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<Player> findYouthByTeamId(long id) {
        final List<Player> list = jdbcTemplate.query("SELECT * FROM player WHERE team = ? AND youth = TRUE", ROW_MAPPER, id);
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public boolean delete(Player player) {
        jdbcTemplate.update("DELETE FROM player WHERE playerid = ?", player.getId());
        return true;
    }
}
