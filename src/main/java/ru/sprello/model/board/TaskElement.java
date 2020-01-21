package ru.sprello.model.board;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import ru.sprello.utils.Views;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name="task_elem")
public class TaskElement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.PrivateBoard.class)
    private Long id;

    @Column(name = "text")
    @JsonView(Views.PrivateBoard.class)
    private String text;

    @Column(name = "color")
    @JsonView(Views.PrivateBoard.class)
    private Color color;

    @ManyToOne
    @JsonView(Views.PrivateBoard.class)
    private Task parent;
}
