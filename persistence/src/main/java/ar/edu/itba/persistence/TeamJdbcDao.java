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
                .usingGeneratedKeyColumns("id");
    }

    private static final RowMapper<Team> ROW_MAPPER = new RowMapper<Team>() {
        @Override
        public Team mapRow(ResultSet rs, int rowNumber) throws SQLException {
            /*
            long id = rs.getInt("id");
            String name = rs.getString("name");
            League league = LeagueJdbcDao.findById(rs.getInt("league"));
            Stadium stadium = StadiumJdbcDao.findById(rs.getInt("stadium"));
            Formation formation = FormationJdbcDao.findById(rs.getInt("formation"));
            List<Player> players = ContractJdbcDao.findPlayersByTeamId(id);
            YouthAcademy youthAcademy = YouthAcademyJdbcDao.findById(rs.getInt("youthAcademy"));
            int fanTrust = rs.getInt("fanTrust");
            int boardTrust = rs.getInt("boardTrust");
            List<Receipt> finance = ReceiptJdbcDao.findReceiptsByTeamId(id);
            private List<BankLoan> loans = nose en que base se guardan;
            int money = rs.getInt("money");

            return new Team(id, name, league, stadium, formation, players, youthAcademy, fanTrust, boardTrust, finance, money);
            */
            return null;
        }
    };



    @Override
    public Team create(String name, League league, Stadium stadium, Formation formation, List<Player> players, List<Player> youthAcademy, Integer fanTrust, Integer boardTrust, Integer money) {
        final Map<String, Object> args = new HashMap<>();
        args.put("name", name);
        args.put("league", league.getId());
        args.put("stadium", stadium.getId());
        args.put("formation", formation.getId());
        args.put("fanTrust", fanTrust);
        args.put("boardTrust", boardTrust);
        args.put("money", money);
        final Number id = jdbcInsert.executeAndReturnKey(args);
        AddContracts(players, id.longValue());

        return new Team(id.longValue(), name, league, stadium, formation, players, youthAcademy, fanTrust, boardTrust, null, null, money);
    }

    private void AddContracts(List<Player> players, long teamId) {
        final Map<String, Object> args = new HashMap<>();
        for(Player player: players) {
            //ContractJdbcDao.create(player, teamId);
        }
    }

    @Override
    public Team findById(final long id) {
        final List<Team> list = jdbcTemplate.query("SELECT * FROM team WHERE id = ?", ROW_MAPPER, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
