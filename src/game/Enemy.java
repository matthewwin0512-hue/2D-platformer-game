package game;
import city.cs.engine.*;


public class Enemy extends DynamicBody {
    public Enemy(World world, Shape shape) {
        super(world, shape);
        addImage(new BodyImage("data/an871k4o1sn51.png", 4)); // Add enemy image
    }
}

