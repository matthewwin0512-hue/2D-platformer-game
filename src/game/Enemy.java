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
    private boolean facingRight = true; // Track visual facing direction

    protected float getLeftBoundary() {
        return leftBoundary;
    }

    protected float getRightBoundary() {
        return rightBoundary;
    }

    protected float getSpeed() {
        return speed;
    }

    protected boolean isMovingRight() {
        return movingRight;
    }

    protected void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    protected boolean isFacingRight() {
        return facingRight;
    }

    protected void setFacingRight(boolean facingRight) {
        if (this.facingRight != facingRight) {
            this.facingRight = facingRight;
            updateImage();
        }
    }

    public Enemy(World world, Shape shape, float leftBoundary, float rightBoundary, float speed) {
        super(world, shape);
        this.leftBoundary = leftBoundary;
        this.rightBoundary = rightBoundary;
        this.speed = speed;
        this.movingRight = true; // Start moving to the right

        updateImage();

        // Add this enemy as a StepListener
        world.addStepListener(this);
    }

    private void updateImage() {
        removeAllImages();
        String image = facingRight ?
                "data/d9a0e811a3c5c857ca3b6fc5a43840f2.gif" : // Right-facing image
                "data/ezgif.com-rotate3.gif"; // Left-facing image
        addImage(new BodyImage(image, 6f));
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

        // Update facing direction if changed
        if (movingRight != facingRight) {
            facingRight = movingRight;
            updateImage();
        }

        // Move the enemy
        setLinearVelocity(new Vec2(movingRight ? speed : -speed, 0));
    }

    @Override
    public void postStep(StepEvent stepEvent) {
        // Not used, but required by the StepListener interface
    }
}