package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * A class representing a collision listener for interactions between the player and enemies.
 * When the player collides with an enemy, the player takes damage and is pushed away.
 */

public class PlayerEnemyCollision implements CollisionListener {
    private final Player player;

    public PlayerEnemyCollision(Player player) {
        this.player = player;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getReportingBody() instanceof Player && e.getOtherBody() instanceof Enemy) {
            Enemy enemy = (Enemy)e.getOtherBody();

            // Apply damage
            player.takeDamage(10);
            System.out.println("Player hit by enemy! Health: " + player.getHealth());

            // Always push player based on enemy's facing direction
            pushPlayerByEnemyFacing(enemy);
        }
    }

    private void pushPlayerByEnemyFacing(Enemy enemy) {
        // Get direction from enemy to player
        Vec2 direction = player.getPosition().sub(enemy.getPosition());
        direction.normalize();

        // Apply stronger force in enemy's facing direction
        float enemyBasedPush = enemy.isFacingRight() ? 1 : -1;
        float pushForceX = enemyBasedPush * 10f; // Strong horizontal force
        float pushForceY = 4f; // Upward force

        player.setLinearVelocity(new Vec2(pushForceX, pushForceY));
    }
}