package ru.sprello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
    @GetMapping
    public String indexPage(Model model) {
        return "index";
    }

    @GetMapping("board")
    public String boardPage(Model model) {
        return "board";
    }

    @GetMapping("board/{id}")
    public String boardTemplate(Model model, @PathVariable String id) {
        model.addAttribute("id", id);
        return "board_template";
    }

    @GetMapping("users")
    public String userPage(Model model) {
        return "users";
    }

    @GetMapping("about")
    public String aboutPage(Model model) {
        return "about";
    }
}
