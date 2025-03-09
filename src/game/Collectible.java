package game;

import city.cs.engine.*;

/**
 * A class representing a collectible item in the game.
 * Collectibles are DynamicBody objects that the player can interact with (e.g., collect for points or health).
 */
public class Collectible extends DynamicBody {
    public Collectible(World world, Shape shape) {
        super(world, shape); // Call the superclass constructor to initialize the DynamicBody
        addImage(new BodyImage("data/5790445.png", 2)); // Add an image to the collectible
    }
}
