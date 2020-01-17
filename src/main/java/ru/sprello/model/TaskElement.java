package ru.sprello.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name="task_elem")
public class TaskElement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "text")
    @NotBlank
    private String text;
    @Column(name = "color")
    private Color color;
    @ManyToOne
    private Task parent;
}
