package net.developer.webappgame.repository;


import net.developer.webappgame.builder.PreparedStatementBuilder;
import net.developer.webappgame.model.Fight;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FightRepositoryImpl extends ConnectionUtil implements FightRepository {

    private static final String CREATE_FIGHT_SQL = "INSERT INTO fights (state) VALUES (?)";
    private static final String UPDATE_FIGHT_SQL = "UPDATE fights SET state=? WHERE id=?";
    private static final String LOAD_FIGHT_SQL = "SELECT * FROM fights WHERE id=?";

    @Override
    public void create(Fight fight) {

        PreparedStatementBuilder preparedStatementBuilder = new PreparedStatementBuilder(CREATE_FIGHT_SQL) {
            @Override
            protected void fillStatement(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, fight.getState());
            }
        };

        fight.setId((int) executeUpdate(preparedStatementBuilder));
        connectionClose();
    }

    @Override
    public void update(Fight fight) {

        PreparedStatementBuilder preparedStatementBuilder = new PreparedStatementBuilder(UPDATE_FIGHT_SQL) {
            @Override
            protected void fillStatement(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, fight.getState());
                preparedStatement.setInt(2, fight.getId());
            }
        };

        executeUpdate(preparedStatementBuilder);
        connectionClose();
    }

    @Override
    public Fight loadFightById(int id) {
        Fight fight = null;
        ResultSet resultSet;

        PreparedStatementBuilder preparedStatementBuilder = new PreparedStatementBuilder(LOAD_FIGHT_SQL) {
            @Override
            protected void fillStatement(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, id);
            }
        };

        try {
            resultSet = execute(preparedStatementBuilder);
            if (!resultSet.isBeforeFirst()) {
                connectionClose();
                return fight;
            }
            resultSet.next();

            fight = new Fight(
                    resultSet.getInt("id"),
                    resultSet.getInt("state")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionClose();
        }

        return fight;
    }
}
