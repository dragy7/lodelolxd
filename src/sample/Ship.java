package sample;

import javafx.scene.paint.Paint;

import java.util.ArrayList;

public class Ship {
    private double x;
    private double y;
    private double ySpawn;
    private double width;
    private double height;
    private int score = 0;
    public static ArrayList<Ship> ships = new ArrayList<>();

    public Ship(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        ySpawn = y;
        ships.add(this);
    }

    public void update() {
        if (y <= -height) {
            score++;
            respawn();
        }
    }

    public void up() {
        y--;
    }

    public void down() {
        if (y + height + 1 < Controller.gameHeight) {
            y++;
        }
    }

    public void respawn() {
        y = ySpawn;
    }

    public void incrementScore() {
        score++;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getySpawn() {
        return ySpawn;
    }

    public void setySpawn(double ySpawn) {
        this.ySpawn = ySpawn;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
