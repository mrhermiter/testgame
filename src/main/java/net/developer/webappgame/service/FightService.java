package net.developer.webappgame.service;

import net.developer.webappgame.model.Fight;
import net.developer.webappgame.model.User;

import java.util.List;

public interface FightService {

    void init(User selfUser, User enemyUser, Fight fight);

    List<String> hit();

    void finish();

    boolean isFinish();

    User getWinner();

    User getSelfUser();

    User getEnemyUser();

    List<String> getLog();
}
