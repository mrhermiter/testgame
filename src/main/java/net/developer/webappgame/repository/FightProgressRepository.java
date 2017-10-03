package net.developer.webappgame.repository;


import net.developer.webappgame.model.FightProgress;

import java.util.List;

public interface FightProgressRepository {
    void create(FightProgress fightProgress);

    List<FightProgress> loadProgressByFightId(int fightId);
}
