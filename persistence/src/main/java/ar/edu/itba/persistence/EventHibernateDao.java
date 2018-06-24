package ar.edu.itba.persistence;

import ar.edu.itba.interfaces.dao.EventDao;
import ar.edu.itba.model.Event;
import ar.edu.itba.model.Match;
import ar.edu.itba.model.Player;
import ar.edu.itba.model.User;
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
public class EventHibernateDao implements EventDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Event> findByMatchId(long id) {
        final TypedQuery<Event> query = em.createQuery("SELECT e FROM Event e WHERE e.match.id = :matchId", Event.class);
        query.setParameter("matchId", id);
        return query.getResultList();
    }

    @Override
    public List<Event> findByMatchAndPlayerId(long matchId, long playerId) {
        final TypedQuery<Event> query = em.createQuery("SELECT e FROM Event e WHERE e.match.id = :matchId " +
                "AND e.p1.id = :playerId", Event.class);
        query.setParameter("matchId", matchId);
        query.setParameter("playerId", playerId);
        return query.getResultList();
    }

    @Override
    public Event create(Match match, Player p1, Player p2, Event.Type type, int minute) {
        final Event event = new Event(match, p1, p2, type, minute);
        em.persist(event);
        return event;
    }

    @Override
    public Event create(long matchId, long p1Id, long p2Id, Event.Type type, int minute) {
        Match match = em.getReference(Match.class, matchId);
        Player p1 = em.getReference(Player.class, p1Id);
        Player p2 = em.getReference(Player.class, p2Id);
        return create(match, p1, p2, type, minute);
    }
}
