package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Wall extends StaticBody {
    public Wall(Level level, Vec2 position) {
        super(level, new BoxShape(0.5f, 18f));
        setPosition(position);
        //addImage(new BodyImage("data/gate.png", 10f)); // Add gate texture
    }
}