package ru.sprello.controller.board;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.sprello.model.board.Board;
import ru.sprello.model.User;
import ru.sprello.repo.BoardRepository;

import java.util.List;

@RestController
@RequestMapping("api/v1/board")
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
     * @param type тип Board, которые необходимо получить <br/>
     *             <i>Возможные значения: </i><b>"all", "own"</b>
     * @param user пользователь, который выполняет запрос
     *
     * @return список Board, соответствующий параметрам запроса
     */
    @GetMapping
    public List<Board> getAll(@RequestBody(required = false) String type, @AuthenticationPrincipal User user) {
        if (type == null) type = "all";
        List<Board> boards = null;
        if ("all".equals(type)) {
            boards = boardRepository.findAllByIsPrivateFalse();
            LOG.info("GET[type=all] boards for user with id " + user.getId());
        } else if ("own".equals(type)) {
            boards = boardRepository.findAllByUsersContaining(user);
            LOG.info("GET[type=own] boards for user with id " + user.getId());
        } else {
            LOG.warn("Invalid parameter \"type\" received");
        }

        return boards;
    }

    /**
     * Обработчик GET маппинга для получения данных о Board по его id
     *
     * @param id   уникальный идентификатор Board
     * @param user пользователь, запрашивающий данные
     *
     * @return <b>header: 200; body: Board</b> в случае существования Board и наличия прав на её просмотр у user<br/>
     * <b>header: 403</b> в случае отсутствия у пользователя прав на просмотр<br/>
     * <b>header: 404</b> если Board не существует
     */
    @GetMapping("{id}")
    @ResponseBody
    public ResponseEntity<?> getBoard(@PathVariable Long id, @AuthenticationPrincipal User user) {
        Board board = boardRepository.findById(id);
        if (board != null) {
            if (!board.getIsPrivate() || board.getUsers().contains(user)) {
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
     * @param board объект из тела запроса
     * @param user  создатель Board, <i>становится его первым участником</i>
     *
     * @return <b>header: 200; body: Board</b> в случае успешного создания и сохранения в базу данных<br/>
     * <b>header: 400</b> в случае отсутствия параметров "name" или "isPrivate", а также если "name" - пустая строка<br/>
     */
    @PostMapping
    @ResponseBody
    public ResponseEntity<?> createBoard(@RequestBody Board board, @AuthenticationPrincipal User user) {
        if (board.getName() == null || board.getName().equals("")
                || board.getIsPrivate() == null) {
            return ResponseEntity.badRequest().build();
        }
        board.setUsers(List.of(user));
        board.setTasks(List.of());
        board.setMessages(List.of());

        boardRepository.save(board);
        return ResponseEntity.ok(board);
    }

    /**
     * Обработчик PATCH маппинга для частичного или полного обновления Board<br/>
     * <b>Обновляются только обычные поля. Поля коллекции обновлены не будут</b>
     *
     * @param id           уникальный идентификатор Board
     * @param boardFromUsr объект из тела запроса
     * @param user         пользователь, выполняющий запрос
     *
     * @return <b>header: 200; body: Board</b> в случае успешного обновления данных
     * <b>header: 403</b> если у пользователя нет прав для изменения Board<br/>
     * <b>header: 404</b> если Board отсутствует в базе данных
     */
    // TODO реализовать обновление списков в специальных контроллерах для Task, User и Message
    @PatchMapping("{id}")
    public ResponseEntity<?> updateBoard(@PathVariable Long id, @RequestBody Board boardFromUsr, @AuthenticationPrincipal User user) {
        Board boardFromDb = boardRepository.findById(id);
        if (boardFromDb == null) {
            return ResponseEntity.notFound().build();
        } else if (boardFromDb.getIsPrivate() && !boardFromDb.getUsers().contains(user)) {
            // если борда приватная, а юзер в ней не состоит - 403 FORBIDDEN
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } else {
            String name = boardFromUsr.getName();
            Boolean isPrivate = boardFromUsr.getIsPrivate();
            if (name != null)
                boardFromDb.setName(name);
            if (isPrivate != null)
                boardFromDb.setIsPrivate(isPrivate);
            boardRepository.save(boardFromDb);
            return ResponseEntity.ok(boardFromDb);
        }
    }

    /**
     * Обработчик DELETE маппинга для удаления Board из базы данных
     *
     * @param id   уникальный идентификатор Board
     * @param user пользователь, выполняющий запрос
     *
     * @return <b>header: 200</b> если удаление прошло успешно<br/>
     * <b>header: 403</b> в случае отсутствия у пользователя прав на удаление данных
     */
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable Long id, @AuthenticationPrincipal User user) {
        Board board = boardRepository.findById(id);
        if (!board.getUsers().contains(user)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } else {
            boardRepository.delete(board);
            return ResponseEntity.ok().build();
        }
    }
}
