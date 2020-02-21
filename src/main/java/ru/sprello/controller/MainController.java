package ru.sprello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * MVC контроллер, возвращающий HTML View
 */
@Controller
@RequestMapping("/")
public class MainController {
    /**
     * @return HTML страницу SPA фронтенда
     */
    @GetMapping public String indexPage() {
        return "index";
    }
}
