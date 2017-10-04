package net.developer.webappgame.repository;

import net.developer.webappgame.model.User;

/**
 * Users access object
 */
public interface UserRepository {

    /**
     * Create new user
     * @param user user object
     */
    void create(User user);

    /**
     * Update user
     * @param user updating user object
     */
    void update(User user);

    /**
     * Load user by login
     * @param login users login
     * @return user object
     */
    User loadByLogin(String login);

    /**
     * Find random user other than parameter
     * @param user user object
     * @return finder user object
     */
    User findRandomWaitUserLessUser(User user);

    /**
     * Find a user other than this in common fight
     * @param user user object
     * @return finder user object
     */
    User findEnemyUser(User user);
}
