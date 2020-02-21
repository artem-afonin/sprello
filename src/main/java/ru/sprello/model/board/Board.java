package ru.sprello.model.board;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import ru.sprello.model.Message;
import ru.sprello.model.User;
import ru.sprello.utils.Views;

import javax.persistence.*;
import java.util.Set;

/**
 * Модель, описывающая доску для пользователей.
 * <p>
 * Является главной сущностью в проекте, остальные сущности
 * в той или иной степени зависят от Board
 *
 * @author Vasiliy Vintovkin
 * @see User
 * @see Task
 */
@Entity
@Table(name = "board")
public class Board {
    /**
     * Уникальный идентификатор доски
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.PublicSimple.class)
    @Getter
    @Setter
    private Long id;

    /**
     * Название доски
     */
    @Column(name = "name")
    @JsonView(Views.PublicSimple.class)
    @Getter
    @Setter
    private String name;

    /**
     * Коллекция пользователей, состоящих в данной доске
     * <p>
     * Имеют полный доступ к доске и всем данным о ней
     *
     * @see User
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "board_users",
            joinColumns = @JoinColumn(name = "BOARD_ID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "id")
    )
    @JsonView(Views.PublicExtendedBoard.class)
    @Getter
    @Setter
    private Set<User> users;

    /**
     * Коллекция пользователей, запращивающих доступ к доске.
     * <p>
     * После одобрения запроса на вступление - пользователь перемещается
     * из коллекции requestors в коллекцию users
     *
     * @see User
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "board_requestors",
            joinColumns = @JoinColumn(name = "BOARD_ID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "id")
    )
    @JsonView(Views.PrivateBoard.class)
    @Getter
    @Setter
    private Set<User> requestors;

    /**
     * Коллекция задач, прикреплённых в доске
     *
     * @see Task
     */
    @OneToMany(orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "board")
    @JsonView(Views.PrivateBoard.class)
    @Getter
    @Setter
    private Set<Task> tasks;

    /**
     * Коллекция сообщений в чате доски
     *
     * @see Message
     */
    @OneToMany(orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "board")
    @JsonView(Views.PrivateBoard.class)
    @Getter
    @Setter
    private Set<Message> messages;

    /**
     * Проверяет наличие пользователя user в данной доске
     *
     * @param user пользователь
     *
     * @return true - если пользователь является участником доски,
     * иначе false
     */
    public boolean containsUser(User user) {
        String id = user.getId();
        return this.getUsers().stream().map(User::getId).anyMatch(id::equals);
    }

    /**
     * Проверяет запрашивает ли пользователь доступ к доске
     *
     * @param user пользователь
     *
     * @return true - если пользователь не является участником, но запрашивает доступ к доске,
     * иначе false
     */
    public boolean containsRequestor(User user) {
        String id = user.getId();
        return this.getRequestors().stream().map(User::getId).anyMatch(id::equals);
    }
}
