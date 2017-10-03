package net.developer.webappgame.repository;


import net.developer.webappgame.builder.PreparedStatementBuilder;
import net.developer.webappgame.model.FightProgress;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FightProgressRepositoryImpl extends ConnectionUtil implements FightProgressRepository {

    private static final String CREATE_PROGRESS_ITEM_SQL = "INSERT INTO fights_progress (fightID, userID, damage) VALUES (?, ?, ?)";
    private static final String LOAD_FIGHT_PROGRESS_SQL = "SELECT * FROM fights_progress WHERE fightID=?";

    @Override
    public void create(FightProgress fightProgress) {

        PreparedStatementBuilder preparedStatementBuilder = new PreparedStatementBuilder(CREATE_PROGRESS_ITEM_SQL) {
            @Override
            protected void fillStatement(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, fightProgress.getFightID());
                preparedStatement.setInt(2, fightProgress.getUserID());
                preparedStatement.setInt(3, fightProgress.getDamage());
            }
        };

        executeUpdate(preparedStatementBuilder);
        connectionClose();
    }

    @Override
    public List<FightProgress> loadProgressByFightId(int fightId) {

        List<FightProgress> progresses = new ArrayList<>();
        ResultSet resultSet;

        PreparedStatementBuilder preparedStatementBuilder = new PreparedStatementBuilder(LOAD_FIGHT_PROGRESS_SQL) {
            @Override
            protected void fillStatement(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, fightId);
            }
        };

        try {
            resultSet = execute(preparedStatementBuilder);
            if (!resultSet.isBeforeFirst()) {
                connectionClose();
                return progresses;
            }
            while (resultSet.next()) {

                progresses.add(new FightProgress(
                        resultSet.getInt("id"),
                        resultSet.getInt("fightID"),
                        resultSet.getInt("userID"),
                        resultSet.getInt("damage")
                ));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionClose();
        }

        return progresses;
    }
}
