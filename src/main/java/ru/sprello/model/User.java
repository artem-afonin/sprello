package ru.sprello.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import ru.sprello.model.board.Board;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "usr")
public class User {
    @Id
    @JsonView(Views.PublicSimple.class)
    private String id;

    @JsonView(Views.PublicSimple.class)
    private String name;

    @JsonView(Views.PublicSimple.class)
    private String userpic;

    @JsonView(Views.PublicExtendedUser.class)
    private LocalDateTime lastVisit;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @NotNull
    private Set<Role> roles;

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    @JsonView(Views.PublicExtendedUser.class)
    private Set<Board> boards;
}
