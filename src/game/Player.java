package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import java.util.ArrayList;
import java.util.List;

/**
 * A class representing the player character in the game.
 * The player is a Walker object that can move, take damage, and regain health.
 * The player's health is tracked and displayed using a HealthBar.
 */
public class Player extends Walker {
    private int health; // The current health of the player
    private HealthBar healthBar; // Reference to the HealthBar to display the player's health
    private int coinCount;
    private boolean facingRight = true;
    private boolean canAttack = false;
    private boolean isAttacking = false;
    private int attackTimer = 0;
    private static final float ATTACK_RANGE = 6f;
    private static final int ATTACK_DURATION = 120;

    public Player(World world, Shape shape, HealthBar healthBar) {
        super(world, shape); // Call the superclass constructor to initialize the Walker
        this.health = 100; // Set initial health to 100
        this.healthBar = healthBar; // Initialize the HealthBar reference
        this.coinCount = 0;
        healthBar.setHealth(health); // Update the HealthBar with the initial health value
        updateImage();

        world.addStepListener(new StepListener() {
            @Override
            public void preStep(StepEvent e) {
                updateAttack(); // This will now be called every frame
            }

            @Override
            public void postStep(StepEvent e) {}
        });
    }

    public void attack() {
        if (canAttack() && !isAttacking) {
            isAttacking = true;
            attackTimer = ATTACK_DURATION;
            //attackedEnemies.clear(); // Reset list for new attack
            updateImage();
        }
    }

    public void updateAttack() {
        if (isAttacking) {
            attackTimer--;

            // Damage enemies at attack peak (mid-animation)
            if (attackTimer == ATTACK_DURATION/2) {
                damageEnemiesInRange();
            }

            if (attackTimer <= 0) {
                endAttack();
            }
        }
    }

    private void damageEnemiesInRange() {
        Vec2 attackPos = getPosition();

        for (DynamicBody body : getWorld().getDynamicBodies()) {
            if (body instanceof DamageableEnemy) {
                DamageableEnemy enemy = (DamageableEnemy ) body;
                if (attackPos.sub(body.getPosition()).length() < ATTACK_RANGE) {
                    enemy.takeDamage(0);
                    System.out.println("Enemy destroyed!");
                }
            }
        }
    }

    private void endAttack() {
        isAttacking = false;
        updateImage();
    }

    public boolean canAttack() {
        return coinCount >= 5;
    }

    public void takeDamage(int damage) {
        health -= damage; // Reduce health by the damage amount
        if (health <= 0) {
            destroy(); // Remove the player from the world if health is 0 or below
        }
        healthBar.setHealth(health); // Update the HealthBar with the new health value
    }

    public void regainHealth(int amount) {
        health += amount; // Increase health by the specified amount
        if (health > 100) {
            health = 100; // Cap health at 100
        }
        healthBar.setHealth(health); // Update the HealthBar with the new health value
    }

    public int getHealth() {
        return health;
    }

    public void incrementCoinCount() {
        coinCount++;
        System.out.println("Coins collected: " + coinCount);
        healthBar.setCoins(coinCount, 5);
    }

    public int getCoinCount() {
        return coinCount;
    }

    public boolean isFacingRight() {
        return facingRight;
    }

    private void updateImage() {
        removeAllImages();
        String imageName;
        float size;

        if (isAttacking) {
            imageName = facingRight ?
                    "data/ezgif.com-crop4.gif" :
                    "data/ezgif.com-rotate6.gif";
            size = 12f;
        } else {
            imageName = facingRight ?
                    "data/output-onlinegiftools-ezgif.com-crop (1).gif" :
                    "data/ezgif.com-rotate.gif";
            size = 5f;
        }
        addImage(new BodyImage(imageName, size));
    }

    public void setFacingRight(boolean facingRight) {
        if (this.facingRight != facingRight) {
            this.facingRight = facingRight;
            updateImage();
        }
    }
}
