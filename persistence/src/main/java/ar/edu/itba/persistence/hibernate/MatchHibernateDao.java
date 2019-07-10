package ar.edu.itba.persistence.hibernate;

import ar.edu.itba.interfaces.dao.MatchDao;
import ar.edu.itba.model.League;
import ar.edu.itba.model.Match;
import ar.edu.itba.model.Team;
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
public class MatchHibernateDao implements MatchDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Match findById(long id) {
        return em.find(Match.class, id);
    }

    @Override
    public List<Match> findByTeamId(long id) {
        final TypedQuery<Match> query = em.createQuery("SELECT m FROM Match m WHERE m.home.id = :homeId " +
                "OR m.away.id = :awayId", Match.class);
        query.setParameter("homeId", id);
        query.setParameter("awayId", id);
        return query.getResultList();
    }

    @Override
    public List<Match> findByTeamIdFromDate(long id, Date date) {
        final TypedQuery<Match> query = em.createQuery("SELECT m FROM Match m WHERE m.day >= :day AND " +
                "(m.home.id = :homeId OR m.away.id = :awayId)", Match.class);
        query.setParameter("day", date);
        query.setParameter("homeId", id);
        query.setParameter("awayId", id);
        return query.getResultList();
    }

    @Override
    public List<Match> findByTeamIdBeforeDate(long id, Date date) {
        final TypedQuery<Match> query = em.createQuery("SELECT m FROM Match m WHERE m.day < :day AND " +
                "(m.home.id = :homeId OR m.away.id = :awayId)", Match.class);
        query.setParameter("day", date);
        query.setParameter("homeId", id);
        query.setParameter("awayId", id);
        return query.getResultList();
    }

    @Override
    public List<Match> findByLeagueId(long id) {
        final TypedQuery<Match> query = em.createQuery("SELECT m FROM Match m WHERE m.league.id = :leagueId", Match.class);
        query.setParameter("leagueId", id);
        return query.getResultList();
    }

    @Override
    public List<Match> findByLeagueIdAndDate(long id, Date date) {
        final TypedQuery<Match> query = em.createQuery("SELECT m FROM Match m WHERE m.league.id = :leagueId " +
                "AND m.day = :day", Match.class);
        query.setParameter("leagueId", id);
        query.setParameter("day", date);
        return query.getResultList();
    }

    @Override
    public List<Match> findPlayedByLeagueId(long id) {
        final TypedQuery<Match> query = em.createQuery("SELECT m FROM Match m WHERE m.league.id = :leagueId " +
                "AND m.played = true ORDER BY m.day DESC", Match.class);
        query.setParameter("leagueId", id);
        return query.getResultList();
    }

    @Override
    public List<Match> findByLeagueIdAndBeforeDate(long id, Date date) {
        final TypedQuery<Match> query = em.createQuery("SELECT m FROM Match m WHERE m.league.id = :leagueId " +
                "AND m.day < :day", Match.class);
        query.setParameter("leagueId", id);
        query.setParameter("day", date);
        return query.getResultList();
    }

    @Override
    public List<Match> findByLeagueIdAndFromDate(long id, Date date) {
        final TypedQuery<Match> query = em.createQuery("SELECT m FROM Match m WHERE m.league.id = :leagueId " +
                "AND m.day >= :day", Match.class);
        query.setParameter("leagueId", id);
        query.setParameter("day", date);
        return query.getResultList();
    }

    @Override
    public Match create(League league, Team home, Team away, Date day) {
        final Match match = new Match(home, away, league, day, 0, 0, 0, 0, null, false, null);
        em.persist(match);
        return match;
    }

    @Override
    public Match create(long leagueId, long homeId, long awayId, Date day) {
        League league = em.getReference(League.class, leagueId);
        Team home = em.getReference(Team.class, homeId);
        Team away = em.getReference(Team.class, awayId);
        return create(league, home, away, day);
    }

    @Override
    public boolean save(Match match) {
        em.merge(match);
        return true;
    }
}
