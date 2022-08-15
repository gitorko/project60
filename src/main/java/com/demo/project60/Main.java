package com.demo.project60;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.demo.project60.domain.Customer;
import com.demo.project60.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;

@SpringBootApplication
@Slf4j
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner seedData(CustomerRepository customerRepository) {
        return args -> {
            log.info("Initializing repo!");
            List<String> city = Arrays.asList("London", "New York", "Bangalore");
            Flux<Customer> customers = Flux.range(1, 5).map(i -> {
                int randomIndex = new Random().nextInt(2 - 0 + 1) + 0;
                return new Customer(null, "first_" + i, "last_" + i, city.get(randomIndex));
            });
            customerRepository.deleteAll()
                    .thenMany(customers.flatMap(customerRepository::save)
                            .thenMany(customerRepository.findAll()))
                    .subscribe(e -> log.info(e.toString()));
            log.info("Data seed completed!");
        };
    }
}
