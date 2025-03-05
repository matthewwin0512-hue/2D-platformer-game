package game;
import city.cs.engine.*;

public class Collectible extends DynamicBody {
    public Collectible(World world, Shape shape) {
        super(world, shape);
        addImage(new BodyImage("data/5790445.png", 2)); // Add collectible image
    }
}
