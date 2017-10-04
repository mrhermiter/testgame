package net.developer.webappgame.repository;


import net.developer.webappgame.builder.PreparedStatementBuilder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryInfoRepositoryImpl extends ConnectionUtil implements QueryInfoRepository {

    private final static String SET_PROFILING_1_SQL = "set profiling=1";
    private final static String SET_PROFILING_0_SQL = "set profiling=0";
    private final static String SET_PROFILING_HISTORY_SIZE_0_SQL = "set profiling_history_size=0";
    private final static String SET_PROFILING_HISTORY_SIZE_100_SQL = "set profiling_history_size=100";
    private final static String SHOW_PROFILES_SQL = "show profiles";


    @Override
    public void startWatching() {
        clear(SET_PROFILING_0_SQL);
        clear(SET_PROFILING_HISTORY_SIZE_0_SQL);
        clear(SET_PROFILING_HISTORY_SIZE_100_SQL);

        PreparedStatementBuilder preparedStatementBuilder = new PreparedStatementBuilder(SET_PROFILING_1_SQL) {
            @Override
            protected void fillStatement(PreparedStatement preparedStatement) throws SQLException {

            }
        };

        executeUpdate(preparedStatementBuilder);
        connectionClose();
    }

    @Override
    public List<Integer> stopWatching() {
        List<Integer> result = new ArrayList<>();
        ResultSet resultSet;

        PreparedStatementBuilder preparedStatementBuilder = new PreparedStatementBuilder(SHOW_PROFILES_SQL) {
            @Override
            protected void fillStatement(PreparedStatement preparedStatement) throws SQLException {

            }
        };

        try {
            resultSet = execute(preparedStatementBuilder);
            if (!resultSet.isBeforeFirst()) {
                connectionClose();
                return result;
            }
            while (resultSet.next()) {
                int sec = (int) (resultSet.getDouble(2) * 1000);
                result.add(sec);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionClose();
        }

        return result;
    }

    private void clear(String sql) {
        PreparedStatementBuilder preparedStatementBuilder = new PreparedStatementBuilder(sql) {
            @Override
            protected void fillStatement(PreparedStatement preparedStatement) throws SQLException {

            }
        };

        executeUpdate(preparedStatementBuilder);
        connectionClose();
    }

}
