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

/* Во всех методах этого контроллера
    обязательно делать проверку пользователя
 */
@RestController
@RequestMapping(Application.apiUrl + "board/tasks")
public class BoardTasksController {
    private static final Logger LOG = Logger.getLogger(BoardTasksController.class);
    private final TaskRepository taskRepository;
    private final BoardRepository boardRepository;

    @Autowired
    public BoardTasksController(TaskRepository taskRepository, BoardRepository boardRepository) {
        this.taskRepository = taskRepository;
        this.boardRepository = boardRepository;
    }

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
