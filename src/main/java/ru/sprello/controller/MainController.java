package ru.sprello.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sprello.model.User;

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
    public String indexPage(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("isDevMode", isDevMode);
        model.addAttribute("userInfo", user);
        return "index";
    }

    @GetMapping("board")
    public String boardPage(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("isDevMode", isDevMode);
        model.addAttribute("userInfo", user);
        return "board";
    }

    @GetMapping("board/{id}")
    public String boardTemplate(Model model, @PathVariable String id, @AuthenticationPrincipal User user) {
        model.addAttribute("isDevMode", isDevMode);
        model.addAttribute("id", id);
        model.addAttribute("userInfo", user);
        return "board_template";
    }

    @GetMapping("users")
    public String userPage(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("isDevMode", isDevMode);
        model.addAttribute("userInfo", user);
        return "users";
    }

    @GetMapping("about")
    public String aboutPage(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("isDevMode", isDevMode);
        model.addAttribute("userInfo", user);
        return "about";
    }
}
