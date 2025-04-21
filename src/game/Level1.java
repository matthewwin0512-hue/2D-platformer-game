package game.levels;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import javax.swing.*;
import game.*;

public class Level1 extends Level {  // Now properly extends Level

    public Level1(JFrame frame) {
        super(frame, "Level 1", "data/360_F_717598564_BcH9JsPcokbf9ddcgi8wXDmbsMUyr8Y8.jpg", 100);
        populate();
        setupCollisions();
        start();
    }

    @Override
    public void populate() {
        // Ground platform
        new StaticBody(this, new BoxShape(30, 0.5f))
                .setPosition(new Vec2(0f, -11.5f));

        // Floating platform
        new StaticBody(this, new BoxShape(3, 0.5f))
                .setPosition(new Vec2(3f, 1f));

        // Floating platform
        new StaticBody(this, new BoxShape(3, 0.5f))
                .setPosition(new Vec2(-5f, -5f));

        // Create player
        createPlayer(new Vec2(-14f, -9f));

        // Add enemies
        new Enemy(this, new BoxShape(1, 2f), 3, 15, 2)
                .setPosition(new Vec2(3f, -9f));

        new Enemy(this, new BoxShape(1, 2f), -14, -1, 2)
                .setPosition(new Vec2(1f, -9f));

        // Add collectible
        new Collectible(this, new BoxShape(1, 2f))
                .setPosition(new Vec2(3f, 6f));

        //Creates gate to next level
        createGate();

        // Add coin
        new Coin(this, new CircleShape(1f))
                .setPosition(new Vec2(-5f, -3f));

        new Coin(this, new CircleShape(1f))
                .setPosition(new Vec2(1, -9f));
    }
}
