package ru.sprello.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.sprello.model.board.Board;
import ru.sprello.utils.Views;

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
    @Getter
    @Setter
    private String id;

    @JsonView(Views.PublicSimple.class)
    @Getter
    @Setter
    private String name;

    @JsonView(Views.PublicSimple.class)
    @Getter
    @Setter
    private String userpic;

    @JsonView(Views.PublicSimple.class)
    @Getter
    @Setter
    private LocalDateTime lastVisit;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @NotNull
    @Getter
    @Setter
    private Set<Role> roles;

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    @JsonView(Views.PublicExtendedUser.class)
    @Getter
    @Setter
    private Set<Board> boards;
}
