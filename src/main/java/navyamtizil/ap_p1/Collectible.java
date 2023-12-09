package navyamtizil.project;

public abstract class Collectible {
    int value;

    // Spawn a new collectible
    abstract void spawn();

    // Consume and grab the collectible
    abstract void eaten();

    // Remove the collectible from the game if it is not consumed
    abstract void despawn();

    // Determine the quality of the collectible/ Special Attirubte (for further development)
    abstract void quality();
}
