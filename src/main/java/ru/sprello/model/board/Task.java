package ru.sprello.model.board;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    @NotBlank
    @NotNull
    private String name;
    @OneToMany
    @NotNull
    private Set<TaskElement> elements;
    @ManyToOne
    @NotNull
    private Board board;
}

