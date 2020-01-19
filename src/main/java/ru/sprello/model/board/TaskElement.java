package ru.sprello.model.board;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name="task_elem")
public class TaskElement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "text")
    @NotBlank
    @NotNull
    private String text;
    @Column(name = "color")
    @NotNull
    private Color color;
    @ManyToOne
    @NotNull
    private Task parent;
}
