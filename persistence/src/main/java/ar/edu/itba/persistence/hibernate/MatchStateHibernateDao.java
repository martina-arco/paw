package ar.edu.itba.persistence.hibernate;

import ar.edu.itba.interfaces.dao.MatchStateDao;
import ar.edu.itba.model.Match;
import ar.edu.itba.model.MatchDeepStatus;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class MatchStateHibernateDao implements MatchStateDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public MatchDeepStatus create(Match match) {
        final MatchDeepStatus matchDeepStatus = new MatchDeepStatus(match);
        em.persist(matchDeepStatus);
        return matchDeepStatus;
    }

    @Override
    public MatchDeepStatus findByMatch(Match match) {
        final TypedQuery<MatchDeepStatus> query = em.createQuery("SELECT ms FROM MatchDeepStatus ms " +
                "WHERE ms.match.id = :matchId", MatchDeepStatus.class);
        query.setParameter("matchId", match.getId());
        final List<MatchDeepStatus> list = query.getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public MatchDeepStatus save(MatchDeepStatus matchDeepStatus) {
        return em.merge(matchDeepStatus);
    }
}
