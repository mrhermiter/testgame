package net.developer.webappgame.model;

/**
 * User model
 */
public class User {

    private int id;
    private String login;
    private String password;
    private int rating;
    private int damage;
    private int health;
    private int fightHealth;
    private int state;
    private int fightId;

    public User() {
    }

    public User(int id, String login, String password, int rating, int damage, int health, int fightHealth, int state, int fightId) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.rating = rating;
        this.damage = damage;
        this.health = health;
        this.fightHealth = fightHealth;
        this.state = state;
        this.fightId = fightId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getFightHealth() {
        return fightHealth;
    }

    public void setFightHealth(int fightHealth) {
        this.fightHealth = fightHealth;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getFightId() {
        return fightId;
    }

    public void setFightId(int fightId) {
        this.fightId = fightId;
    }
}
