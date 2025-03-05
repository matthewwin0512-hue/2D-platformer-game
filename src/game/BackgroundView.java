package game;

import city.cs.engine.*;

import javax.swing.*;
import java.awt.*;

public class BackgroundView extends UserView {
    private Image background;

    public BackgroundView(World world, int width, int height, String backgroundImagePath) {
        super(world, width, height);
        this.background = new ImageIcon(backgroundImagePath).getImage();
    }

    @Override
    protected void paintBackground(Graphics2D g) {
        g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}