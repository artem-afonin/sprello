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
 * REST контроллер, реализующий логику запросов на вступление пользователя {@link User} в доску {@link Board}
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

    /**
     * Обработчик POST маппинга, обрабатывающий заявку пользователя на вступление в доску
     *
     * @param requestor пользователь, подающий заявку на вступление
     * @param boardId   уникальный идентификатор доски
     *
     * @return HTTPResponse<br />
     * <b>status code: 404</b> в случае отсутствия доски<br/>
     * <b>status code: 400</b> если пользователь не имеет права отправить запрос<br/>
     * (уже состоит в доске, либо уже отправлял запрос ранее)<br/>
     * <b>status code: 200</b> если новый запрос успешно создан
     */
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

    /**
     * Обработчик POST маппинга, обрабатывающий одобрение заявки на вступление
     *
     * @param user        пользователь доски, одобряющий заявку на вступление
     * @param boardId     уникальный идентификатор доски
     * @param requestorId уникальный идентификатор пользователя, подавшего заявку на вступление
     *
     * @return HTTPResponse<br />
     * <b>status code: 404</b> в случае отсутствия доски или пользователя REQUESTOR<br/>
     * <b>status code: 403</b> если у пользователя USER нет прав на одобрение запроса<br/>
     * <b>status code: 200</b> если запрос на вступление одобрен успешно
     */
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
