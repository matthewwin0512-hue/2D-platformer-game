package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * A class representing a collision listener for interactions between the player and enemies.
 * When the player collides with an enemy, the player takes damage and is pushed away.
 */
public class PlayerEnemyCollision implements CollisionListener {
    private Player player; // Reference to the player object

    public PlayerEnemyCollision(Player player) {
        this.player = player; // Initialize the player reference
    }

    @Override
    public void collide(CollisionEvent e) {
        // Check if the collision is between the player and an enemy
        if (e.getReportingBody() instanceof Player && e.getOtherBody() instanceof Enemy) {
            // Apply damage to the player
            player.takeDamage(10); // Decrement player health by 10
            System.out.println("Player hit by enemy! Health: " + player.getHealth());

            // Push the player away from the enemy
            pushPlayerAway(e);
        }
    }

    private void pushPlayerAway(CollisionEvent e) {
        // Get the player's current position
        Vec2 playerPosition = player.getPosition();
        float playerX = playerPosition.x;
        float playerY = playerPosition.y;

        // Move the player to the left by a fixed amount (e.g., -1 unit on the x-axis)
        float pushDistance = -1.0f; // Adjust this value as needed
        float newPlayerX = playerX + pushDistance;
        float newPlayerY = playerY; // Keep the y-position the same

        // Set the player's new position
        player.setPosition(new Vec2(newPlayerX, newPlayerY));

        // Optional: Add a small upward force to make the pushback more dynamic
        float pushStrength = 5.0f; // Adjust this value as needed
        player.setLinearVelocity(new Vec2(pushDistance * pushStrength, 2)); // Apply a small upward force
    }
}