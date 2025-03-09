package game;

import city.cs.engine.*;
import javax.swing.*;
import java.awt.*;

public class BackgroundView extends UserView {
    private Image background;
    private HealthBar healthBar; // Reference to the health bar

    public BackgroundView(World world, int width, int height, String backgroundImagePath, HealthBar healthBar) {
        super(world, width, height);
        this.background = new ImageIcon(backgroundImagePath).getImage();
        this.healthBar = healthBar; // Initialize the health bar reference
    }

    @Override
    protected void paintBackground(Graphics2D g) {
        g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    @Override
    protected void paintForeground(Graphics2D g) {
        // Draw the health bar
        healthBar.draw(g);
    }
}