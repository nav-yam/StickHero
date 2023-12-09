package navyamtizil.ap_p1;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Math.*;

public class GamePlayController {
    private static final int RESPWAN_PRICE = 2;
    private static int NUM_CHERRIES = 10;
    private static int SCORE = 0;
    private static double STICK_LIMIT = 200;
    @FXML
    public AnchorPane pane;
    @FXML
    public ImageView character;
    @FXML
    public Rectangle Tower1;
    public Button respawnNo;
    public Button respawnYes;
    public Text respawnText;
    public AnchorPane respawnWindow;
    private boolean inverted = false; // Flag for character inversion
    private static final double LEVEL = 500;
    boolean isRespawning;
    @FXML
    private Line stick;
    private boolean isMousePressed = false;
    private boolean isReady = true;
    private Timeline sizeIncreaseTimeline;
    private static GamePlayController gpc;
    Rectangle Tower2;
    Rectangle BonusRect;
    Text scoreCard;

    double TOWER_WIDTH;
    double TOWER_DIST;

    final double BONUS_RECT_WIDTH = 8;
    final double BONUS_RECT_HEIGHT = 13;
    double BONUS_BOUND_BUFFER = (BONUS_RECT_WIDTH / 2) + 2;
    final int BONUS_VALUE = 2;
    int RESPAWN_REMAINING;

    final double STICK_GROW_SPEED = 2.5;
    final double STICK_STROKE_WIDTH = 8;
    Color STICK_COLOR = Color.web("#ff1f1f");
    Color BONUS_COLOR = Color.web("#ff1f1f");
    int SCORECARD_X = 300;
    int SCORECARD_Y = 100;

    final double BOUND_BUFFER = 3;
    double BUFFER = 0;
    int TOWER_PASS_VALUE = 1;
    boolean isBonusCollected = false;
    int PLAYER_SPEED = 200; // per 1 second

    double TOWER_X; // Initial character X position
    double BONUS_X;
    

