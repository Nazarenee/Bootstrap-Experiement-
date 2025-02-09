package com.example.demo.User;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandline (UserRepository repository){
        return args -> {
            User nicklas = new User("Nicklas", "nicklas123",Role.USER);
            User anton =  new User("Anton", "Anton123", Role.ADMIN);
            repository.saveAll(List.of(nicklas, anton));
        };
    }
}
