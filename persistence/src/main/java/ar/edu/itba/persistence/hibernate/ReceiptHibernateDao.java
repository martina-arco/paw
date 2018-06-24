package ar.edu.itba.persistence.hibernate;

import ar.edu.itba.interfaces.dao.ReceiptDao;
import ar.edu.itba.model.Receipt;
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
public class ReceiptHibernateDao implements ReceiptDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Receipt> findAllByTeamId(long id) {
        final TypedQuery<Receipt> query = em.createQuery("SELECT r FROM Receipt r WHERE r.team.id = :teamId", Receipt.class);
        query.setParameter("teamId", id);
        return query.getResultList();
    }

    @Override
    public Receipt create(Team team, int amount, Receipt.Type type) {
        final Receipt receipt = new Receipt(amount, type, team);
        em.persist(receipt);
        return receipt;
    }

    @Override
    public Receipt create(long teamId, int amount, Receipt.Type type) {
        Team team = em.getReference(Team.class, teamId);
        return create(team, amount, type);
    }
}
