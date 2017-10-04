package net.developer.webappgame.service;


import net.developer.webappgame.model.User;

/**
 * Generate fight service
 */
public interface FightInitService {

    /**
     * Generate fight for this user
     * @param user user object
     * @return fight service
     */
    FightService init(User user);
}
