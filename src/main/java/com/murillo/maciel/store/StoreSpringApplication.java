package com.murillo.maciel.store;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import com.murillo.maciel.store.domain.*;
import com.murillo.maciel.store.domain.enums.ClientType;
import com.murillo.maciel.store.domain.enums.PaymentStatus;
import com.murillo.maciel.store.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoreSpringApplication implements CommandLineRunner
{

    public static void main(String[] args)
    {
        SpringApplication.run(StoreSpringApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception
    {
    }

}
