package net.developer.webappgame.repository;


import net.developer.webappgame.model.Fight;


public interface FightRepository {
    void create(Fight fight);

    void update(Fight fight);

    Fight loadFightById(int id);
}
