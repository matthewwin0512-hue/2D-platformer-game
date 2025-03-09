package game;

import java.awt.*;

public class HealthBar {
    private int health;

    public HealthBar() {
        this.health = 100; // Initial health
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void draw(Graphics2D g) {
        // Draw health as text
        g.setColor(Color.WHITE); // Set text color
        g.setFont(new Font("Arial", Font.BOLD, 20)); // Set font
        g.drawString("Health: " + health, 20, 30); // Draw health text

        // Draw a progress bar for health
        int barWidth = 100;
        int barHeight = 10;
        g.setColor(Color.RED); // Health bar color
        g.fillRect(20, 40, health, barHeight); // Draw health bar
        g.setColor(Color.WHITE); // Border color
        g.drawRect(20, 40, barWidth, barHeight); // Draw health bar border
    }
}