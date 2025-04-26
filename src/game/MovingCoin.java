package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class MovingCoin extends Coin implements StepListener {
    private final float leftBound;
    private final float rightBound;
    private final float speed;
    private boolean movingRight = true;

    public MovingCoin(World world, Shape shape, float leftBound, float rightBound, float speed, Vec2 startPos) {
        super(world, shape);
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.speed = speed;
        setPosition(startPos);
        world.addStepListener(this);
    }

    @Override
    public void preStep(StepEvent e) {
        Vec2 currentPos = getPosition();

        // Change direction at boundaries
        if (currentPos.x <= leftBound) movingRight = true;
        else if (currentPos.x >= rightBound) movingRight = false;

        // Apply movement
        setPosition(new Vec2(currentPos.x + (movingRight ? speed : -speed), currentPos.y))
        ;
    }

    @Override
    public void postStep(StepEvent e) {
    }
}