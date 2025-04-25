package game;

import city.cs.engine.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import org.jbox2d.common.Vec2;

public abstract class Level extends World {
    protected Player player;
    protected HealthBar healthBar;
    protected List<Enemy> enemies = new ArrayList<>();
    protected List<Collectible> collectibles = new ArrayList<>();
    protected BackgroundView view;
    protected DebugViewer debugView;
    protected JFrame frame;
    protected Game game;
    protected float levelExitX = 22f;
    protected Wall wall;
    protected final int coinsToCollect = 5;
    protected List<Coin> coins = new ArrayList<>();
    private final StepListener exitListener;
    protected List<MovingPlatform> movingPlatforms = new ArrayList<>();

    public Level(Game game) {
        this.game = game;
        this.healthBar = new HealthBar();
        this.view = new BackgroundView(this, 800, 750, getBackgroundPath(), healthBar);
        this.frame = new JFrame("City Game - Level " + game.getCurrentLevelNumber());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(view);
        view.setFocusable(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        this.debugView = new DebugViewer(this, 800, 750);
        debugView.setVisible(false);

        exitListener = new StepListener() {
            @Override public void preStep(StepEvent e) {
                checkLevelExit();
            }
            @Override public void postStep(StepEvent e) {}
        };
        this.addStepListener(exitListener);
    }

    protected abstract String getBackgroundPath();

    protected void setupCollisions() {
        if (player != null) {
            player.addCollisionListener(new PlayerCollectibleCollision(player));
            player.addCollisionListener(new PlayerEnemyCollision(player));
            player.addCollisionListener(new CoinCollision(player, this));
        }
    }

    protected Player createPlayer(Vec2 position) {
        player = new Player(this, new BoxShape(1, 2f), healthBar);
        player.setPosition(position);
        view.addKeyListener(new PlayerControls(player));
        return player;
    }

    public void createWall() {
        wall = new Wall(this, new Vec2(20f, 6f));
    }

    public void removeWall() {
        if (wall != null) {
            wall.destroy();
            wall = null;
            System.out.println("Wall removed!");
        }
    }

    public MovingPlatform createMovingPlatform(Shape shape, float leftBound, float rightBound, float speed, Vec2 startPos) {
        MovingPlatform platform = new MovingPlatform(this, shape, leftBound, rightBound, speed, startPos);
        movingPlatforms.add(platform);
        return platform;
    }

    protected void checkLevelExit() {
        if (player != null) {
            float playerX = player.getPosition().x;
            if (playerX >= levelExitX && player.getCoinCount() >= coinsToCollect) {
                game.goToNextLevel();
            }
        }
    }

    public void destroy() {
        for (MovingPlatform platform : movingPlatforms) {
            removeStepListener(platform);
        }
        movingPlatforms.clear();
        if (player != null) {
            view.removeKeyListener(new PlayerControls(player));
        }
        if (view != null) {
            frame.remove(view);
        }
        if (debugView != null) {
            debugView.setVisible(false);
            debugView.dispose();
        }
        frame.dispose();
        stop();
    }

    public void start() {
        super.start();
        view.requestFocusInWindow();
    }

    public abstract void populate();
}