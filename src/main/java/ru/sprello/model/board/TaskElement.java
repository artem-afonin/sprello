package ru.sprello.model.board;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.sprello.utils.Views;

import javax.persistence.*;

/**
 * Модель, описывающая подзадачу в {@link Task}
 *
 * @see Task
 * @see Board
 */
@Data
@Entity
@Table(name="task_elem")
public class TaskElement {
    /**
     * Уникальный идентификатор подзадачи
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({Views.PrivateBoard.class, Views.TaskInfo.class, Views.TaskElementInfo.class})
    @Getter
    @Setter
    private Long id;

    /**
     * Текст подзадачи
     */
    @Column(name = "text")
    @JsonView({Views.PrivateBoard.class, Views.TaskInfo.class, Views.TaskElementInfo.class})
    @Getter
    @Setter
    private String text;

    /**
     * Цвет подзадачи
     *
     * @see Color
     */
    @Column(name = "color")
    @Enumerated(EnumType.STRING)
    @JsonView({Views.PrivateBoard.class, Views.TaskInfo.class, Views.TaskElementInfo.class})
    @Getter
    @Setter
    private Color color;

    /**
     * Главная задача, частью которой является данная модель
     *
     * @see Task
     */
    @ManyToOne
    @JsonView(Views.TaskElementInfo.class)
    @Getter
    @Setter
    private Task parent;
}
