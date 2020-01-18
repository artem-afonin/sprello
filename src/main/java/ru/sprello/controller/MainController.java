package ru.sprello.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sprello.model.User;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping("/")
public class MainController {
    @GetMapping public String indexPage() { return "index"; }
}
