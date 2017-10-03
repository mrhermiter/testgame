package net.developer.webappgame.repository;


import java.util.List;

public interface QueryInfoRepository {
    void startWatching();

    List<Integer> stopWatching();
}
