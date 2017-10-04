package net.developer.webappgame.builder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * Prepared statement builder
 */
public abstract class PreparedStatementBuilder {
    private String sql;

    /**
     * Construct builder
     * @param sql SQL request
     */
    public PreparedStatementBuilder(String sql) {
        this.sql = sql;
    }

    /**
     * Fill statement
     * @param preparedStatement
     * @throws SQLException
     */
    protected abstract void fillStatement(PreparedStatement preparedStatement) throws SQLException;

    /**
     * Build end statement
     * @param connection db connection
     * @return ready statement
     * @throws SQLException
     */
    public PreparedStatement build(Connection connection) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        fillStatement(preparedStatement);

        return preparedStatement;
    }
}
