package project;

public class Home {
    int currency;
    int totalGames;
    int currentCurrency;
    int amountSpent;
    playerSkin;
    boolean isPremium;

    public Home(){
        currency = 0;
        totalGames = 0;
        currentCurrency = 0;
        amountSpent = 0;
        isPremium = false;
    }

    // Start a new game
    private void startGame(){}

    // Run the new game
    private void runGame(){}

    // End the game, and update the player's stats
    private void endGame(){}

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
