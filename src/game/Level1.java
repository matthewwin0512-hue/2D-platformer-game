package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import javax.swing.*;

public class Level1 extends Level {
    public Level1(Game game) {
        super(game);
        populate();
        setupCollisions();
    }

    @Override
    protected String getBackgroundPath() {
        return "data/360_F_717598564_BcH9JsPcokbf9ddcgi8wXDmbsMUyr8Y8.jpg";
    }

    @Override
    public void populate() {
        // Ground platform
        new StaticBody(this, new BoxShape(30, 0.5f))
                .setPosition(new Vec2(0f, -11.5f));

        // Floating platform
        new StaticBody(this, new BoxShape(3, 0.5f))
                .setPosition(new Vec2(3f, 1f));

        new StaticBody(this, new BoxShape(3, 0.5f))
                .setPosition(new Vec2(-5f, -5f));

        new StaticBody(this, new BoxShape(3, 0.5f))
                .setPosition(new Vec2(-9f, 7f));

        new StaticBody(this, new BoxShape(3, 0.5f))
                .setPosition(new Vec2(6f, 11f));

        // Create player
        createPlayer(new Vec2(-17f, -9f));

        // Add enemies
        new Enemy(this, new BoxShape(1, 2f), 3, 15, 2)
                .setPosition(new Vec2(3f, -9f));

        new Enemy(this, new BoxShape(1, 2f), -14, -1, 2)
                .setPosition(new Vec2(1f, -9f));

        // Add collectible
        new Collectible(this, new BoxShape(1, 2f))
                .setPosition(new Vec2(5f, 2f));

        //Creates wall to next level
        createWall();

        // Add coin
        new Coin(this, new CircleShape(1f))
                .setPosition(new Vec2(-5f, -3f));

        new Coin(this, new CircleShape(1f))
                .setPosition(new Vec2(1f, 2f));

        new Coin(this, new CircleShape(1f))
                .setPosition(new Vec2(-9f, 8f));

        new Coin(this, new CircleShape(1f))
                .setPosition(new Vec2(4f, 12f));

        new Coin(this, new CircleShape(1f))
                .setPosition(new Vec2(8f, 12f));
    }
}