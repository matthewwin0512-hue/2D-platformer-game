package game;

import city.cs.engine.*;
import java.util.ArrayList;
import java.util.List;
import org.jbox2d.common.Vec2;

public abstract class Level extends World {
    protected Player player;
    protected HealthBar healthBar;
    protected BackgroundView view;
    protected DebugViewer debugView;
    protected Game game;
    protected float levelExitX = 22f;
    protected Wall wall;
    protected final int coinsToCollect = 5;
    private final StepListener exitListener;
    protected List<MovingPlatform> movingPlatforms = new ArrayList<>();

    public Level(Game game) {
        this.game = game;
        this.healthBar = new HealthBar();

        this.view = new BackgroundView(this, 800, 750, getBackgroundPath(), healthBar);
        view.setFocusable(true);

        //this.debugView = new DebugViewer(this, 800, 750);
        //debugView.setVisible(false);

        exitListener = new StepListener() {
            @Override public void preStep(StepEvent e) {
                checkLevelExit();
            }
            @Override public void postStep(StepEvent e) {}
        };
        this.addStepListener(exitListener);
    }

    public BackgroundView getView() {
        return view;
    }

    protected abstract String getBackgroundPath();

    protected void setupCollisions() {
        if (player != null) {
            player.addCollisionListener(new PlayerCollectibleCollision(player));
            player.addCollisionListener(new PlayerEnemyCollision(player));
            player.addCollisionListener(new CoinCollision(player, this));
            player.addCollisionListener(new PlayerExplodingEnemyCollision(player));
        }
    }

    protected Player createPlayer(Vec2 position) {
        player = new Player(this, new BoxShape(1, 2f), healthBar);
        player.setPosition(position);
        view.addKeyListener(new PlayerControls(player));
        return player;
    }

    public void createWall() {
        wall = new Wall(this, new Vec2(-20f, 6f));
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
        if (debugView != null) {
            debugView.setVisible(false);
            debugView.dispose();
        }
    }

    public void start() {
        super.start();
        view.requestFocusInWindow();
    }

    public abstract void populate();
}