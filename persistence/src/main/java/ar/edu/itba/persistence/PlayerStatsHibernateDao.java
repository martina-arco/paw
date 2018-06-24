package ar.edu.itba.persistence;

import ar.edu.itba.interfaces.dao.PlayerStatsDao;
import ar.edu.itba.model.Event;
import ar.edu.itba.model.Match;
import ar.edu.itba.model.Player;
import ar.edu.itba.model.PlayerStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PlayerStatsHibernateDao implements PlayerStatsDao {

    @PersistenceContext
    private EntityManager em;
    @Override
    public List<PlayerStats> findByMatchId(long id) {
        final TypedQuery<PlayerStats> query = em.createQuery("SELECT ps FROM PlayerStats ps WHERE ps.match.id = :matchId", PlayerStats.class);
        query.setParameter("matchId", id);
        return query.getResultList();
    }

    @Override
    public boolean save(PlayerStats playerStats) {
        em.merge(playerStats);
        return true;
    }

    @Override
    public PlayerStats create(Player p, Match m) {
        final PlayerStats playerStats = new PlayerStats(m, p, 0, 0,0,0,0,0,0, 0);
        em.persist(playerStats);
        return playerStats;
    }

    @Override
    public PlayerStats create(long pId, long mId) {
        Match match = em.getReference(Match.class, mId);
        Player player = em.getReference(Player.class, pId);
        return create(player, match);
    }
}
