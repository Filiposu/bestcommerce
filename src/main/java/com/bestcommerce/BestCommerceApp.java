package com.bestcommerce;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BestCommerceApp implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BestCommerceApp.class,args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
