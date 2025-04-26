package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class ExplodingEnemy extends DynamicBody implements StepListener {
    private static final float DETECTION_RANGE = 10f;
    private static final float EXPLOSION_RANGE = 5f;
    private static final float SPEED = 2f;
    private static final int COOLDOWN_DURATION = 180; // 3 seconds
    private static final int ATTACK_DURATION = 90; // 0.5 seconds

    private final Player player;
    private boolean isAttacking = false;
    private boolean facingRight = true;
    private int cooldownTimer = 0;
    private int attackTimer = 0;
    private boolean hasDamaged = false;
    private boolean inCooldown = false;

    public ExplodingEnemy(World world, Player player, Vec2 position) {
        super(world, new BoxShape(1, 2f));
        this.player = player;
        setPosition(position);
        world.addStepListener(this);
        addImage(new BodyImage("data/ezgif.com-animated-gif-maker exploding.gif", 16f));
    }

    @Override
    public void preStep(StepEvent e) {
        // Skip if player is dead (health <= 0)
        if (player.getHealth() <= 0) {
            setLinearVelocity(new Vec2(0, 0));
            if (isAttacking) {
                endAttack(); // Immediately end any ongoing attack
            }
            return;
        }

        // Skip if in cooldown (stays still)
        if (inCooldown) {
            cooldownTimer--;
            if (cooldownTimer <= 0) {
                inCooldown = false;
            }
            return;
        }

        // Handle attack sequence
        if (isAttacking) {
            attackTimer--;

            // Apply damage once at start of attack
            if (!hasDamaged) {
                if (getPosition().sub(player.getPosition()).length() <= EXPLOSION_RANGE) {
                    player.takeDamage(25);
                }
                hasDamaged = true;
            }

            // End attack when duration complete
            if (attackTimer <= 0) {
                endAttack();
            }
            return;
        }

        // Only move/attack if player is detected
        Vec2 playerPos = player.getPosition();
        Vec2 enemyPos = getPosition();
        float distanceX = playerPos.x - enemyPos.x;

        // Face player direction if detected
        if (Math.abs(distanceX) <= DETECTION_RANGE) {
            boolean shouldFaceRight = distanceX > 0;
            if (shouldFaceRight != facingRight) {
                facingRight = shouldFaceRight;
                updateImage();
            }

            // Attack if in range
            if (Math.abs(distanceX) <= EXPLOSION_RANGE) {
                startAttack();
            }
            // Chase if detected but not in attack range
            else {
                setLinearVelocity(new Vec2(facingRight ? SPEED : -SPEED, 0));
            }
        }
        // Stay still if player not detected
        else {
            setLinearVelocity(new Vec2(0, 0));
        }
    }

    private void startAttack() {
        isAttacking = true;
        hasDamaged = false;
        attackTimer = ATTACK_DURATION;
        setLinearVelocity(new Vec2(0, 0));
        updateImage();
    }

    private void endAttack() {
        isAttacking = false;
        inCooldown = true;
        cooldownTimer = COOLDOWN_DURATION;
        setLinearVelocity(new Vec2(0, 0));
        updateImage();
    }

    private void updateImage() {
        removeAllImages();
        String image;
        if (isAttacking) {
            // Use different attack images based on facing direction
            image = facingRight ?
                    "data/ezgif.com-animated-gif-maker exploding2.gif" :
                    "data/ezgif.com-rotate exploding2.gif";
        } else {
            // Use different idle images based on facing direction
            image = facingRight ?
                    "data/ezgif.com-animated-gif-maker exploding.gif" :
                    "data/ezgif.com-rotate exploding1.gif";
        }
        addImage(new BodyImage(image, 16f));
    }

    @Override
    public void postStep(StepEvent e) {}
}