package game;

import city.cs.engine.*;

public class Player extends Walker {
    private int health;

    public Player(World world, Shape shape) {
        super(world, shape);
        this.health = 100;
        addImage(new BodyImage("data/iio3xm4o1sn51.png", 4)); // Add player image
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            destroy(); // Remove player from the world
        }
    }
}
