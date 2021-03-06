package com.demo.project60;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    //Route all requests on / to index.html
    @Bean
    public RouterFunction<ServerResponse> indexRouter(@Value("classpath:/public/index.html") final Resource indexHtml) {
        return route(GET("/"), request -> ok().contentType(MediaType.TEXT_HTML).bodyValue(indexHtml));
    }
}

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Slf4j
class CustomerRestController {
    private final CustomerRepository repo;

    @GetMapping("/customer")
    public Flux<Customer> getCustomers() {
        log.info("Getting customers!");
        return repo.findAll();
    }

    @PostMapping("/customer")
    public Mono<Customer> saveCustomer(@RequestBody Customer customer) {
        log.info("Saving customer: {}", customer);
        return repo.save(customer);
    }

    @DeleteMapping(value = "/customer/{id}")
    public Mono<Void> deleteCustomer(@PathVariable String id) {
        log.info("Deleting customer: {}", id);
        return repo.deleteById(id);
    }

    @GetMapping("/time")
    public Mono<LocalDateTime> getTime() {
        return Mono.just(LocalDateTime.now());
    }

}

@Component
@AllArgsConstructor
@Slf4j
class Initializer implements ApplicationRunner {

    private final CustomerRepository repo;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Initializing repo!");
        List<String> city = Arrays.asList("London", "New York", "Bangalore");
        Flux<Customer> customers = Flux.range(1, 5).map(i -> {
            int randomIndex = new Random().nextInt(2 - 0 + 1) + 0;
            return new Customer(null, "first_" + i, "last_" + i, city.get(randomIndex));
        });
        repo.deleteAll().thenMany(customers.flatMap(repo::save).thenMany(repo.findAll())).subscribe(e -> log.info(e.toString()));
        log.info("Data seed completed!");
    }
}

interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {
}

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
class Customer {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String city;
}
