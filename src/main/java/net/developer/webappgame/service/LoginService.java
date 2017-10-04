package net.developer.webappgame.service;


import net.developer.webappgame.model.User;

/**
 * Login service
 */
public interface LoginService {

    /**
     * Login user
     * @param user authenticated user
     * @return logged user or null else
     */
    User loginUser(User user);
}
