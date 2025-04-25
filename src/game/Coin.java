package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Coin extends DynamicBody {
    public Coin(World world, Shape shape) {
        super(world, shape);

        addImage(new BodyImage("data/gold-coin-icon-1024x1024-9iafsrqp.png", 1.5f));
    }
}