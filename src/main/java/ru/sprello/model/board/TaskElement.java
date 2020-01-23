package ru.sprello.model.board;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import ru.sprello.utils.Views;

import javax.persistence.*;

@Data
@Entity
@Table(name="task_elem")
public class TaskElement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({Views.PrivateBoard.class, Views.TaskElementInfo.class})
    private Long id;

    @Column(name = "text")
    @JsonView({Views.PrivateBoard.class, Views.TaskElementInfo.class})
    private String text;

    @Column(name = "color")
    @Enumerated(EnumType.STRING)
    @JsonView({Views.PrivateBoard.class, Views.TaskElementInfo.class})
    private Color color;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonView(Views.TaskElementInfo.class)
    private Task parent;
}
