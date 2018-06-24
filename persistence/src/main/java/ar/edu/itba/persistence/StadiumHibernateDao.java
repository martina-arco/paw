package ar.edu.itba.persistence;

import ar.edu.itba.interfaces.dao.StadiumDao;
import ar.edu.itba.model.Stadium;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StadiumHibernateDao implements StadiumDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Stadium create(String name, Team team, int lowClass, int lowClassPrice, int mediumClass, int mediumClassPrice,
                          int highClass, int highClassPrice) {
        final Stadium stadium = new Stadium(name, team, lowClass, mediumClass, highClass, lowClassPrice, mediumClassPrice,
                highClassPrice);
        em.persist(stadium);
        return stadium;
    }

    @Override
    public Stadium create(String name, long teamId, int lowClass, int lowClassPrice, int mediumClass, int mediumClassPrice, int highClass, int highClassPrice) {
        Team team = em.getReference(Team.class, teamId);
        return create(name, team, lowClass, lowClassPrice, mediumClass, mediumClassPrice, highClass, highClassPrice);
    }

    @Override
    public boolean save(Stadium stadium) {
        em.merge(stadium);
        return true;
    }

    @Override
    public Stadium findById(long id) {
        return em.find(Stadium.class, id);
    }

    @Override
    public Stadium findByTeamId(long id) {
        final TypedQuery<Stadium> query = em.createQuery("SELECT s FROM Stadium s WHERE s.team.id = :teamId", Stadium.class);
        query.setParameter("teamId", id);
        final List<Stadium> list = query.getResultList();
        return list.isEmpty() ? null : list.get(0);
    }
}
