package ar.edu.itba.persistence;

import ar.edu.itba.model.Stadium;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import javax.print.attribute.standard.MediaSize;
import javax.sql.DataSource;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Sql("classpath:schema.sql")
public class StadiumJdbcDaoTest {

    private final String NAME = "Estadio";
    private final int LOWCLASS = 11233;
    private final int LOWCLASS_PRICE = 123;
    private final int MEDIUMCLASS = 145747;
    private final int MEDIUMCLASS_PRICE = 432;
    private final int HIGHCLASS = 7234;
    private final int HIGHCLASS_PRICE = 1094;

    @Autowired
    private DataSource ds;
    @Autowired
    private StadiumJdbcDao stadiumDao;

    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(ds);
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "stadium");
    }

    @Test
    public void testCreate() {
        final Stadium stadium = stadiumDao.create(NAME, LOWCLASS, LOWCLASS_PRICE, MEDIUMCLASS, MEDIUMCLASS_PRICE, HIGHCLASS, HIGHCLASS_PRICE);
        assertNotNull(stadium);
        assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "stadium"));
        assertEquals(NAME, stadium.getName());
        assertEquals(LOWCLASS, stadium.getLowClass());
        assertEquals(LOWCLASS_PRICE, stadium.getLowClassPrice());
        assertEquals(MEDIUMCLASS, stadium.getMediumClass());
        assertEquals(MEDIUMCLASS_PRICE, stadium.getMediumClassPrice());
        assertEquals(HIGHCLASS, stadium.getHighClass());
        assertEquals(HIGHCLASS_PRICE, stadium.getHighClassPrice());
    }

    @Test
    public void testFindById() {
        final long id = stadiumDao.create(NAME, LOWCLASS, LOWCLASS_PRICE, MEDIUMCLASS, MEDIUMCLASS_PRICE, HIGHCLASS, HIGHCLASS_PRICE).getId();
        Stadium stadium = stadiumDao.findById(id);
        assertNotNull(stadium);
        assertEquals(NAME, stadium.getName());
        assertEquals(LOWCLASS, stadium.getLowClass());
        assertEquals(LOWCLASS_PRICE, stadium.getLowClassPrice());
        assertEquals(MEDIUMCLASS, stadium.getMediumClass());
        assertEquals(MEDIUMCLASS_PRICE, stadium.getMediumClassPrice());
        assertEquals(HIGHCLASS, stadium.getHighClass());
        assertEquals(HIGHCLASS_PRICE, stadium.getHighClassPrice());
        assertEquals(id, stadium.getId());
    }
}