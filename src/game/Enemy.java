package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * A class representing an enemy in the game.
 * The enemy patrols back and forth between two boundaries and interacts with the player.
 **/

public class Enemy extends DynamicBody implements StepListener {
    private float leftBoundary;  // Left boundary for patrolling
    private float rightBoundary; // Right boundary for patrolling
    private float speed;         // Movement speed
    private boolean movingRight; // Direction flag

    public Enemy(World world, Shape shape, float leftBoundary, float rightBoundary, float speed) {
        super(world, shape);
        this.leftBoundary = leftBoundary;
        this.rightBoundary = rightBoundary;
        this.speed = speed;
        this.movingRight = true; // Start moving to the right

        // Add an image to the enemy
        addImage(new BodyImage("data/an871k4o1sn51.png", 4));

        // Add this enemy as a StepListener
        world.addStepListener(this);
    }

    @Override
    public void preStep(StepEvent stepEvent) {
        // Get the current position
        Vec2 position = getPosition();

        // Check if the enemy has reached a boundary
        if (position.x <= leftBoundary) {
            movingRight = true; // Change direction to the right
        } else if (position.x >= rightBoundary) {
            movingRight = false; // Change direction to the left
        }

        // Move the enemy
        if (movingRight) {
            setLinearVelocity(new Vec2(speed, 0)); // Move right
        } else {
            setLinearVelocity(new Vec2(-speed, 0)); // Move left
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {
        // Not used, but required by the StepListener interface
    }
}
