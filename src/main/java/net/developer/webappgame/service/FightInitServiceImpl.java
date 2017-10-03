package net.developer.webappgame.service;


import net.developer.webappgame.model.Fight;
import net.developer.webappgame.model.User;
import net.developer.webappgame.repository.FightRepository;
import net.developer.webappgame.repository.FightRepositoryImpl;
import net.developer.webappgame.repository.UserRepository;
import net.developer.webappgame.repository.UserRepositoryImpl;

public class FightInitServiceImpl implements FightInitService {

    UserRepository userRepository = new UserRepositoryImpl();
    FightRepository fightRepository = new FightRepositoryImpl();

    private final static int USER_DEFAULT_STATE = 0;
    private final static int USER_READY_TO_FIGHT_STATE = 1;
    private final static int USER_IN_FIGHT_STATE = 2;

    private final static int FIGHT_STARTED_STATE = 1;

    @Override
    public synchronized FightService init(User user) {
        FightService fightService;
        User enemyUser = null;
        Fight fight = null;
        user = userRepository.loadByLogin(user.getLogin());
        user.setFightHealth(user.getHealth());

        if (user.getState() == USER_READY_TO_FIGHT_STATE) {
            return null;
        }

        if (user.getState() == USER_DEFAULT_STATE) {
            user.setState(USER_READY_TO_FIGHT_STATE);
            userRepository.update(user);
            enemyUser = userRepository.findRandomWaitUserLessUser(user);

            if (enemyUser == null) {
                return null;
            }
            enemyUser.setFightHealth(enemyUser.getHealth());
            enemyUser.setState(USER_IN_FIGHT_STATE);
            user.setState(USER_IN_FIGHT_STATE);
            fight = new Fight();
            fight.setState(FIGHT_STARTED_STATE);
            fightRepository.create(fight);
            enemyUser.setFightId(fight.getId());
            user.setFightId(fight.getId());

            userRepository.update(user);
            userRepository.update(enemyUser);
        }

        if (user.getState() == USER_IN_FIGHT_STATE) {
            enemyUser = userRepository.findEnemyUser(user);
            fight = fightRepository.loadFightById(user.getFightId());
        }

        fightService = new FightServiceImpl();
        fightService.init(user, enemyUser, fight);

        return fightService;
    }
}
