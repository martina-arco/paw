package ar.edu.itba.persistence.hibernate;

import ar.edu.itba.interfaces.dao.PlayerDao;
import ar.edu.itba.model.Player;
import ar.edu.itba.model.Team;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Repository
public class PlayerHibernateDao implements PlayerDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Player create(String name, Team team, int age, int value, int potential, int skillLevel, int goalkeeping,
                         int finishing, int defending, int passing, int fitness, int salary, Date contractExpiration, boolean youth) {
            final Player player = new Player(team, name, age, value, potential, skillLevel, goalkeeping, finishing,
                    defending, passing, fitness, salary, contractExpiration, youth);
            em.persist(player);
            return player;
        }

    @Override
    public Player create(String name, long teamId, int age, int value, int potential, int skillLevel, int goalkeeping,
                         int finishing, int defending, int passing, int fitness, int salary, Date contractExpiration, boolean youth) {
        Team team = em.getReference(Team.class, teamId);
        return create(name, team, age, value, potential, skillLevel, goalkeeping, finishing, defending, passing, fitness,
                salary, contractExpiration, youth);
    }

    @Override
    public boolean save(Player player) {
        em.merge(player);
        return true;
    }

    @Override
    public Player findById(long id) {
        return em.find(Player.class, id);
    }

    @Override
    public List<Player> findAllByIdIn(List<Long> ids) {
        final TypedQuery<Player> query = em.createQuery("SELECT p FROM Player p WHERE p.id IN (:ids)", Player.class);
        query.setParameter("ids", ids);
        return query.getResultList();
    }

    @Override
    public List<Player> findAdultsByTeamId(long id) {
        final TypedQuery<Player> query = em.createQuery("SELECT p FROM Player p WHERE p.team.id = :teamId " +
                "AND p.youth = FALSE", Player.class);
        query.setParameter("teamId", id);
        return query.getResultList();
    }

    @Override
    public List<Player> findYouthByTeamId(long id) {
        final TypedQuery<Player> query = em.createQuery("SELECT p FROM Player p WHERE p.team.id = :teamId " +
                "AND p.youth = TRUE", Player.class);
        query.setParameter("teamId", id);
        return query.getResultList();
    }

    @Override
    public boolean delete(Player player) {
        em.remove(player);
        return true;
    }
}
