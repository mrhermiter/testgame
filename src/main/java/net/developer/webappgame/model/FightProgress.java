package net.developer.webappgame.model;

/**
 * Fight progress model
 */
public class FightProgress {
    private int id;
    private int fightId;
    private int userId;
    private int damage;

    public FightProgress() {

    }

    public FightProgress(int id, int fightId, int userId, int damage) {
        this.id = id;
        this.fightId = fightId;
        this.userId = userId;
        this.damage = damage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFightID() {
        return fightId;
    }

    public void setFightID(int fightID) {
        this.fightId = fightID;
    }

    public int getUserID() {
        return userId;
    }

    public void setUserID(int userId) {
        this.userId = userId;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
