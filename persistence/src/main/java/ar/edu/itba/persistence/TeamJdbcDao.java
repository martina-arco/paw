package ar.edu.itba.persistence;

import ar.edu.itba.interfaces.dao.LeagueDao;
import ar.edu.itba.interfaces.dao.TeamDao;
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
public class TeamJdbcDao implements TeamDao {
    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    @Autowired
    public TeamJdbcDao(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("team")
                .usingGeneratedKeyColumns("teamid");
    }

    private static final RowMapper<Team> ROW_MAPPER = new RowMapper<Team>() {
        @Override
        public Team mapRow(ResultSet rs, int rowNumber) throws SQLException {
            long id = rs.getLong("teamid");
            String name = rs.getString("name");
            long league = rs.getLong("league");
            long formation = rs.getLong("formation");
            int fanCount = rs.getInt("fanCount");
            int fanTrust = rs.getInt("fanTrust");
            int boardTrust = rs.getInt("boardTrust");
            int money = rs.getInt("money");

            return new Team(id, league, formation, name, fanCount, fanTrust, boardTrust, money);
        }
    };

    @Override
    public Team create(String name, League league, Stadium stadium, Formation formation, List<Player> players, List<Player> youthAcademy, int fanCount, int fanTrust, int boardTrust, int money) {
        Team team = create(name, league == null ? 0 : league.getId(), formation == null ? 0 : formation.getId(), fanCount, fanTrust, boardTrust, money);
        team.setLeague(league);
        team.setStadium(stadium);
        team.setFormation(formation);
        team.setPlayers(players);
        team.setYouthAcademy(youthAcademy);
        return team;
    }

    @Override
    public Team create(String name, long league, long formation, int fanCount, int fanTrust, int boardTrust, int money) {
        final Map<String, Object> args = new HashMap<>();
        args.put("name", name);
        args.put("league", league == 0 ? null : league);
        args.put("formation", formation == 0 ? null : formation);
        args.put("fanCount", fanCount);
        args.put("fanTrust", fanTrust);
        args.put("boardTrust", boardTrust);
        args.put("money", money);

        final Number id = jdbcInsert.executeAndReturnKey(args);
        return new Team(id.longValue(), league, formation, name, fanCount, fanTrust, boardTrust, money);
    }

    @Override
    public boolean save(Team team) {
        jdbcTemplate.update("UPDATE team SET name = ?, fancount = ?, fantrust = ?, boardtrust = ?, league = ?, " +
                "formation = ? WHERE teamid = ?", team.getName(), team.getFanCount(), team.getFanTrust(),
                team.getBoardTrust(), team.getLeagueId(), team.getFormationId(), team.getId());
        return true;
    }

    @Override
    public Team findById(final long id) {
        final List<Team> list = jdbcTemplate.query("SELECT * FROM team WHERE teamid = ?", ROW_MAPPER, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Team> findAllByLeagueId(long id) {
        final List<Team> list = jdbcTemplate.query("SELECT * FROM team WHERE league = ?", ROW_MAPPER, id);
        return list;
    }

    @Override
    public Team findByUserId(long id) {
        List<Team> list = jdbcTemplate.query("SELECT * FROM team JOIN users ON team.teamid = users.team WHERE userid = ?", ROW_MAPPER, id);
        if(list.isEmpty())
            return null;
        return list.get(0);
    }
}
