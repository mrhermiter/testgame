package net.developer.webappgame.builder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class PreparedStatementBuilder {
    private String sql;

    public PreparedStatementBuilder(String sql) {
        this.sql = sql;
    }

    protected abstract void fillStatement(PreparedStatement preparedStatement) throws SQLException;

    public PreparedStatement build(Connection connection) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        fillStatement(preparedStatement);

        return preparedStatement;
    }
}
