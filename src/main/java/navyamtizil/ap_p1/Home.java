package navyamtizil.ap_p1;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class Home {
    int currency;
    int totalGames;
    int currentCurrency;
    int amountSpent;
    static Stage gameScreen;
//    playerSkin;
    boolean isPremium;

    public Home(){
        currency = 0;
        totalGames = 0;
        currentCurrency = 0;
        amountSpent = 0;
        isPremium = false;
    }

    @FXML
    // Start a new game
    public static void startGame() throws IOException {
        gameScreen = new Stage();
        FXMLLoader gameFXML = new FXMLLoader(Home.class.getResource("Gameplay.fxml"));
        Scene scene = new Scene(gameFXML.load());
        gameScreen.setTitle("STICK HERO !");
        gameScreen.setScene(scene);
        gameScreen.show();

    }

    // Run the new game
    private void runGame(){}

    // End the game, and update the player's stats
    public static void quitGame(){
        gameScreen.close();
    }

    // Display a video of how to play the game
    private void howToPlay(){}

    // Display new skins with cost as collectibles
    private void shop(){}

    // Mock buy currency
    private void buyCurrency(){}

    // Change and set the volume, also mute and unmute the music
    private void setVolume(){}

    // After a skin is bought, decrease the player's currency
    private void spend(){}

    // Change the player's skin, if the user owns the skin
    private void setSkin(){}

    // If the user clicks any unbought item in the shop, display a prompt to buy and a larger image of the skin. After that check balance and purchase the skin
    private void buySkin(){}

    // No Adds and for some additional features (for further development)
    private void buyPremium(){}

    // Exit the game and save the game stats
    private void exitGame() {}
}
