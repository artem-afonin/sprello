package ru.sprello.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sprello.Application;
import ru.sprello.model.User;
import ru.sprello.repo.BoardRepository;
import ru.sprello.repo.UserRepository;
import ru.sprello.utils.Views;

import java.util.Optional;

@RestController
@RequestMapping(Application.apiUrl + "user")
public class UserController {
    private static final Logger LOG = Logger.getLogger(UserController.class);
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    @Autowired
    public UserController(UserRepository userRepository, BoardRepository boardRepository) {
        this.userRepository = userRepository;
        this.boardRepository = boardRepository;
    }

    /**
     * Обработчик для получения информации о пользователе, выполняющем запрос
     *
     * @param requestor пользователь, запрашивающий информацию
     *
     * @return основная информация о данном пользователе
     */
    @GetMapping
    @JsonView(Views.PublicExtendedUser.class)
    public ResponseEntity<?> getMyUser(
            @AuthenticationPrincipal User requestor
    ) {
        Optional<User> optionalUser = userRepository.findById(requestor.getId());
        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(optionalUser.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Обработчик для получения информации о пользователе с id
     *
     * @param requestor пользователь, запрашивающий информацию
     * @param id        уникальный идентификатор пользователя, о котором запрашивается информация
     *
     * @return основная информация о пользователе с id
     */
    @GetMapping("{id}")
    @JsonView(Views.PublicExtendedUser.class)
    public ResponseEntity<?> getMyUser(
            @AuthenticationPrincipal User requestor,
            @PathVariable String id
    ) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(optionalUser.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
