package game;

import city.cs.engine.*;

/**
 * A class representing a collision listener for interactions between the player and collectibles.
 * When the player collides with a collectible, the collectible is destroyed, and the player's health is increased.
 */
public class PlayerCollectibleCollision implements CollisionListener {
    private Player player; // Reference to the player object

    public PlayerCollectibleCollision(Player player) {
        this.player = player; // Initialize the player reference
    }

    @Override
    public void collide(CollisionEvent e) {
        // Check if the collision is between the player and a collectible
        if (e.getReportingBody() instanceof Player && e.getOtherBody() instanceof Collectible) {
            Collectible collectible = (Collectible) e.getOtherBody(); // Get the collectible object
            collectible.destroy(); // Destroy the collectible

            // Regain 20 health for the player (ensure it doesn't exceed 100)
            player.regainHealth(20);

            // Print a message to the console indicating the collectible was collected
            System.out.println("Collectible collected! Health: " + player.getHealth());
        }
    }
}