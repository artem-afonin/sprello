package ru.sprello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Класс, реализующий старт Spring Boot приложения
 * @see SpringApplication
 */
@SpringBootApplication
public class Application {
    /**
     * Стандартный URL префикс для REST API
     */
    public static final String apiUrl = "api/v1/";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}