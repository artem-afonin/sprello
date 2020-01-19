package ru.sprello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static final String apiUrl = "api/v1/";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}