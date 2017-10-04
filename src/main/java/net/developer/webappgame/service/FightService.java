package net.developer.webappgame.service;

import net.developer.webappgame.model.Fight;
import net.developer.webappgame.model.User;

import java.util.List;

/**
 * Fight service
 */
public interface FightService {

    /**
     * Initialisation fight
     * @param selfUser self user object
     * @param enemyUser enemy user object
     * @param fight fight object
     */
    void init(User selfUser, User enemyUser, Fight fight);

    /**
     * Hit action
     * @return list results hits
     */
    List<String> hit();

    /**
     * Finish fight
     */
    void finish();

    /**
     * Finish state of fight
     * @return is finished fight
     */
    boolean isFinish();

    /**
     * Get winner of fight
     * @return winner user object
     */
    User getWinner();

    /**
     * Get self user
     * @return self user object
     */
    User getSelfUser();

    /**
     * Get enemy user
     * @return enemy user object
     */
    User getEnemyUser();

    /**
     * Get log of fight
     * @return log of fight
     */
    List<String> getLog();
}
