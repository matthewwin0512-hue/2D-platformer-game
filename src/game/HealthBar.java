package game;

import java.awt.*;

/**
 * A class representing a health bar for the player.
 * The health bar displays the player's current health as text and a visual progress bar.
 */
public class HealthBar {
    private int health; // The current health value of the player
    private int coinsCollected;
    private int totalCoins;

    public HealthBar() {
        this.health = 100; // Set initial health to 100
        this.coinsCollected = 0;
        this.totalCoins = 5;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setCoins(int collected, int total) {
        this.coinsCollected = collected;
        this.totalCoins = total;
    }

    public void draw(Graphics2D g) {
        // Draw health as text
        g.setColor(Color.WHITE); // Set the text color to white
        g.setFont(new Font("Arial", Font.BOLD, 20)); // Set the font to Arial, bold, size 20
        g.drawString("Health: " + health, 20, 30); // Draw the health text at position (20, 30)

        // Draw a progress bar for health
        int barWidth = 100; // Width of the health bar
        int barHeight = 10; // Height of the health bar
        g.setColor(Color.RED); // Set the health bar color to red
        g.fillRect(20, 40, health, barHeight); // Draw the filled health bar at position (20, 40)
        g.setColor(Color.WHITE); // Set the border color to white
        g.drawRect(20, 40, barWidth, barHeight); // Draw the health bar border at position (20, 40)

        // Draw coin counter
        g.setColor(Color.YELLOW);
        g.drawString("Coins: " + coinsCollected + "/" + totalCoins, 20, 80);

    }
}