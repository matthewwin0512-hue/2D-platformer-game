package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * A class representing the player character in the game.
 * The player is a Walker object that can move, take damage, and regain health.
 * The player's health is tracked and displayed using a HealthBar.
 */
public class Player extends Walker {
    private int health; // The current health of the player
    private HealthBar healthBar; // Reference to the HealthBar to display the player's health
    private int coinCount;

    public Player(World world, Shape shape, HealthBar healthBar) {
        super(world, shape); // Call the superclass constructor to initialize the Walker
        this.health = 100; // Set initial health to 100
        this.healthBar = healthBar; // Initialize the HealthBar reference
        this.coinCount = 0;
        addImage(new BodyImage("data/output-onlinegiftools-ezgif.com-crop (1).gif", 5f)); // Add an image to the player
        healthBar.setHealth(health); // Update the HealthBar with the initial health value
    }

    public void takeDamage(int damage) {
        health -= damage; // Reduce health by the damage amount
        if (health <= 0) {
            destroy(); // Remove the player from the world if health is 0 or below
        }
        healthBar.setHealth(health); // Update the HealthBar with the new health value
    }

    public void regainHealth(int amount) {
        health += amount; // Increase health by the specified amount
        if (health > 100) {
            health = 100; // Cap health at 100
        }
        healthBar.setHealth(health); // Update the HealthBar with the new health value
    }

    public int getHealth() {
        return health;
    }

    public void incrementCoinCount() {
        coinCount++;
        System.out.println("Coins collected: " + coinCount);
        healthBar.setCoins(coinCount, 5);
    }

    public int getCoinCount() {
        return coinCount;
    }
}
