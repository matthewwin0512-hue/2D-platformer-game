package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Level2 extends Level {
    public Level2(Game game) {
        super(game);
        populate();
        setupCollisions();
    }

    @Override
    protected String getBackgroundPath() {
        return "data/360_F_871072284_cQeBgRZd2QDJUSeEku7e9x8QOFErjJyP.jpg";
    }

    @Override
    public void populate() {
        // Ground platform
        new StaticBody(this, new BoxShape(30, 0.5f))
                .setPosition(new Vec2(0f, -11.5f));

        // Floating platform
        new StaticBody(this, new BoxShape(3, 0.5f))
                .setPosition(new Vec2(0f, -5f));

        Shape platformShape = new BoxShape(3f, 0.5f);
        MovingPlatform platform = createMovingPlatform(platformShape, -10f, 10f, 0.1f, new Vec2(-15f, 1f));

        Shape platformShape2 = new BoxShape(3f, 0.5f);
        MovingPlatform platform2 = createMovingPlatform(platformShape, -10f, 10f, 0.1f, new Vec2(15f, 7f));

        new StaticBody(this, new BoxShape(3, 0.5f))
                .setPosition(new Vec2(15f, 11f));

        new StaticBody(this, new BoxShape(3, 0.5f))
                .setPosition(new Vec2(-15f, 11f));

        // Create player
        createPlayer(new Vec2(-17f, -9f));

        // Add enemies
        new Enemy(this, new BoxShape(1, 2f), 3, 15, 2)
                .setPosition(new Vec2(3f, -9f));

        new Enemy(this, new BoxShape(1, 2f), -14, -1, 2)
                .setPosition(new Vec2(1f, -9f));

        new FlyingEnemy(this, new BoxShape(1, 2f), -17, -5, 2, -2)
                .setPosition(new Vec2(-17f, -2f));

        new FlyingEnemy(this, new BoxShape(1, 2f), 5, 17, 2, -2)
                .setPosition(new Vec2(17f, -2f));

        new FlyingEnemy(this, new BoxShape(1, 2f), -9, 9, 2, 15)
                .setPosition(new Vec2(0f, 15f));

        // Add collectible
        new Collectible(this, new BoxShape(1, 2f))
                .setPosition(new Vec2(-17f, 12f));

        //Creates gate to next level
        createWall();

        // Add coin
        new Coin(this, new CircleShape(1f))
                .setPosition(new Vec2(0f, -4f));

        new MovingCoin(this, new CircleShape(1f), -10f, 10f, 0.1f, new Vec2(-15f, 2f));

        new MovingCoin(this, new CircleShape(1f), -10f, 10f, 0.1f, new Vec2(15f, 8f));

        new Coin(this, new CircleShape(1f))
                .setPosition(new Vec2(15f, 12f));

        new Coin(this, new CircleShape(1f))
                .setPosition(new Vec2(-13f, 12f));
    }
}