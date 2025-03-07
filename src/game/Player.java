package game;

import city.cs.engine.*;

public class Player extends Walker {
    private int health;
    private HealthBar healthBar;

    public Player(World world, Shape shape, HealthBar healthBar) {
        super(world, shape);
        this.health = 100;
        this.healthBar = healthBar;
        addImage(new BodyImage("data/iio3xm4o1sn51.png", 4)); // Add player image
        healthBar.setHealth(health);
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            destroy();// Remove player from the world
        }
        healthBar.setHealth(health);
    }

    public void regainHealth(int amount) {
        health += amount;
        if (health > 100) {
            health = 100; // Ensure health doesn't exceed 100
        }
        healthBar.setHealth(health);
    }

    public int getHealth() {
        return health;
    }
}
