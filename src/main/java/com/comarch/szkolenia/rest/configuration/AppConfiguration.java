package com.comarch.szkolenia.rest.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.hibernate.SessionFactory;

@Configuration
@ComponentScan("com.comarch.szkolenia.rest")
public class AppConfiguration {

    /*@Bean
    public Connection connection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "");
        } catch (ClassNotFoundException e) {
            System.out.println("nie znaleziono sterownika !!");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println("nie dziala polaczenie do bazy !!");
            throw new RuntimeException(e);
        }
    }*/

    @Bean
    public SessionFactory sessionFactory() {
        return new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
    }
}
