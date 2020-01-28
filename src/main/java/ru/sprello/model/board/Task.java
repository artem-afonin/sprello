package ru.sprello.model.board;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import ru.sprello.utils.Views;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({Views.PrivateBoard.class, Views.TaskInfo.class})
    @Getter
    @Setter
    private Long id;

    @Column(name = "name")
    @JsonView({Views.PrivateBoard.class, Views.TaskInfo.class})
    @Getter
    @Setter
    private String name;

    @OneToMany(orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "parent")
    @JsonView({Views.PrivateBoard.class, Views.TaskInfo.class})
    @Getter
    @Setter
    private Set<TaskElement> elements;

    @ManyToOne
    @JsonView(Views.TaskInfo.class)
    @Getter
    @Setter
    private Board board;
}

