package ru.bogatov.AutoRent;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;


@SpringBootApplication
public class Applicaton {
    public static void main(String[] args) {
        SpringApplication.run(Applicaton.class, args);

    }
}
