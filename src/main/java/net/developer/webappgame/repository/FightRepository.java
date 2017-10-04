package net.developer.webappgame.repository;


import net.developer.webappgame.model.Fight;

/**
 * Fights access object
 */
public interface FightRepository {

    /**
     * Create new fight
     * @param fight fight object
     */
    void create(Fight fight);

    /**
     * Update fight
     * @param fight updating fight object
     */
    void update(Fight fight);

    /**
     * Load fight by fightId
     * @param id identifier of fight
     * @return fight object
     */
    Fight loadFightById(int id);
}
