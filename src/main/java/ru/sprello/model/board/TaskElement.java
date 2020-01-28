package ru.sprello.model.board;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.sprello.utils.Views;

import javax.persistence.*;

@Data
@Entity
@Table(name="task_elem")
public class TaskElement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({Views.PrivateBoard.class, Views.TaskInfo.class, Views.TaskElementInfo.class})
    @Getter
    @Setter
    private Long id;

    @Column(name = "text")
    @JsonView({Views.PrivateBoard.class, Views.TaskInfo.class, Views.TaskElementInfo.class})
    @Getter
    @Setter
    private String text;

    @Column(name = "color")
    @Enumerated(EnumType.STRING)
    @JsonView({Views.PrivateBoard.class, Views.TaskInfo.class, Views.TaskElementInfo.class})
    @Getter
    @Setter
    private Color color;

    @ManyToOne
    @JsonView(Views.TaskElementInfo.class)
    @Getter
    @Setter
    private Task parent;
}
