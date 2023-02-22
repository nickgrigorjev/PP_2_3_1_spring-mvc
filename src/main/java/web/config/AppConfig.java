package web.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "web.entity")
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
public class AppConfig {

    private final Environment environment;

//    @Autowired
    public AppConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("db.driver"));
        dataSource.setUrl(environment.getProperty("db.url"));
        dataSource.setUsername(environment.getProperty("db.username"));
        dataSource.setPassword(environment.getProperty("db.password"));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean getLocalEntityManager() {
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();//?????
        entityManager.setDataSource(getDataSource());
        entityManager.setPackagesToScan("web");
        Properties properties = new Properties();
        properties.put(org.hibernate.cfg.Environment.SHOW_SQL, environment.getProperty("hibernate.show_sql"));
        properties.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, environment.getProperty("hibernate.hbm2ddl.auto"));
//        properties.put(org.hibernate.cfg.Environment.DIALECT, environment.getProperty("hibernate.dialect"));
        entityManager.setJpaProperties(properties);
        entityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());//?????
        return entityManager;
    }


    @Bean
    public JpaTransactionManager getTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setDataSource(getDataSource());
        transactionManager.setEntityManagerFactory(getLocalEntityManager().getObject());
        return transactionManager;
    }
}
