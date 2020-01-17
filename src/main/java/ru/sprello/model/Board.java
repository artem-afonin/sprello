package ru.sprello.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name="board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="name")
    @NotBlank
    private String name;
    private Boolean isPrivate;
    @ManyToMany
    private List<User> users;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages;
}
