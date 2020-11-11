package com.murillo.maciel.store.config;
import com.murillo.maciel.store.services.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("dev")
public class DevConfig
{
    @Autowired
    private DbService dbService;

//    recupera o valor da chave no properties de dev -> spring.jpa.hibernate.ddl-auto=create
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;


    @Bean
    public boolean instantiateTestDatabase() throws ParseException
    {
        if (!strategy.equals("create"))
        {
            return false;
        }
        dbService.instantiateTestDatabase();
        return true;
    }
}