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
@RequestMapping(Application.apiUrl + "board/user/requestors")
public class BoardUsersRequestorsController {
    private static final Logger LOG = Logger.getLogger(BoardUsersRequestorsController.class);
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Autowired
    public BoardUsersRequestorsController(BoardRepository boardRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/request")
    @JsonView(Views.PublicSimple.class)
    public ResponseEntity<?> createRequest(
            @AuthenticationPrincipal User requestor,
            @RequestParam Long boardId
    ) {
        Optional<Board> optionalBoard = boardRepository.findById(boardId);
        Board board;
        if (optionalBoard.isPresent()) {
            board = optionalBoard.get();
        } else {
            return ResponseEntity.notFound().build();
        }

        if (board.containsUser(requestor) || board.containsRequestor(requestor)) {
        // нельзя отправлять запрос если ты уже состоишь в доске!
        // не нужно грузить БД, если ты уже отправлял запрос!
            return ResponseEntity.badRequest().build();
        } else {
            board.getRequestors().add(requestor);
            board = boardRepository.save(board);
            return ResponseEntity.ok(board);
        }
    }

    @PostMapping("/accept")
    @JsonView(Views.PrivateBoard.class)
    public ResponseEntity<?> acceptRequest(
            @AuthenticationPrincipal User user,
            @RequestParam Long boardId,
            @RequestParam String requestorId
    ) {
        Optional<Board> optionalBoard = boardRepository.findById(boardId);
        Optional<User> optionalUser = userRepository.findById(requestorId);
        Board board;
        User newUser;
        if (optionalBoard.isPresent() && optionalUser.isPresent()) {
            board = optionalBoard.get();
            newUser = optionalUser.get();
        } else {
            return ResponseEntity.notFound().build();
        }

        if (!board.containsUser(user)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        if (!board.containsRequestor(newUser)) {
            return ResponseEntity.badRequest().build();
        }

        board.getRequestors().remove(newUser);
        board.getUsers().add(newUser);
        board = boardRepository.save(board);
        return ResponseEntity.ok(board);
    }
}
