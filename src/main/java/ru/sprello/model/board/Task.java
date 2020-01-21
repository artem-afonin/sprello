package ru.sprello.model.board;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import ru.sprello.utils.Views;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.PrivateBoard.class)
    private Long id;

    @Column(name = "name")
    @JsonView(Views.PrivateBoard.class)
    private String name;

    @OneToMany
    @JsonView(Views.PrivateBoard.class)
    private Set<TaskElement> elements;

    @ManyToOne
    @JsonView(Views.PrivateBoard.class)
    private Board board;
}

