package com.greglturnquist.hackingspringboot.reactive;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

@Component
public class TemplateDatabaseLoader {
    @Bean
    CommandLineRunner initialize(MongoOperations mongo) {
        return args -> {
            mongo.save(new Item ("Alf alarm clock","Wake up with Alf!",19.99));
            mongo.save(new Item("Smurf TV tray", "It is a small blue tray, so what?!",24.99));
        };
    }
}
