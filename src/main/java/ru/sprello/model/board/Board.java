package ru.sprello.model.board;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import ru.sprello.model.Message;
import ru.sprello.model.User;
import ru.sprello.utils.Views;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.PublicSimple.class)
    @Getter
    @Setter
    private Long id;

    @Column(name="name")
    @JsonView(Views.PublicSimple.class)
    @Getter
    @Setter
    private String name;

    @JsonView(Views.PublicSimple.class)
    @Getter
    @Setter
    private Boolean isPrivate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "board_users",
            joinColumns = @JoinColumn(name = "BOARD_ID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "id")
    )
    @JsonView(Views.PublicExtendedBoard.class)
    @Getter
    @Setter
    private Set<User> users;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonView(Views.PrivateBoard.class)
    @Getter
    @Setter
    private Set<Task> tasks;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonView(Views.PrivateBoard.class)
    @Getter
    @Setter
    private Set<Message> messages;
}
