package com.sline.sline.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource({"classpath:persistence-mysql.properties"})
public class JPAConfig {
    private final Environment environment;

    public JPAConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    @Primary
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(environment.getRequiredProperty("spring.datasource.url") + "?useUnicode=yes&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false");
        dataSource.setUsername(environment.resolveRequiredPlaceholders(environment.getRequiredProperty("spring.datasource.username")));
        dataSource.setPassword(environment.resolveRequiredPlaceholders(environment.getRequiredProperty("spring.datasource.password")));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.sline.sline.entity");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(hibernateProperties());
        return em;
    }

    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("spring.jpa.database-platform", environment.getRequiredProperty("hibernate.dialect"));
        hibernateProperties.setProperty("spring.jpa.generate-ddl", "true");
        hibernateProperties.setProperty("spring.jpa.show-sql", "true");
        hibernateProperties.setProperty("hibernate.connection.CharSet", "utf8");
        hibernateProperties.setProperty("hibernate.connection.characterEncoding", "utf8");
        hibernateProperties.setProperty("hibernate.connection.useUnicode", "true");
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
        hibernateProperties.setProperty("hibernate.enable_lazy_load_no_trans", "true");
        return hibernateProperties;
    }
}
