package ru.sprello.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "task")
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @OneToMany
    private List<TaskElement> elements;
    @ManyToOne
    private Board board;
}

