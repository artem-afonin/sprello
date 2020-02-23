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
import ru.sprello.model.board.Task;
import ru.sprello.repo.BoardRepository;
import ru.sprello.repo.TaskRepository;
import ru.sprello.utils.Views;

import java.util.Collections;
import java.util.Optional;

/**
 * REST контроллер, контролирующий доступ к сведениям о {@link Task}.
 */
@RestController
@RequestMapping(Application.apiUrl + "board/task")
public class BoardTasksController {
    private static final Logger LOG = Logger.getLogger(BoardTasksController.class);
    private final TaskRepository taskRepository;
    private final BoardRepository boardRepository;

    @Autowired
    public BoardTasksController(TaskRepository taskRepository, BoardRepository boardRepository) {
        this.taskRepository = taskRepository;
        this.boardRepository = boardRepository;
    }

    /**
     * Обработчик POST маппинга, реализующий добавление новой задачи на доске
     *
     * @param user    пользователь, создающий задачу
     * @param boardId уникальный идентификатор доски
     * @param name    название задачи
     *
     * @return HTTPResponse<br />
     * <b>status code: 404</b> в случае отсутствия доски<br/>
     * <b>status code: 403</b> в случае отсутствия прав у user на совершение запроса<br/>
     * <b>status code: 200</b> если задача создана успешно
     */
    @PostMapping
    @JsonView(Views.TaskInfo.class)
    public ResponseEntity<?> addNewTask(
            @AuthenticationPrincipal User user,
            @RequestParam Long boardId,
            @RequestParam String name
    ) {
        Optional<Board> optionalBoard = boardRepository.findById(boardId);
        Board board;
        if (optionalBoard.isPresent()) {
            board = optionalBoard.get();
        } else {
            return ResponseEntity.notFound().build();
        }

        if (!board.containsUser(user))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        Task newTask = new Task();
        if (!name.equals("")) newTask.setName(name);
        newTask.setElements(Collections.emptySet());
        newTask.setBoard(board);

        newTask = taskRepository.save(newTask);
        return ResponseEntity.ok(newTask);
    }

    /**
     * Обработчик PATCH маппинга, реализующий обновление данных о задаче.
     *
     * @param user   пользователь, редактирующий задачу
     * @param taskId уникальный идентификатор задачи
     * @param name   новое название задачи
     *
     * @return HTTPResponse<br />
     * <b>status code: 404</b> в случае отсутствия доски<br/>
     * <b>status code: 403</b> в случае отсутствия прав у user на совершение запроса<br/>
     * <b>status code: 200</b> если задача успешно обновлена
     */
    @PatchMapping
    @JsonView(Views.TaskInfo.class)
    public ResponseEntity<?> updateTask(
            @AuthenticationPrincipal User user,
            @RequestParam Long taskId,
            @RequestParam(required = false) String name
    ) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        Task task;
        if (optionalTask.isPresent()) {
            task = optionalTask.get();
        } else {
            return ResponseEntity.notFound().build();
        }

        if (!task.getBoard().containsUser(user))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        if (name != null) task.setName(name);

        task = taskRepository.save(task);
        return ResponseEntity.ok(task);
    }

    /**
     * Обработчик DELETE маппинга, реализующий удаление задачи из доски.
     *
     * @param user   пользователь, удаляющуй задачу
     * @param taskId уникальный идентификатор задачи
     *
     * @return HTTPResponse<br />
     * <b>status code: 404</b> в случае отсутствия доски<br/>
     * <b>status code: 403</b> в случае отсутствия прав у user на совершение запроса<br/>
     * <b>status code: 200</b> если задача удалена успешно
     */
    @DeleteMapping
    @JsonView(Views.TaskInfo.class)
    public ResponseEntity<?> deleteTask(
            @AuthenticationPrincipal User user,
            @RequestParam Long taskId
    ) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        Task task;
        if (optionalTask.isPresent()) {
            task = optionalTask.get();
        } else {
            return ResponseEntity.notFound().build();
        }

        if (!task.getBoard().containsUser(user))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        taskRepository.delete(task);
        return ResponseEntity.ok().build();
    }
}
