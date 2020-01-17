package ru.sprello.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="name")
    private String name;
    @OneToMany
    private List<User> users;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages;
}
