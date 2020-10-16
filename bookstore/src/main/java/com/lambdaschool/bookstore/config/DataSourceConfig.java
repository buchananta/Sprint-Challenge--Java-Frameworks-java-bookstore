package com.lambdaschool.bookstore.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class DataSourceConfig
{
    @Value("H2")
    private String dValue;

    @Value("${spring.datasource.url:}")
    private String dURL;

    @Bean
    public DataSource dataSource()
    {
        if (dValue.equalsIgnoreCase("POSTGRESQL"))
        {
            // Assuming Heroku
            HikariConfig config = new HikariConfig();
            config.setDriverClassName("org.postgresql.Driver");
            config.setJdbcUrl(dURL);
            return new HikariDataSource(config);
        } else
        {
            // default H2
            String myURLString = "jdbc:h2:mem:testdb";
            String myDriverClass = "org.h2.Driver";
            String myUserName = "sa";
            String myPassword = "";

            return DataSourceBuilder.create()
                    .username(myUserName)
                    .password(myPassword)
                    .url(myURLString)
                    .driverClassName(myDriverClass)
                    .build();
        }
    }
}
