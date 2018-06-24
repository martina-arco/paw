package ar.edu.itba.persistence.hibernate;

import ar.edu.itba.interfaces.dao.TeamDao;
import ar.edu.itba.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;
import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TeamHibernateDao implements TeamDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Team create(String name, League league, Stadium stadium, Formation formation, List<Player> players, List<Player> youthAcademy, int fanCount, int fanTrust, int boardTrust, int money) {
        final Team team = new Team(name, league, stadium, formation, players, youthAcademy, fanCount, fanTrust, boardTrust, money, null);
        em.persist(team);
        return team;
    }

    @Override
    public Team create(String name, long leagueId, long formationId, int fanCount, int fanTrust, int boardTrust, int money) {
        League league = em.getReference(League.class, leagueId);
        Formation formation = em.getReference(Formation.class, formationId);
        return create(name, league, null, formation, null, null, fanCount, fanTrust, boardTrust, money);
    }

    @Override
    public boolean save(Team team) {
        em.merge(team);
        return true;
    }

    @Override
    public Team findById(final long id) {
        return em.find(Team.class, id);
    }

    @Override
    public List<Team> findAllByLeagueId(long id) {
        final TypedQuery<Team> query = em.createQuery("SELECT t FROM Team t WHERE t.league.id = :leagueId", Team.class);
        query.setParameter("leagueId", id);
        return query.getResultList();
    }

    @Override
    public Team findByUserId(long id) {
        final TypedQuery<Team> query = em.createQuery("SELECT u.team FROM User u WHERE u.id = :userId", Team.class);
        query.setParameter("userId", id);
        final List<Team> list = query.getResultList();
        return list.isEmpty() ? null : list.get(0);
    }
}
