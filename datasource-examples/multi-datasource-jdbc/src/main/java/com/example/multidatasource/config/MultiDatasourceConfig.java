package com.example.multidatasource.config;

import org.omg.CORBA.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

@PropertySources({@PropertySource("classpath:datasource-cfg.properties")})
@Configuration
public class MultiDatasourceConfig {

    @Autowired
    private ApplicationContext env;
//
//    @Bean(name = "dataSource")
//    public DataSource getDataSource(DataSource dataSource1, DataSource dataSource2) {
//
//        System.out.println("## Create DataSource from dataSource1 & dataSource2");
//
//        MyRoutingDataSource dataSource = new MyRoutingDataSource();
//
//        dataSource.initDataSources(dataSource1, dataSource2);
//
//        return dataSource;
//    }
//
//    @Bean(name = "dataSource1")
//    public DataSource getDataSource1() throws SQLException {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//
//        // See: datasouce-cfg.properties
//        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name.1"));
//        dataSource.setUrl(env.getProperty("spring.datasource.url.1"));
//        dataSource.setUsername(env.getProperty("spring.datasource.username.1"));
//        dataSource.setPassword(env.getProperty("spring.datasource.password.1"));
//
//        System.out.println("## DataSource1: " + dataSource);
//        return dataSource;
//    }
//
//    @Bean(name = "dataSource2")
//    public DataSource getDataSource2() throws SQLException {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//
//        // See: datasouce-cfg.properties
//        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name.2"));
//        dataSource.setUrl(env.getProperty("spring.datasource.url.2"));
//        dataSource.setUsername(env.getProperty("spring.datasource.username.2"));
//        dataSource.setPassword(env.getProperty("spring.datasource.password.2"));
//
//        System.out.println("## DataSource2: " + dataSource);
//
//        return dataSource;
//    }
//
//    @Bean
//    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
//        DataSourceTransactionManager txManager = new DataSourceTransactionManager();
//        txManager.setDataSource(dataSource);
//        return txManager;
//    }
}
