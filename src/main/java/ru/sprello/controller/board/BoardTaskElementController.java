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
import ru.sprello.model.board.Color;
import ru.sprello.model.board.Task;
import ru.sprello.model.board.TaskElement;
import ru.sprello.repo.TaskElementRepository;
import ru.sprello.repo.TaskRepository;
import ru.sprello.utils.Views;

import java.util.Optional;

/* Во всех методах этого контроллера
    обязательно делать проверку пользователя
 */
@RestController
@RequestMapping(Application.apiUrl + "board/task/element")
public class BoardTaskElementController {
    private static final Logger LOG = Logger.getLogger(BoardTaskElementController.class);
    private final TaskElementRepository taskElementRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public BoardTaskElementController(TaskElementRepository taskElementRepository, TaskRepository taskRepository) {
        this.taskElementRepository = taskElementRepository;
        this.taskRepository = taskRepository;
    }

    @PostMapping
    @JsonView(Views.TaskElementInfo.class)
    public ResponseEntity<?> addNewTaskElement(
            @AuthenticationPrincipal User user,
            @RequestParam Long taskId,
            @RequestParam String text,
            @RequestParam(required = false) Color color
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

        TaskElement taskElement = new TaskElement();
        if (!"".equals(text)) taskElement.setText(text);
        else taskElement.setText("unnamed");
        if (color != null) taskElement.setColor(color);
        else taskElement.setColor(Color.DEFAULT);
        taskElement.setParent(task);

        taskElement = taskElementRepository.save(taskElement);
        return ResponseEntity.ok(taskElement);
    }

    @PatchMapping
    @JsonView(Views.TaskElementInfo.class)
    public ResponseEntity<?> updateTask(
            @AuthenticationPrincipal User user,
            @RequestParam Long taskElementId,
            @RequestParam(required = false) String text,
            @RequestParam(required = false) Color color
    ) {
        Optional<TaskElement> optionalTaskElement = taskElementRepository.findById(taskElementId);
        TaskElement taskElement;
        if (optionalTaskElement.isPresent()) {
            taskElement = optionalTaskElement.get();
        } else {
            return ResponseEntity.notFound().build();
        }

        if (!taskElement.getParent().getBoard().containsUser(user))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        if (text != null) taskElement.setText(text);
        if (color != null) taskElement.setColor(color);

        taskElement = taskElementRepository.save(taskElement);
        return ResponseEntity.ok(taskElement);
    }

    @DeleteMapping
    @JsonView(Views.TaskElementInfo.class)
    public ResponseEntity<?> deleteTaskElement(
            @AuthenticationPrincipal User user,
            @RequestParam Long taskElementId
    ) {
        Optional<TaskElement> optionalTaskElement = taskElementRepository.findById(taskElementId);
        TaskElement taskElement;
        if (optionalTaskElement.isPresent()) {
            taskElement = optionalTaskElement.get();
        } else {
            return ResponseEntity.notFound().build();
        }

        if (!taskElement.getParent().getBoard().containsUser(user))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        taskElementRepository.delete(taskElement);
        return ResponseEntity.ok().build();
    }
}
