package net.developer.webappgame.model;


public class User {

    private int id;
    private String login;
    private String password;
    private int rating;
    private int damage;
    private int health;

    public User(){}

    public User(int id, String login, String password, int rating, int damage, int health) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.rating = rating;
        this.damage = damage;
        this.health = health;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", rating=" + rating +
                ", damage=" + damage +
                ", health=" + health +
                '}';
    }
}
