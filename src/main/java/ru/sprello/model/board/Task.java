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
    @JsonView({Views.PrivateBoard.class, Views.TaskInfo.class})
    private Long id;

    @Column(name = "name")
    @JsonView({Views.PrivateBoard.class, Views.TaskInfo.class})
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "parent")
    @JsonView({Views.PrivateBoard.class, Views.TaskInfo.class})
    private Set<TaskElement> elements;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonView(Views.TaskInfo.class)
    private Board board;
}

