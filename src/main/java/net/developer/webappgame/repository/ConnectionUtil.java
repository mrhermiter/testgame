package net.developer.webappgame.repository;


import net.developer.webappgame.builder.PreparedStatementBuilder;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Connection and request to db manager
 */
public class ConnectionUtil {
    Connection connection;
    DataSource dataSource;

    /**
     * Connection pool generate
     */
    {
        InitialContext initContext;
        try {
            initContext = new InitialContext();
            dataSource = (DataSource) initContext.lookup("java:comp/env/jdbc/testDB");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Execute request
     * @param preparedStatementBuilder building statement
     * @return request result set
     */
    protected ResultSet execute(PreparedStatementBuilder preparedStatementBuilder) {


        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            resultSet = preparedStatementBuilder.build(connection).executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    /**
     * Execute update request
     * @param preparedStatementBuilder building statement
     * @return generated key
     */
    protected long executeUpdate(PreparedStatementBuilder preparedStatementBuilder) {


        PreparedStatement preparedStatement;
        long result = 0;


        try {
            connection = dataSource.getConnection();
            preparedStatement = preparedStatementBuilder.build(connection);
            result = preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    result = generatedKeys.getLong(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Close connection
     */
    protected void connectionClose() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
