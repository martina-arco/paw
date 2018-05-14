package ar.edu.itba.persistence;

import org.hsqldb.jdbc.JDBCDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

@ComponentScan({ "ar.edu.itba.persistence"})
@Configuration
public class TestConfig {
    @Bean
    public DataSource dataSource() {
        final SimpleDriverDataSource ds = new SimpleDriverDataSource();
        ds.setDriverClass(org.h2.Driver.class);
        ds.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;IGNORECASE=TRUE");
        ds.setUsername("sa");
        ds.setPassword("");
        return ds;
    }
}
