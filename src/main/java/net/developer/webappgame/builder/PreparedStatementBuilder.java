package net.developer.webappgame.builder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class PreparedStatementBuilder {
    private String sql;

    public PreparedStatementBuilder(String sql) {
        this.sql = sql;
    }

    protected void preparePrepared(PreparedStatement preparedStatement) throws SQLException {
    }

    public PreparedStatement build(Connection connection) throws SQLException {

        PreparedStatement returnable = connection.prepareStatement(sql);
        preparePrepared(returnable);

        return returnable;
    }
}
