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

/**
 * REST контроллер, контролирующий доступ к сведениям о {@link TaskElement}.
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

    /**
     * Обработчик POST маппинга, реализующий создание подзадачи для задачи {@link Task}
     *
     * @param user   пользователь, создающий задачу
     * @param taskId уникальный идентификатор родительской основной задачи
     * @param text   текст подзадачи
     * @param color  цвет задачи {@link Color}
     *
     * @return HTTPResponse<br />
     * <b>status code: 404</b> в случае отсутствия родительской задачи<br/>
     * <b>status code: 403</b> в случае отсутствия прав у user на совершение запроса<br/>
     * <b>status code: 200</b> если подзадача создана успешно
     */
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

    /**
     * Обработчик PATCH маппинга, реализующий обновление данных о подзадаче.
     *
     * @param user          пользователь, редактирующий подзадачу
     * @param taskElementId уникальный идентификатор подзадачи
     * @param text          новое водержание подзадачи
     * @param color         новый цвет подзадачи
     *
     * @return HTTPResponse<br />
     * <b>status code: 404</b> в случае отсутствия подзадачи<br/>
     * <b>status code: 403</b> в случае отсутствия прав у user на совершение запроса<br/>
     * <b>status code: 200</b> если подзадача успешно обновлена
     */
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

    /**
     * Обработчик DELETE маппинга, реализующий удаление задачи из доски.
     *
     * @param user          пользователь, удаляющуй задачу
     * @param taskElementId уникальный идентификатор подзадачи
     *
     * @return HTTPResponse<br />
     * <b>status code: 404</b> в случае отсутствия подзадачи<br/>
     * <b>status code: 403</b> в случае отсутствия прав у user на совершение запроса<br/>
     * <b>status code: 200</b> если подзадача удалена успешно
     */
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
