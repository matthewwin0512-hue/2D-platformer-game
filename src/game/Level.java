package game;

import city.cs.engine.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import org.jbox2d.common.Vec2;

public abstract class Level extends World {
    protected Player player;
    protected final HealthBar healthBar;
    protected final List<Enemy> enemies = new ArrayList<>();
    protected final List<Collectible> collectibles = new ArrayList<>();
    protected BackgroundView view;
    protected DebugViewer debugView;

    public Level(JFrame frame, String levelName, String backgroundImage, int targetScore) {
        this.healthBar = new HealthBar();

        // View setup
        this.view = new BackgroundView(this, 700, 600, backgroundImage, healthBar);
        frame.add(view);
        view.setFocusable(true);
        view.requestFocusInWindow();

        // Debug view
        this.debugView = new DebugViewer(this, 700, 600);
        debugView.setVisible(true);

        frame.pack();
        frame.setVisible(true);
    }

    protected void setupCollisions() {
        if (player != null) {
            player.addCollisionListener(new PlayerCollectibleCollision(player));
            player.addCollisionListener(new PlayerEnemyCollision(player));
        }
    }

    protected Player createPlayer(Vec2 position) {
        player = new Player(this, new BoxShape(1, 2f), healthBar);
        player.setPosition(position);
        view.addKeyListener(new PlayerControls(player));
        return player;
    }

    public abstract void populate();
}