package net.developer.webappgame.service;


import net.developer.webappgame.model.Fight;
import net.developer.webappgame.model.FightProgress;
import net.developer.webappgame.model.User;
import net.developer.webappgame.repository.*;

import java.util.ArrayList;
import java.util.List;

public class FightServiceImpl implements FightService {

    UserRepository userRepository = new UserRepositoryImpl();
    FightRepository fightRepository = new FightRepositoryImpl();
    FightProgressRepository fightProgressRepository = new FightProgressRepositoryImpl();

    private final static int FIGHT_FINISH_STATE = 0;

    private final static int USER_DEFAULT_STATE = 0;

    private final static int WINNER_UPGRADE_RATING = 1;
    private final static int LOSER_UPGRADE_RATING = -1;
    private final static int UPGRADE_HEALTH = 1;
    private final static int UPGRADE_DAMAGE = 1;

    private User selfUser;
    private User enemyUser;
    private User winnerUser;
    private User loseUser;
    private Fight fight;
    private boolean isFinish;

    @Override
    public void init(User selfUser, User enemyUser, Fight fight) {
        this.selfUser = selfUser;
        this.enemyUser = enemyUser;
        this.fight = fight;
        this.isFinish = false;
    }

    @Override
    public List<String> hit() {

        int healthAfterHit;

        if (!isAlive(selfUser)) {
            winnerUser = enemyUser;
            loseUser = selfUser;
            finish();
        } else {
            healthAfterHit = enemyUser.getFightHealth() - selfUser.getDamage();
            enemyUser.setFightHealth((healthAfterHit >= 0) ? healthAfterHit : 0);
            userRepository.update(enemyUser);
            fightProgressRepository.create(new FightProgress(0, fight.getId(), selfUser.getId(), selfUser.getDamage()));
        }

        if (!isAlive(enemyUser)) {
            winnerUser = selfUser;
            loseUser = enemyUser;
            finish();
        }

        selfUser = userRepository.loadByLogin(selfUser.getLogin());
        enemyUser = userRepository.loadByLogin(enemyUser.getLogin());

        return getLog();
    }

    @Override
    public void finish() {
        fight = fightRepository.loadFightById(fight.getId());
        if (fight.getState() != FIGHT_FINISH_STATE) {
            fight.setState(FIGHT_FINISH_STATE);
            fightRepository.update(fight);
            winnerUser.setState(USER_DEFAULT_STATE);
            loseUser.setState(USER_DEFAULT_STATE);
            updateUserAfterFight(winnerUser, WINNER_UPGRADE_RATING, UPGRADE_HEALTH, UPGRADE_DAMAGE);
            updateUserAfterFight(loseUser, LOSER_UPGRADE_RATING, UPGRADE_HEALTH, UPGRADE_DAMAGE);
        }

        isFinish = true;
    }

    @Override
    public boolean isFinish() {
        return this.isFinish;
    }

    @Override
    public User getWinner() {
        return winnerUser;
    }

    @Override
    public User getSelfUser() {
        return selfUser;
    }

    @Override
    public User getEnemyUser() {
        return enemyUser;
    }

    private boolean isAlive(User user) {
        user = userRepository.loadByLogin(user.getLogin());

        if (user.getFightHealth() > 0) {
            return true;
        }

        return false;
    }

    @Override
    public List<String> getLog() {
        List<String> log = new ArrayList<>();
        List<FightProgress> fightProgressList = fightProgressRepository.loadProgressByFightId(fight.getId());
        for (FightProgress item : fightProgressList) {
            String s = (item.getUserID() == selfUser.getId()) ? "You hit " + enemyUser.getLogin() + " on " + item.getDamage() + " damage" : enemyUser.getLogin() + " hit You on " + item.getDamage() + " damage";
            log.add(s);
        }
        if (isFinish) {
            String s = (selfUser.getId() == winnerUser.getId()) ? "You kill " + enemyUser.getLogin() : winnerUser.getLogin() + " kill You";
            log.add(s);
        }

        return log;
    }

    private void updateUserAfterFight(User user, int rating, int health, int damage) {
        user.setRating(user.getRating() + rating);
        user.setHealth(user.getHealth() + health);
        user.setDamage(user.getDamage() + damage);

        userRepository.update(user);
    }
}
