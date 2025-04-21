package game;

import city.cs.engine.*;

public class Coin extends DynamicBody {
    public Coin(World world, Shape shape) {
        super(world, shape);
        addImage(new BodyImage("data/gold-coin-icon-1024x1024-9iafsrqp.png", 2f)); // Add your coin image

    }
}