package ru.sprello.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="task_elem")
public class TaskElement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "text")
    private String text;
    @Column(name = "color")
    private Color color;
    @OneToOne
    private Task parent;
}
