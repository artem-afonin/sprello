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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "board_users",
            joinColumns = @JoinColumn(name = "BOARD_ID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "id")
    )
    @JsonView(Views.PublicExtendedBoard.class)
    @Getter
    @Setter
    private Set<User> users;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "board_requestors",
            joinColumns = @JoinColumn(name = "BOARD_ID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "id")
    )
    @JsonView(Views.PrivateBoard.class)
    @Getter
    @Setter
    private Set<User> requestors;

    @OneToMany(orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "board")
    @JsonView(Views.PrivateBoard.class)
    @Getter
    @Setter
    private Set<Task> tasks;

    @OneToMany(orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "board")
    @JsonView(Views.PrivateBoard.class)
    @Getter
    @Setter
    private Set<Message> messages;

    public boolean containsUser(User user) {
        String id = user.getId();
        return this.getUsers().stream().map(User::getId).anyMatch(id::equals);
    }

    public boolean containsRequestor(User user) {
        String id = user.getId();
        return this.getRequestors().stream().map(User::getId).anyMatch(id::equals);
    }
}
