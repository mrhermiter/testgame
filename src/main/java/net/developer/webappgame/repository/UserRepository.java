package net.developer.webappgame.repository;

import net.developer.webappgame.model.Fight;
import net.developer.webappgame.model.User;


public interface UserRepository {

    void create(User user);

    void update(User user);

    User loadByLogin(String login);

    User findRandomWaitUserLessUser(User user);

    User findEnemyUser(User user);
}
