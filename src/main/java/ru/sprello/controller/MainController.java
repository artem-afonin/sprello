package ru.sprello.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping("/")
public class MainController {
    @Value("${spring.profiles.active:prod}")
    private String profile;
    private Boolean isDevMode;

    @PostConstruct
    private void postConstruct() {
        this.isDevMode = "dev".equals(profile);
    }

    @GetMapping
    public String indexPage(Model model) {
        model.addAttribute("isDevMode", isDevMode);
        return "index";
    }

    @GetMapping("board")
    public String boardPage(Model model) {
        model.addAttribute("isDevMode", isDevMode);
        return "board";
    }

    @GetMapping("board/{id}")
    public String boardTemplate(Model model, @PathVariable String id) {
        model.addAttribute("isDevMode", isDevMode);
        model.addAttribute("id", id);
        return "board_template";
    }

    @GetMapping("users")
    public String userPage(Model model) {
        model.addAttribute("isDevMode", isDevMode);
        return "users";
    }

    @GetMapping("about")
    public String aboutPage(Model model) {
        model.addAttribute("isDevMode", isDevMode);
        return "about";
    }
}
