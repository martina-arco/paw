package ar.edu.itba.persistence;

import ar.edu.itba.interfaces.dao.UserDao;
import ar.edu.itba.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository
public class UserHibernateDao implements UserDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserHibernateDao.class);

    private final DataSource ds;

    @PersistenceContext
    private EntityManager em;

    @Value("classpath:init.sql")
    private Resource initSql;

    @Autowired
    public UserHibernateDao(final DataSource ds) {
        this.ds = ds;
    }

    @Override
    public User findById(final long id) {
        return em.find(User.class, id);
    }

    @Override
    public User findByUsername(String username) {
        final TypedQuery<User> query = em.createQuery("select u from User u where u.username = :username", User.class);
        query.setParameter("username", username);
        final List<User> list = query.getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public User create(String username, String password, String mail, Date currentDay) {
        final User user = new User(username, password, mail, null, currentDay);
        em.persist(user);
        initDataSet(user.getId());
        return user;
    }

    @Override
    public boolean save(User user) {
        em.merge(user);
        return true;
    }

    private boolean initDataSet(long userId) {
        StringBuilder script = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(initSql.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                script.append(line);
            }
        } catch (IOException e) {
            LOGGER.error("Failed to read init.sql", e);
            return false;
        }
        int index;
        while ((index = script.indexOf("?")) != -1) {
            script.replace(index, index + 1, Long.toString(userId));
        }

        try {
            ScriptUtils.executeSqlScript(ds.getConnection(),
                    new InputStreamResource( new ByteArrayInputStream(script.toString().getBytes())));
        } catch (SQLException e) {
            LOGGER.error("Failed to get datasource", e);
            return false;
        }
        return true;
    }
}