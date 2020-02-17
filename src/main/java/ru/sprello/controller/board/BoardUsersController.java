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

/* Во всех методах этого контроллера
    обязательно делать проверку пользователя
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

    @PostMapping
    @JsonView(Views.PublicSimple.class)
    public ResponseEntity<?> addNewUser(
            @AuthenticationPrincipal User requestor,
            @RequestParam Long boardId,
            @RequestParam(name = "userId") String newUserId
    ) {
        Optional<Board> optionalBoard = boardRepository.findById(boardId);
        Board board;

        //TODO Здесь был плохой код. Реализовать отдельный контроллер для подачи заявки на вступление в борду

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
