package net.developer.webappgame.repository;

import net.developer.webappgame.model.User;


public interface UserRepository {

    void save (User user);
    User loadByLogin (String login);

}
