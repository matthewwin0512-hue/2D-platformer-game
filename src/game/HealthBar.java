package game;

import javax.swing.*;
import java.awt.*;

public class HealthBar extends JPanel {
    private JProgressBar healthBar;

    public HealthBar() {
        // Initialize the health bar
        healthBar = new JProgressBar(0, 100); // Min: 0, Max: 100
        healthBar.setValue(100); // Initial health: 100
        healthBar.setStringPainted(true); // Show health percentage
        healthBar.setForeground(Color.RED); // Set the color of the health bar
        healthBar.setBackground(Color.LIGHT_GRAY); // Set the background color

        // Add the health bar to the panel
        this.setLayout(new BorderLayout());
        this.add(healthBar, BorderLayout.CENTER);
    }

    // Method to update the health bar
    public void setHealth(int health) {
        healthBar.setValue(health);
    }
}
