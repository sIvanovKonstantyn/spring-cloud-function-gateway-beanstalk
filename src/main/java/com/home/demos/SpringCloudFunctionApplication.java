package com.home.demos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@SpringBootApplication
public class SpringCloudFunctionApplication {
    private static final List<Book> storage = new ArrayList<>();

    public static void main(String[] args) {
        SpringApplication.run(
                SpringCloudFunctionApplication.class, args);
    }

    @Bean
    public Supplier<List<Book>> findAll() {
        return () -> storage;
    }

    @Bean
    public Function<String, List<Book>> findByName() {
        return input -> storage.stream()
                .filter(book -> book.getName().equals(input))
                .collect(Collectors.toList());
    }

    @Bean
    public Consumer<Book> save() {
        return storage::add;
    }
}
