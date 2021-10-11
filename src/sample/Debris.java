package sample;

import java.util.ArrayList;
import java.util.Random;

public class Debris {
    private double x;
    private double y;
    private double width;
    private double height;
    private boolean direction = false;
    public static ArrayList<Debris> debrisArrayList = new ArrayList<>();

    public Debris(double width, double height) {
        this.width = width;
        this.height = height;
        Random random = new Random();
        int d = random.nextInt(2);
        if (d == 0) {
            direction = true;
        }
        this.x = random.nextInt((int) Controller.gameWidth - 5) + 1;
        this.y = random.nextInt((int) Controller.gameHeight - 100) + 1;
        debrisArrayList.add(this);
    }

    public void respawn() {
        Random random = new Random();
        int x = random.nextInt(2);
        if (x == 0) {
            this.x = 0;
            direction = true;
        } else {
            this.x = Controller.gameWidth;
            direction = false;
        }
        this.y = random.nextInt((int) Controller.gameHeight - 50);
    }

    public static void spawn(int number) {
        for (int i = 0; i < number; i++) {
            debrisArrayList.add(new Debris(20, 5));
        }
    }

    public void update() {
        if (x > -width && x < Controller.gameWidth + width) {
            if (direction) {
                x++;
                return;
            }
            x--;
        } else {
            respawn();
        }
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

    public boolean isDirection() {
        return direction;
    }

    public void setDirection(boolean direction) {
        this.direction = direction;
    }
}
