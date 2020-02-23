package ru.sprello.controller.board;

import com.fasterxml.jackson.annotation.JsonView;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.sprello.Application;
import ru.sprello.model.User;
import ru.sprello.model.board.Board;
import ru.sprello.repo.BoardRepository;
import ru.sprello.repo.UserRepository;
import ru.sprello.utils.Views;

import java.util.Optional;

/**
 * REST контроллер, контролирующий доступ к сведениям о {@link User}, состоящих в {@link Board}.
 * <p>
 * Данный контроллер работает только с данными, которые связывают {@link User} и {@link Board},
 * приватные данные вышеописанных моделей не подвергаются изменению.
 */
@RestController
@RequestMapping(Application.apiUrl + "board/users")
public class BoardUsersController {
    private static final Logger LOG = Logger.getLogger(BoardUsersController.class);
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Autowired
    public BoardUsersController(BoardRepository boardRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    /**
     * Обработчик POST маппинга, реализующий добавление пользователя в доску
     *
     * @param requestor пользователь, одобряющий заявку на вступление
     * @param boardId   уникальный идентификатор доски
     * @param newUserId уникальный идентификатор нового пользователя, которого добавляют в доску
     *
     * @return HTTPResponse<br />
     * <b>status code: 404</b> в случае отсутствия доски<br/>
     * <b>status code: 403</b> в случае отсутствия прав у requestor на совершение запроса<br/>
     * <b>status code: 200</b> если пользователь добавлен успешно
     */
    @PostMapping
    @JsonView(Views.PublicSimple.class)
    public ResponseEntity<?> addNewUser(
            @AuthenticationPrincipal User requestor,
            @RequestParam Long boardId,
            @RequestParam(name = "userId") String newUserId
    ) {
        Optional<Board> optionalBoard = boardRepository.findById(boardId);
        Board board;

        // участники добаляют пользователя в приватную доску
        Optional<User> newOptionalUser = userRepository.findById(newUserId);
        User newUser;
        if (optionalBoard.isPresent() && newOptionalUser.isPresent()) {
            board = optionalBoard.get();
            newUser = newOptionalUser.get();
        } else {
            return ResponseEntity.notFound().build();
        }

        // если автор запроса сам состоит в доске
        if (board.containsUser(requestor)) {
            board.getUsers().add(newUser);
            board = boardRepository.save(board);
            return ResponseEntity.ok(board);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

    }

    /**
     * Обработчик DELETE маппинга, реализующий удаление пользователя из доски
     *
     * @param requestor пользователь, покидающий доску
     * @param boardId   уникальный идентификатор доски
     *
     * @return HTTPResponse<br />
     * <b>status code: 404</b> в случае отсутствия доски<br/>
     * <b>status code: 200</b> если пользователь успешно удолён
     */
    @DeleteMapping
    public ResponseEntity<?> leaveBoard(
            @AuthenticationPrincipal User requestor,
            @RequestParam Long boardId
    ) {
        Optional<Board> optionalBoard = boardRepository.findById(boardId);
        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();
            board.getUsers().remove(requestor);
            boardRepository.save(board);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
