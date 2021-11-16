package by.beglyakdehterenok.store.config;

import by.beglyakdehterenok.store.entity.Storage;
import by.beglyakdehterenok.store.mapper.StorageMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource(value = "classpath:database.properties")
@ComponentScan(basePackages = "by.beglyakdehterenok.store")
@EnableJpaRepositories(basePackages = "by.beglyakdehterenok.store.repository")
@EnableTransactionManagement
public class DatabaseConfig {

    private final Environment environment;

    @Autowired
    public DatabaseConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("hibernate.driver"));
        dataSource.setUsername(environment.getRequiredProperty("hibernate.user"));
        dataSource.setPassword(environment.getRequiredProperty("hibernate.password"));
        dataSource.setUrl(environment.getRequiredProperty("hibernate.url"));

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(dataSource());
        entityManager.setPackagesToScan("by.beglyakdehterenok.store.entity");

        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        entityManager.setJpaVendorAdapter(jpaVendorAdapter);
        entityManager.setJpaProperties(jpaProperties());

        return entityManager;
    }

    @Bean
    public Properties jpaProperties(){
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect",environment.getRequiredProperty("hibernate.dialect"));
//        properties.setProperty("hibernate.format_sql",environment.getRequiredProperty("hibernate.format_sql"));
//        properties.setProperty("hibernate.show_sql",environment.getRequiredProperty("hibernate.show_sql"));
        properties.setProperty("hibernate.hbm2ddl.auto",environment.getRequiredProperty("hibernate.hbm2ddl.auto"));

        return properties;
    }

    @Bean
    @Scope("prototype")
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    @Scope("prototype")
    public Storage storage(){
        return new Storage();
    }

    @Bean
    public StorageMapperImpl storageMapper(){
        return new StorageMapperImpl();
    }
}
