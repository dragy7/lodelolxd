package sample;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public Canvas canvas;
    private GraphicsContext gc;
    private ArrayList<String> input = new ArrayList<>();
    private Ship leftShip;
    private Ship rightShip;
    private AnimationTimer gameLoop;
    public static double gameWidth;
    public static double gameHeight;
    private Image image = new Image("sample/spaceship.png");
    private double shipRatio = 0.5;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gc = canvas.getGraphicsContext2D();
        gameWidth = canvas.getWidth();
        gameHeight = canvas.getHeight();
        leftShip = new Ship(canvas.getWidth() / 2 - 100, canvas.getHeight() - 30, image.getWidth() * shipRatio, image.getHeight() * shipRatio);
        rightShip = new Ship(canvas.getWidth() / 2 + 100, canvas.getHeight() - 30, image.getWidth() * shipRatio, image.getHeight() * shipRatio);
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gc.setFill(Paint.valueOf("BLACK"));
                gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                collision();
                movement();
                draw();
            }
        };
        gameLoop.start();
        Debris.spawn(20);
    }

    public void draw() {
        drawShip(leftShip);
        drawShip(rightShip);
        drawDebris();
        drawScore();
    }

    public void drawShip(Ship ship) {
        gc.drawImage(new Image("sample/spaceship.png"), ship.getX(), ship.getY(), ship.getWidth(), ship.getHeight());
        ship.update();
    }

    public void drawScore() {
        gc.setFill(Paint.valueOf("WHITE"));
        gc.setFont(new Font(48));
        gc.fillText(String.valueOf(leftShip.getScore()), leftShip.getX() - 50, canvas.getHeight() - 60);
        gc.fillText(String.valueOf(rightShip.getScore()), rightShip.getX() + 50, canvas.getHeight() - 60);
    }

    public void drawDebris() {
        Debris.debrisArrayList.forEach(debris -> {
            debris.update();
            gc.setFill(Paint.valueOf("WHITE"));
            gc.fillRect(debris.getX(), debris.getY(), debris.getWidth(), debris.getHeight());
        });
    }

    public void movement() {
        if (input.contains("W")) {
            leftShip.up();
        }
        if (input.contains("S")) {
            leftShip.down();
        }
        if (input.contains("UP")) {
            rightShip.up();
        }
        if (input.contains("DOWN")) {
            rightShip.down();
        }
    }

    public void collision() {
        Ship.ships.forEach(ship -> {
            Debris.debrisArrayList.forEach(debris -> {
                if (
                        ship.getX() < debris.getX() + debris.getWidth() &&
                        ship.getX() + ship.getWidth() > debris.getX() &&
                        ship.getY() < debris.getY() + debris.getHeight() &&
                        ship.getY() + ship.getHeight() > debris.getY()
                ) {
                    ship.respawn();
                }
            });
        });
    }

    public void keyPressed(KeyEvent keyEvent) {
        String code = keyEvent.getCode().toString();
        if (code.equals("W") || code.equals("S") || code.equals("UP") || code.equals("DOWN")) {
            if (!input.contains(code)) {
                input.add(code);
            }
        }
    }

    public void keyReleased(KeyEvent keyEvent) {
        String code = keyEvent.getCode().toString();
        input.remove(code);
    }



}
