package net.developer.webappgame.repository;


import java.util.List;

/**
 * Query information repository
 */
public interface QueryInfoRepository {

    /**
     * Start collecting queries information
     */
    void startWatching();

    /**
     * Stop collecting query information
     * @return list query durations in ms
     */
    List<Integer> stopWatching();
}
