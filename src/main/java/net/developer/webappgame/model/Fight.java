package net.developer.webappgame.model;

/**
 * Fight model
 */
public class Fight {
    private int id;
    private int state;

    public Fight() {

    }

    public Fight(int id, int state) {
        this.id = id;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
