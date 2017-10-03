package net.developer.webappgame.service;

import net.developer.webappgame.model.User;
import net.developer.webappgame.repository.UserRepository;
import net.developer.webappgame.repository.UserRepositoryImpl;


public class LoginServiceImpl implements LoginService {

    private UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public User loginUser(User user) {
        User searchUser = userRepository.loadByLogin(user.getLogin());
        if (searchUser == null) {
            user = fillNewUser(user);
            userRepository.create(user);

            return user;
        }

        if (user.getPassword().equals(searchUser.getPassword())) {

            return searchUser;
        }

        return null;
    }

    private User fillNewUser(User user) {

        user.setDamage(10);
        user.setHealth(100);
        user.setRating(0);

        return user;
    }
}
