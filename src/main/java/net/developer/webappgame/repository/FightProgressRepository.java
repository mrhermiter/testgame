package net.developer.webappgame.repository;


import net.developer.webappgame.model.FightProgress;

import java.util.List;

/**
 * Fight progresses access object
 */
public interface FightProgressRepository {

    /**
     * Create new progress item
     * @param fightProgress progress item object
     */
    void create(FightProgress fightProgress);

    /**
     * Load progress items by fightId
     * @param fightId identifier of fight
     * @return
     */
    List<FightProgress> loadProgressByFightId(int fightId);
}
