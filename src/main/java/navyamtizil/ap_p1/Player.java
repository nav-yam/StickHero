package navyamtizil.project;

public class Player {
//    playerSkin;
    int collected;
    int score;
    int lives;
    int currency;
    
    public Player(){
//        playerSkin;
        collected = 0;
        score = 0;
        lives = 1;
        currency = 0;
    }

    // Basically start the game
    void born() {}

    // If the player dies, this method is called
    void die() {}


    // During the gameplay time
    void running()  {}

    // If the player collects a collectible, this method is called
    void collect(Collectible item) {}

    // If the stick is of appropriate length, the player can cross the pillars
    void move() {}
}
