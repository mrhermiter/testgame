package net.developer.webappgame.repository;

import net.developer.webappgame.builder.PreparedStatementBuilder;
import net.developer.webappgame.model.User;

import javax.enterprise.context.ApplicationScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@ApplicationScoped

public class UserRepositoryImpl implements UserRepository {

    private final static String SAVE_USER_SQL = "INSERT INTO users (login, password, rating, damage, health) VALUES (?, ?, ?, ?, ?)";
    private final static String LOAD_USER_SQL = "SELECT * FROM users WHERE login=?";

    DataSource dataSource;

    {
        InitialContext initContext = null;
        try {
            initContext = new InitialContext();
            dataSource = (DataSource) initContext.lookup("java:comp/env/jdbc/testDB");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(User user) {

        PreparedStatementBuilder preparedStatementBuilder = new PreparedStatementBuilder(SAVE_USER_SQL) {
            @Override
            protected void preparePrepared(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, user.getLogin());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setInt(3, user.getRating());
                preparedStatement.setInt(4, user.getDamage());
                preparedStatement.setInt(5, user.getHealth());
            }
        };

        executeUpdate(preparedStatementBuilder);

    }

    @Override
    public User loadByLogin(String login) {

        User user = null;
        ResultSet resultSet = null;

        PreparedStatementBuilder preparedStatementBuilder = new PreparedStatementBuilder(LOAD_USER_SQL) {
            @Override
            protected void preparePrepared(PreparedStatement preparedStatement) throws SQLException {
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
                    resultSet.getInt("health")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }


    private ResultSet execute(PreparedStatementBuilder preparedStatementBuilder) {

        ResultSet resultSet = null;

        try {
            Connection connection = dataSource.getConnection();
            resultSet = preparedStatementBuilder.build(connection).executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    private int executeUpdate(PreparedStatementBuilder preparedStatementBuilder) {

        int result = 0;

        try {
            Connection connection = dataSource.getConnection();
            result = preparedStatementBuilder.build(connection).executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
