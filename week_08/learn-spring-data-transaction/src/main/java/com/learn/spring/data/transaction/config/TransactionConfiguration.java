package com.learn.spring.data.transaction.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * 事务配置
 *
 * @author Created by lx_068
 * @date 2021/12/26
 */
@Configuration
@EnableTransactionManagement
public class TransactionConfiguration {

    /**
     * Create platform transaction manager bean.
     *
     * @param dataSource data source
     * @return platform transaction manager
     */
    @Bean
    public PlatformTransactionManager txManager(final DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * Create JDBC template bean.
     *
     * @param dataSource data source
     * @return JDBC template bean
     */
    @Bean
    public JdbcTemplate jdbcTemplate(final DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
