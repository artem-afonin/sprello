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
import ru.sprello.model.Views;
import ru.sprello.model.board.Board;
import ru.sprello.repo.BoardRepository;
import ru.sprello.repo.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
     * Обработчик для получения основной информации о пользователе, выполняющем запрос
     *
     * @param requestor пользователь, запрашивающий информацию
     *
     * @return основная информация о данном пользователе
     */
    @GetMapping
    @JsonView(Views.PublicSimple.class)
    public ResponseEntity<?> getMyUser(@AuthenticationPrincipal User requestor) {
        Optional<User> optionalUser = userRepository.findById(requestor.getId());
        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(optionalUser.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Обработчик для получения списка Board, в которых состоит текущий пользователь
     *
     * @param requestor пользователь, запрашивающий информацию
     *
     * @return {@link UserController#getUserBoards(String, User)}
     */
    @GetMapping("boards")
    @JsonView(Views.PublicExtendedUser.class)
    public ResponseEntity<?> getMyUserBoards(@AuthenticationPrincipal User requestor) {
        return this.getUserBoards(requestor.getId(), requestor);
    }

    /**
     * Обработчик для получения списка Board, в которых состоит пользователь с id<br/>
     * <i>В список войдут только Board, на доступ к которым у requestor есть права</i>
     *
     * @param id        идентификатор пользователя, список Board которого запрашивается
     * @param requestor пользователь, запрашивающий список
     *
     * @return <b>code: 200</b> если список успешно найден в базе <i>(даже если список пуст)</i>
     * <b>code: 404</b> если список не найден в базе
     */
    @GetMapping("{id}/boards")
    @JsonView(Views.PublicExtendedUser.class)
    public ResponseEntity<?> getUserBoards(@PathVariable String id, @AuthenticationPrincipal User requestor) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            // Получаю пользователя, по которому нужна информация
            User user = optionalUser.get();

            // Отдаю список состоящий только из тех бордов, которые можно показывать requestor
            List<Board> boards = boardRepository.findAllByUsersContaining(user).stream().filter(board -> {
                return !board.getIsPrivate() || board.getUsers().contains(requestor);
            }).collect(Collectors.toList());

            return ResponseEntity.ok(boards);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
