package ar.edu.itba.persistence.hibernate;

import ar.edu.itba.interfaces.dao.LeagueDao;
import ar.edu.itba.model.League;
import ar.edu.itba.model.Match;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class LeagueHibernateDao implements LeagueDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public League create(String name, Map<Date, List<Match>> fixture, int prize, User user) {
        final League league = new League(name, prize, user);
        em.persist(league);
        league.setFixture(fixture);
        return league;
    }

    @Override
    public League create(String name, int prize, User user) {
        return create(name, null, prize, user);
    }

    @Override
    public League create(String name, int prize, long userId) {
        User user = em.getReference(User.class, userId);
        return create(name, prize, user);
    }

    @Override
    public League findById(long id) {
        return em.find(League.class, id);
    }

    @Override
    public List<League> findAllByUserId(long id) {
        final TypedQuery<League> query = em.createQuery("SELECT l FROM League l WHERE l.user.id = :userId", League.class);
        query.setParameter("userId", id);
        return query.getResultList();
    }
}
