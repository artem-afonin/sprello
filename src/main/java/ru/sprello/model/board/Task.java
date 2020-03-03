package ru.sprello.model.board;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import ru.sprello.utils.Views;

import javax.persistence.*;
import java.util.Set;

/**
 * Модель, описывающая прикреплённую к доске задачу
 *
 * @author Vasiliy Vintovkin
 * @see Board
 */
@Entity
@Table(name = "task")
public class Task {
    /**
     * Уникальный идентификатор задачи
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({Views.PrivateBoard.class, Views.TaskInfo.class, Views.TaskElementInfo.class})
    @Getter
    @Setter
    private Long id;

    /**
     * Название задачи
     */
    @Column(name = "name")
    @JsonView({Views.PrivateBoard.class, Views.TaskInfo.class, Views.TaskElementInfo.class})
    @Getter
    @Setter
    private String name;

    /**
     * Список подзадач, из которых состоит данная задача
     *
     * @see TaskElement
     */
    @OneToMany(orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "parent")
    @JsonView({Views.PrivateBoard.class, Views.TaskInfo.class})
    @Getter
    @Setter
    private Set<TaskElement> elements;

    /**
     * Доска, в которой находится данная задача
     *
     * @see Board
     */
    @ManyToOne
    @JsonView(Views.TaskInfo.class)
    @Getter
    @Setter
    private Board board;
}

