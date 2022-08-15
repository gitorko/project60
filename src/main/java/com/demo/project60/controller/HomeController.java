package com.demo.project60.controller;

import java.time.LocalDateTime;

import com.demo.project60.domain.Customer;
import com.demo.project60.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Slf4j
public class HomeController {
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
