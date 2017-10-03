package net.developer.webappgame.repository;

import net.developer.webappgame.builder.PreparedStatementBuilder;
import net.developer.webappgame.model.Fight;
import net.developer.webappgame.model.User;

import javax.enterprise.context.ApplicationScoped;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@ApplicationScoped

public class UserRepositoryImpl extends ConnectionUtil implements UserRepository {

    private static final String CREATE_USER_SQL = "INSERT INTO users (login, password, rating, damage, health, fight_health, state, fightID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_USER_SQL = "UPDATE users SET login=?, password=?, rating=?, damage=?, health=?, fight_health=?, state=?, fightID=? WHERE id=?";
    private static final String LOAD_USER_SQL = "SELECT * FROM users WHERE login=?";
    private static final String FIND_RANDOM_WAIT_USER_SQL = "SELECT * FROM users WHERE login<>? AND state=1 ORDER BY RAND() LIMIT 1";
    private static final String FIND_ENEMY_USER_SQL = "SELECT * FROM users WHERE fightID=? AND login<>?";

    @Override
    public void create(User user) {

        PreparedStatementBuilder preparedStatementBuilder = new PreparedStatementBuilder(CREATE_USER_SQL) {
            @Override
            protected void fillStatement(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, user.getLogin());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setInt(3, user.getRating());
                preparedStatement.setInt(4, user.getDamage());
                preparedStatement.setInt(5, user.getHealth());
                preparedStatement.setInt(6, user.getFightHealth());
                preparedStatement.setInt(7, user.getState());
                preparedStatement.setInt(8, user.getFightId());
            }
        };

        user.setId((int) executeUpdate(preparedStatementBuilder));
        connectionClose();
    }

    @Override
    public void update(User user) {

        PreparedStatementBuilder preparedStatementBuilder = new PreparedStatementBuilder(UPDATE_USER_SQL) {
            @Override
            protected void fillStatement(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, user.getLogin());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setInt(3, user.getRating());
                preparedStatement.setInt(4, user.getDamage());
                preparedStatement.setInt(5, user.getHealth());
                preparedStatement.setInt(6, user.getFightHealth());
                preparedStatement.setInt(7, user.getState());
                preparedStatement.setInt(8, user.getFightId());
                preparedStatement.setInt(9, user.getId());
            }
        };

        executeUpdate(preparedStatementBuilder);
        connectionClose();
    }

    @Override
    public User loadByLogin(String login) {

        User user = null;
        ResultSet resultSet;

        PreparedStatementBuilder preparedStatementBuilder = new PreparedStatementBuilder(LOAD_USER_SQL) {
            @Override
            protected void fillStatement(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, login);
            }
        };

        try {
            resultSet = execute(preparedStatementBuilder);
            if (!resultSet.isBeforeFirst()) {
                return user;
            }
            resultSet.next();

            user = new User(
                    resultSet.getInt("id"),
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    resultSet.getInt("rating"),
                    resultSet.getInt("damage"),
                    resultSet.getInt("health"),
                    resultSet.getInt("fight_health"),
                    resultSet.getInt("state"),
                    resultSet.getInt("fightID")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionClose();
        }

        return user;
    }

    @Override
    public User findRandomWaitUserLessUser(User user) {
        User selectedUser = null;
        ResultSet resultSet;

        PreparedStatementBuilder preparedStatementBuilder = new PreparedStatementBuilder(FIND_RANDOM_WAIT_USER_SQL) {
            @Override
            protected void fillStatement(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, user.getLogin());
            }
        };

        try {
            resultSet = execute(preparedStatementBuilder);
            if (!resultSet.isBeforeFirst()) {
                return null;
            }
            resultSet.next();

            selectedUser = new User(
                    resultSet.getInt("id"),
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    resultSet.getInt("rating"),
                    resultSet.getInt("damage"),
                    resultSet.getInt("health"),
                    resultSet.getInt("fight_health"),
                    resultSet.getInt("state"),
                    resultSet.getInt("fightID")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionClose();
        }

        return selectedUser;
    }

    @Override
    public User findEnemyUser(User user) {
        User selectedUser = null;
        ResultSet resultSet;

        PreparedStatementBuilder preparedStatementBuilder = new PreparedStatementBuilder(FIND_ENEMY_USER_SQL) {
            @Override
            protected void fillStatement(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, user.getFightId());
                preparedStatement.setString(2, user.getLogin());
            }
        };

        try {
            resultSet = execute(preparedStatementBuilder);
            if (!resultSet.isBeforeFirst()) {
                return null;
            }
            resultSet.next();

            selectedUser = new User(
                    resultSet.getInt("id"),
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    resultSet.getInt("rating"),
                    resultSet.getInt("damage"),
                    resultSet.getInt("health"),
                    resultSet.getInt("fight_health"),
                    resultSet.getInt("state"),
                    resultSet.getInt("fightID")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionClose();
        }

        return selectedUser;
    }
}
