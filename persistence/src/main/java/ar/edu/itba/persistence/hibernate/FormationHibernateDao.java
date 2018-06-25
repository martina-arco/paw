package ar.edu.itba.persistence.hibernate;

import ar.edu.itba.interfaces.dao.FormationDao;
import ar.edu.itba.model.Formation;
import ar.edu.itba.model.Player;
import ar.edu.itba.model.utils.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Repository
public class FormationHibernateDao implements FormationDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Formation create(Map<Player, Point> starters, List<Player> substitutes, int pressure, int attitude, Player captain, Player freeKickTaker, Player penaltyTaker) {
        final Formation formation =  new Formation(captain, freeKickTaker, penaltyTaker, starters, substitutes, pressure, attitude);
        em.persist(formation);
        return formation;
    }

    @Override
    public Formation create(Map<Long, Point> startersIds, List<Long> substitutesIds, int pressure, int attitude, long captainId, long freeKickTakerId, long penaltyTakerId) {
        Player captain = em.getReference(Player.class, captainId);
        Player freeKickTaker = em.getReference(Player.class, freeKickTakerId);
        Player penaltyTaker = em.getReference(Player.class, penaltyTakerId);
        Map<Player, Point> starters = new HashMap<>();
        for(Map.Entry<Long, Point> entry : startersIds.entrySet()) {
            starters.put(em.getReference(Player.class, entry.getKey()), entry.getValue());
        }
        List<Player> substitutes = new LinkedList<>();
        for(Long id : substitutesIds) {
            substitutes.add(em.getReference(Player.class, id));
        }
        return create(starters, substitutes, pressure, attitude, captain, freeKickTaker, penaltyTaker);
    }

    @Override
    public Formation save(Formation formation) {
        return em.merge(formation);
    }

    @Override
    public Formation findById(long id) {
        return em.find(Formation.class, id);
    }
}
