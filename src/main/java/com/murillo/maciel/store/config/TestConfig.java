package com.murillo.maciel.store.config;
import com.murillo.maciel.store.services.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig
{
    @Autowired
    private DbService dbService;


    @Bean
    public boolean instantiateTestDatabase() throws ParseException
    {
        dbService.instantiateTestDatabase();
        return true;
    }
}