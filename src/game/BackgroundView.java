package game;

import city.cs.engine.*;
import javax.swing.*;
import java.awt.*;

/**
 * A custom view class that extends UserView to render the game world with a background image
 * and a health bar overlay.
 */
public class BackgroundView extends UserView {
    private Image background; // The background image for the game
    private HealthBar healthBar; // Reference to the health bar to display player health

    public BackgroundView(World world, int width, int height, String backgroundImagePath, HealthBar healthBar) {
        super(world, width, height); // Call the superclass constructor
        this.background = new ImageIcon(backgroundImagePath).getImage(); // Load the background image
        this.healthBar = healthBar; // Initialize the health bar reference
    }

    @Override
    protected void paintBackground(Graphics2D g) {
        // Draw the background image, scaling it to fit the view dimensions
        g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    @Override
    protected void paintForeground(Graphics2D g) {
        // Draw the health bar using the HealthBar object
        healthBar.draw(g);
    }
}