package com.example.boot.thymeleaf.app;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class PersistenceContext {

    @Value("${db.url}")
    private String dbUrl;

    @Value("${db.username}")
    private String dbUsername;

    @Value("${db.password}")
    private String dbPassword;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setPackagesToScan(new String[]{"com.example.boot.thymeleaf"});
        emf.setPersistenceUnitName("persistenceUnit");
        emf.setJpaPropertyMap(jpaPropertyMap());

        final JpaVendorAdapter vendorAdapter = new org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter();
        emf.setJpaVendorAdapter(vendorAdapter);
        emf.setJpaProperties(jpaProperties());

        return emf;
    }

    @Bean
    public org.springframework.jdbc.datasource.DriverManagerDataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }

    public Properties jpaProperties() {
        final Properties properties = new Properties();
        properties.setProperty("databasePlatform", "org.eclipse.persistence.platform.database.H2Platform");
        properties.setProperty("generateDdl", "true");
        properties.setProperty("showSql", "false");

        return properties;
    }

    public Map<String, Object> jpaPropertyMap() {
        final Map<String, Object> jpaPropertyMap = new HashMap<>();
        // TODO init static weaving to improve performance
        jpaPropertyMap.put("eclipselink.weaving", "false");
        jpaPropertyMap.put("eclipselink.logging.level.sql", "ALL");
        jpaPropertyMap.put("eclipselink.cache.shared.default", "true");
        jpaPropertyMap.put("javax.persistence.schema-generation.database.action", "create");

        return jpaPropertyMap;
    }

}
