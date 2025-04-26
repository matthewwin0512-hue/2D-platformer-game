package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Level3 extends Level {
    public Level3(Game game) {
        super(game);
        populate();
        setupCollisions();
    }

    @Override
    protected String getBackgroundPath() {
        return "data/hell-background-with-lava-in-cave-free-vector.jpg";
    }

    @Override
    public void populate() {
        // Ground platform
        new StaticBody(this, new BoxShape(30, 0.5f))
                .setPosition(new Vec2(0f, -11.5f));

        // Floating platform
        new StaticBody(this, new BoxShape(3, 0.5f))
                .setPosition(new Vec2(-10f, -5f));

        new StaticBody(this, new BoxShape(11, 0.5f))
                .setPosition(new Vec2(8.4f, 1f));

        new StaticBody(this, new BoxShape(3, 0.5f))
                .setPosition(new Vec2(15f, 14f));

        Shape platformShape = new BoxShape(3f, 0.5f);
        MovingPlatform platform = createMovingPlatform(platformShape, -15f, 4f, 0.1f, new Vec2(-15f, 8f));

        // Create player
        createPlayer(new Vec2(-17f, -9f));

        // Add enemies
        new ExplodingEnemy(this, player, new Vec2(11f, -9f));

        new ExplodingEnemy(this, player, new Vec2(11f, 2f));

        // Add collectible
        new Collectible(this, new BoxShape(1, 2f))
                .setPosition(new Vec2(17f, 15f));

        //Creates gate to next level
        createWall();

        // Add coin
        new Coin(this, new CircleShape(1f))
                .setPosition(new Vec2(-10f, -4f));


        new Coin(this, new CircleShape(1f))
                .setPosition(new Vec2(17f, -10f));

        new Coin(this, new CircleShape(1f))
                .setPosition(new Vec2(17f, 2f));

        new Coin(this, new CircleShape(1f))
                .setPosition(new Vec2(13f, 15f));

        new MovingCoin(this, new CircleShape(1f), -15f, 4f, 0.1f, new Vec2(-15f, 9f));
    }
}