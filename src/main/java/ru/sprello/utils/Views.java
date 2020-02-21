package ru.sprello.utils;

import com.fasterxml.jackson.annotation.JsonView;
import ru.sprello.model.User;
import ru.sprello.model.board.Board;
import ru.sprello.model.board.Task;
import ru.sprello.model.board.TaskElement;

/**
 * Используется для ограничения доступа к данным моделей через JSON.<br/>
 * Модели аннотируются с помощью {@link JsonView}
 *
 * @author Artem Afonin
 * @see JsonView
 */
public final class Views {
    /**
     * Основные данные для моделей верхнего уровня
     *
     * @see User
     * @see Board
     */
    public interface PublicSimple {
    }

    /**
     * Основные данные для моделей верхнего уровня
     * и расширенные для класса {@link Board}
     *
     * @see User
     * @see Board
     */
    public interface PublicExtendedBoard extends PublicSimple {
    }

    /**
     * Основные данные для моделей верхнего уровня
     * и расширенные для класса {@link User}
     *
     * @see User
     * @see Board
     */
    public interface PublicExtendedUser extends PublicSimple {
    }

    /**
     * Все закрытые данные о доске для её участников
     *
     * @see User
     * @see Board
     */
    public interface PrivateBoard extends PublicExtendedBoard {
    }

    /**
     * Вся информация о задаче в некоторой доске
     *
     * @see Board
     * @see Task
     */
    public interface TaskInfo extends PublicSimple {
    }

    /**
     * Вся информация о подзадаче в некоторой основной задаче
     *
     * @see Board
     * @see Task
     * @see TaskElement
     */
    public interface TaskElementInfo extends PublicSimple {
    }
}
