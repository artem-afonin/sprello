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
import ru.sprello.utils.Views;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * REST контроллер, контролирующий доступ к сведениям о {@link Board}.
 */
@RestController
@RequestMapping(Application.apiUrl + "board")
public class BoardController {
    private static final Logger LOG = Logger.getLogger(BoardController.class);
    private final BoardRepository boardRepository;

    @Autowired
    public BoardController(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    /**
     * Обработчик GET маппинга для получения всех доступных Board для данного пользователя
     * с учётом type параметра
     *
     * <i>Параметры </i>
     *
     * @param user пользователь, который выполняет запрос
     * @param own  <ul>
     *             <li><b>true</b> - вернуть только доски данного пользователя</li>
     *             <li><b>false</b> - вернуть все возможные доски</li>
     *             </ul>
     *
     * @return список досок, соответствующий параметру запроса
     */
    @JsonView(Views.PublicSimple.class)
    @GetMapping
    public ResponseEntity<?> getAll(
            @AuthenticationPrincipal User user,
            @RequestParam(required = false) Boolean own) {
        List<Board> boards;
        if (own != null && own) {
            boards = boardRepository.findAllByUsersContaining(user);
            LOG.info("GET[own] boards for user with id " + user.getId());
        } else {
            boards = boardRepository.findAll();
            LOG.info("GET[all] boards for user with id " + user.getId());
        }

        return ResponseEntity.ok(boards);
    }

    /**
     * Обработчик GET маппинга для получения данных о Board по его id
     *
     * @param id   уникальный идентификатор Board
     * @param user пользователь, запрашивающий данные
     *
     * @return HTTPResponse<br/>
     * <b>status code: 200</b> в случае существования доски и наличия прав на её просмотр у user<br/>
     * <b>status code: 403</b> в случае отсутствия у пользователя прав на просмотр<br/>
     * <b>status code: 404</b> если досок не существует
     */
    @JsonView(Views.PrivateBoard.class)
    @GetMapping("{id}")
    public ResponseEntity<?> getOne(
            @AuthenticationPrincipal User user,
            @PathVariable Long id
    ) {
        Optional<Board> optionalBoard = boardRepository.findById(id);
        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();
            if (board.containsUser(user)) {
                // возвращаю код 200 (успех) и board
                LOG.info("GET[id=" + id + "] board for user with id " + user.getId());
                return ResponseEntity.ok(board);
            } else {
                // возвращаю код 403 (запрет доступа) и пустой body
                LOG.warn("GET[id=" + id + "] 403 FORBIDDEN for user with id " + user.getId());
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } else {
            // возвращаю код 404 (не найдено) и пустой body
            LOG.info("GET[id=" + id + "] 404 NOT FOUND");
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Обработчик POST маппинга для создания нового Board
     *
     * @param user создатель доски, <i>становится её первым участником</i>
     * @param name имя доски
     *
     * @return HTTPResponse<br/>
     * <b>status code: 200</b> в случае успешного создания и сохранения в базу данных<br/>
     * <b>status code: 400</b> в случае отсутствия параметров "name" или если "name" - пустая строка<br/>
     */
    @JsonView(Views.PublicSimple.class)
    @PostMapping
    public ResponseEntity<Board> createBoard(
            @AuthenticationPrincipal User user,
            @RequestParam String name
    ) {
        if (name.equals("")) {
            return ResponseEntity.badRequest().build();
        }
        Board board = new Board();
        board.setName(name);
        board.setUsers(Collections.singleton(user));
        board.setTasks(Collections.emptySet());
        board.setMessages(Collections.emptySet());
        board = boardRepository.save(board);

        return ResponseEntity.ok(board);
    }

    /**
     * Обработчик PATCH маппинга для частичного или полного обновления Board<br/>
     * <b>Обновляются только обычные поля. Поля коллекций обновлены не будут</b>
     *
     * @param user пользователь, выполняющий запрос
     * @param id   уникальный идентификатор Board
     * @param name новое название доски
     *
     * @return HTTPResponse<br/>
     * <b>status code: 200</b> в случае успешного обновления данных<br/>
     * <b>status code: 403</b> если у пользователя нет прав для изменения доски<br/>
     * <b>status code: 404</b> если Board отсутствует в базе данных
     */
    // TODO реализовать обновление списков в специальных контроллерах для Task, User и Message
    @JsonView(Views.PublicSimple.class)
    @PatchMapping
    public ResponseEntity<?> updateBoard(
            @AuthenticationPrincipal User user,
            @RequestParam Long id,
            @RequestParam(required = false) String name
    ) {
        Optional<Board> optionalBoard = boardRepository.findById(id);
        Board board;
        if (optionalBoard.isPresent()) {
            board = optionalBoard.get();
        } else {
            return ResponseEntity.notFound().build();
        }

        if (!board.containsUser(user)) {
            // если юзер в ней не состоит - 403 FORBIDDEN
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } else {
            if (name != null && !"".equals(name))
                board.setName(name);
            board = boardRepository.save(board);
            return ResponseEntity.ok(board);
        }
    }

    /**
     * Обработчик DELETE маппинга для удаления доски из базы данных
     *
     * @param id   уникальный идентификатор доски
     * @param user пользователь, выполняющий запрос
     *
     * @return HTTPResponse<br/>
     * <b>status code: 200</b> если удаление прошло успешно<br/>
     * <b>status code: 403</b> в случае отсутствия у пользователя прав на удаление данных<br/>
     * <b>status code: 404</b> если доска не существует
     */
    @JsonView(Views.PublicSimple.class)
    @DeleteMapping
    public ResponseEntity<?> deleteBoard(
            @AuthenticationPrincipal User user,
            @RequestParam Long id
    ) {
        Optional<Board> optionalBoard = boardRepository.findById(id);
        Board board;
        if (optionalBoard.isPresent()) {
            board = optionalBoard.get();
        } else {
            return ResponseEntity.notFound().build();
        }

        if (!board.containsUser(user)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } else {
            boardRepository.delete(board);
            return ResponseEntity.ok().build();
        }
    }
}