    public void newGame() {
        SCORE = 0;
        TOWER_DIST = newDist();
        TOWER_WIDTH = newWidth();

        TOWER_X = Tower1.getX() + Tower1.getWidth() + TOWER_DIST;

        createTowerBonusStick();

        scoreCard = new Text();
        scoreCard.setText("--");
        scoreCard.setLayoutX(SCORECARD_X);
        scoreCard.setLayoutY(SCORECARD_Y);
        scoreCard.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 48));
        scoreCard.setFill(Color.DARKKHAKI);
        pane.getChildren().add(scoreCard);

        // Add components to the root pane
    }

    @FXML
    public void back() {

    }

    @FXML
    private void clickkkk() {
        if (!isReady) {
            playerInvert();
            return;
        }
        stick.setOpacity(1);
        stick.setDisable(false);
        // System.out.println("KEY PRESSED !");
        sizeIncreaseTimeline.play();
        isMousePressed = true;
    }

    private void playerInvert() {
        Rotate r = new Rotate();
        r.setPivotY(-LEVEL);
        r.setPivotX(character.getX());

        character.getTransforms().add(r);
        System.out.println("INVERTINGGG");
        inverted = !inverted;
        RotateTransition playerInverting = new RotateTransition();
        playerInverting.setNode(character);
        playerInverting.setAxis(Rotate.X_AXIS);
        playerInverting.setByAngle(180);
        playerInverting.play();

    }

    public void releaaassee() {
        // stick.setDisable(false);
        if (!isReady)
            return;
        isReady = false;

        // System.out.println("KEY RELEASED !");
        sizeIncreaseTimeline.stop();
        isMousePressed = false;
        stickFall();

        movePlayer();
    }

    private void stickFall() {
        System.out.println(stick.getEndY());
        double len = stick.getEndY() - stick.getStartY();

        stick.setEndX(stick.getStartX() - len);
        stick.setEndY(stick.getStartY());

        System.out.println("Check bounds result when stick falling: " + checkBounds());
        System.out.println(stick.getEndX() + " " + (BonusRect.getX() + 4));
        System.out.println("Stick fall at " + stick.getEndX() + " but bonus is at " + (BonusRect.getX() + 4));

        if (abs(stick.getEndX() - (BonusRect.getX() + 4)) <= BONUS_BOUND_BUFFER) {
            gainBonusPoint();
        }
    }

    private boolean checkBounds() {
        System.out.println("stick.getStartX() stick.getEndX() Tower2.getX()");
        System.out.println(stick.getStartX() + " " + stick.getEndX() + " " + Tower2.getX());
        // int margin =
        return !(stick.getEndX() < Tower2.getX() - BOUND_BUFFER)
                && !(stick.getEndX() > Tower2.getX() + TOWER_WIDTH + BOUND_BUFFER);
    }

    private void gainBonusPoint() {
        isBonusCollected = true;
        scoreGain(BONUS_VALUE);
        newFadeTransition(BonusRect, 786);
        System.out.println("BONUS GAINED");
    }

    private void movePlayer() {
        // System.out.println("MOVINGGGGG");
        TranslateTransition playerMove = new TranslateTransition();
        playerMove.setNode(character);
        System.out.println(stick.getStartX() - stick.getEndX() + 20);
        if (checkBounds()) {
            playerMove.setByX(Tower2.getX() - Tower1.getWidth() - Tower1.getX() + 15);
            playerMove.setOnFinished(event -> {
                // System.out.println("FINISHED");
                playerMovedSuccess();
            });
        } else {
            playerMove.setByX(abs(stick.getStartX() - stick.getEndX()) + 15);
            playerMove.setOnFinished(event -> {
                System.out.println("OHHHH NOOOO");
                playerMOVEDfailure();
//                playerFallFinished();
//                try{Thread.sleep(500);}
//                catch (Exception e) {System.out.println(e.getMessage());}
//                playerFallFinished();
            });
        }
        int len = ((int) abs(stick.getEndX() - stick.getStartX()));
        int time = len * 1000 / PLAYER_SPEED;

        playerMove.setDuration(Duration.millis(time));

        System.out.println("X Coordinate of the character before moving: " + character.getX());
        playerMove.play();


    }

    private void playerMOVEDfailure() {
        System.out.println("FALLLL");
//        respawnWindow.setVisible(true);

        // transition to fall character down
        TranslateTransition playerFall = new TranslateTransition();
        playerFall.setNode(character);
        playerFall.setByY(LEVEL);
        playerFall.setDuration(Duration.millis(500));
        playerFall.setOnFinished(event -> {
            System.out.println("FALL FINISHED");
            playerFallFinished();
        });
        playerFall.play();
    }

    private void playerFallFinished() {
        boolean response = false;
        respawnWindow.setVisible(true);
        Thread t1 = new Thread(() -> {
                System.out.println("IN THREAD");
                isRespawning = false;
                System.out.println("ASKING FOR RESPAWN");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("CLosing respawn window");
                    System.out.println(isRespawning);
                    closeRespawnPane();
                }
        });

        if (RESPAWN_REMAINING > 0 && NUM_CHERRIES >= RESPWAN_PRICE) {
            t1.start();
            try {
                t1.join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            if (isRespawning) {
                respawn();
                return;
            }
        }
        updateScoreAndValues();
        endGame();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GamePlayController.class.getResource("Home.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 676, 457);
            Stage stage = (Stage) character.getScene().getWindow();
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.out.println("ERROR GETTING BACK TO HOME PAGE");
        }

    }

    private void endGame() {

    }

    private void updateScoreAndValues() {

    }

    private void respawn() {
        inverted = false;
        RESPAWN_REMAINING--;
        reduceBalance(RESPWAN_PRICE);

        character.setOpacity(0);
        character.setX(Tower2.getX() - character.getFitWidth() + 2);
        character.setY(LEVEL - character.getFitHeight());

        FadeTransition respawnPlayer = new FadeTransition(Duration.millis(786), character);
        respawnPlayer.setFromValue(0);
        respawnPlayer.setToValue(1);

        respawnPlayer.play();
        try {
            Thread.sleep(786);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        nextTower();
    }

    public void respawnNOO(ActionEvent actionEvent) {
        System.out.println("No respawn");
        isRespawning = false;
        closeRespawnPane();
    }

    public void respawnYESS(ActionEvent actionEvent) {
        System.out.println("Respawning");
        isRespawning = true;
        closeRespawnPane();
    }

    private void closeRespawnPane() {
        respawnWindow.setVisible(false);
    }

    private void reduceBalance(int respwanPrice) {
        if (NUM_CHERRIES >= respwanPrice) {
            NUM_CHERRIES -= respwanPrice;
        }
    }

    private void playerMovedSuccess() {
        if (inverted)
            playerMOVEDfailure();
        scoreGain(TOWER_PASS_VALUE);
        System.out.println("X Coordinate of the character after moving: " + character.getX());
        character.setX(character.getX() + character.getTranslateX());
        System.out.println("X Coordinate of the character after moving: " + character.getX());

        character.setTranslateX(0);
        System.out.println("X Coordinate of tower 2 in Scene: " + Tower2.getX());
        nextTower();
    }

    private void nextTower() {
        TranslateTransition moveToLeft = new TranslateTransition();
        moveToLeft.setNode(pane);
        moveToLeft.setByX(-Tower2.getX() + BUFFER);
        moveToLeft.setDuration(Duration.millis(786));

        TranslateTransition KeepScoreCardIntact = new TranslateTransition();
        KeepScoreCardIntact.setNode(scoreCard);
        KeepScoreCardIntact.setByX(Tower2.getX() - BUFFER);
        KeepScoreCardIntact.setDuration(Duration.millis(786));

        TranslateTransition KeepScoreCardIntact2 = new TranslateTransition();
        KeepScoreCardIntact2.setNode(character);
        KeepScoreCardIntact2.setByX(TOWER_WIDTH - 15);
        KeepScoreCardIntact2.setDuration(Duration.millis(786));

        if (!isBonusCollected)
            newFadeTransition(BonusRect, 786);

        moveToLeft.setOnFinished(event -> {
            NewTower();
        });

        newFadeTransition(stick, 786);
        moveToLeft.play();
        KeepScoreCardIntact.play();
        KeepScoreCardIntact2.play();
    }

    private void NewTower() {
        BUFFER = Tower2.getX();

        Tower1 = Tower2;
        TOWER_DIST = newDist();
        TOWER_WIDTH = newWidth();

        TOWER_X = Tower1.getX() + Tower1.getWidth() + TOWER_DIST;
        Tower1.setFill(Color.DARKBLUE);

        createTowerBonusStick();
        isReady = true;
        isBonusCollected = false;
    }

    private void createTowerBonusStick() {
        createTower2();
        pane.getChildren().add(Tower2);
        createNewBonusRect();
        pane.getChildren().add(BonusRect);
        createNewStick();
        pane.getChildren().add(stick);
        stick.toBack();
        Tower2.toBack();
    }

    private void createNewStick() {
        stick = new Line(BUFFER + Tower1.getWidth() - STICK_STROKE_WIDTH / 2, LEVEL + STICK_STROKE_WIDTH / 2,
                BUFFER + Tower1.getWidth() - STICK_STROKE_WIDTH / 2, LEVEL + STICK_STROKE_WIDTH / 2);
        stick.setStroke(STICK_COLOR);
        stick.setOpacity(0);
        stick.setStrokeWidth(STICK_STROKE_WIDTH);
    }

    private void createNewBonusRect() {
        BONUS_X = TOWER_X + (TOWER_WIDTH / 2) - (BONUS_RECT_WIDTH / 2);
        BonusRect = new Rectangle(BONUS_X, LEVEL, BONUS_RECT_WIDTH,
                BONUS_RECT_HEIGHT);
        BonusRect.setFill(BONUS_COLOR);
        BonusRect.setStroke(Color.BLACK);
        BonusRect.setArcWidth(5.0);
        BonusRect.setArcHeight(5.0);
    }

    private void newFadeTransition(Node obj, double ms) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setNode(obj);
        fadeTransition.setDuration(Duration.millis(ms));
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();
    }

    @FXML
    public void initialize() {
        // Initialize the timeline for increasing the stick size
        sizeIncreaseTimeline = new Timeline(new KeyFrame(Duration.millis(10), event -> {
            // Check if the mouse is pressed and increase the size
            if (isMousePressed) {
                growStick();
            }
        }));
        sizeIncreaseTimeline.setCycleCount(Timeline.INDEFINITE);
        respawnText.setText("For " + RESPWAN_PRICE + " CHERRIES !" + "\n" + "Remaining chances in this turn : "
                + RESPAWN_REMAINING + "\n" + "Cherries : " + NUM_CHERRIES);
        RESPAWN_REMAINING = 2;
        newGame();
    }

    public void growStick() {
        stick.setEndY(max(stick.getEndY() - STICK_GROW_SPEED, -STICK_LIMIT));
    }

    private void scoreGain(int val) {
        SCORE += val;
        scoreCard.setText(Integer.toString(SCORE));
    }

    private double newWidth() {
        return random(50, min(TOWER_DIST - 30, 180));
    }

    private double newDist() {
        return random(80, 380);
    }

    private double random(double x, double y) {
        return x + (y - x) * Math.random();
    }

    private void createTower2() {
        Tower2 = new Rectangle(TOWER_X, LEVEL, TOWER_WIDTH, LEVEL);
        Tower2.setFill(Color.DODGERBLUE);
        Tower2.setStroke(Color.BLACK);
        Tower2.setArcWidth(5.0);
        Tower2.setArcHeight(5.0);
    }

    private void cherrySpawn(){
        
    }
}
