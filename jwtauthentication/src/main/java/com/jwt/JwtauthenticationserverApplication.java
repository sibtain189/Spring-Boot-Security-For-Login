package com.jwt;

import com.jwt.model.User;
import com.jwt.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Random;


@SpringBootApplication
public class JwtauthenticationserverApplication {



    public static void main(String[] args) {
        SpringApplication.run(JwtauthenticationserverApplication.class, args);

    }

    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    

}
