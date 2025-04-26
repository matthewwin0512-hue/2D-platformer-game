package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class FlyingEnemy extends Enemy {
    private final float flyingHeight; // Store the initial flying height

    public FlyingEnemy(World world, Shape shape, float leftBoundary, float rightBoundary, float speed, float flyingHeight) {
        super(world, shape, leftBoundary, rightBoundary, speed);
        this.flyingHeight = flyingHeight;

        // Set initial flying position
        Vec2 pos = super.getPosition();
        super.setPosition(new Vec2(pos.x, flyingHeight));

        // Make it ignore gravity
        setGravityScale(0);
    }

    @Override
    public void preStep(StepEvent stepEvent) {
        // First do the normal enemy movement logic
        super.preStep(stepEvent);

        // Then enforce constant flying height
        Vec2 currentPos = getPosition();
        if (currentPos.y != flyingHeight) {
            setPosition(new Vec2(currentPos.x, flyingHeight));
        }
    }
}