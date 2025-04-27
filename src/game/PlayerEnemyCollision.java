package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * A class representing a collision listener for interactions between the player and enemies.
 * When the player collides with an enemy, the player takes damage and is pushed away.
 */

public class PlayerEnemyCollision implements CollisionListener {
    private Player player;

    public PlayerEnemyCollision(Player player) {
        this.player = player;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getReportingBody() instanceof Player && e.getOtherBody() instanceof Enemy) {
            Player player = (Player)e.getReportingBody();
            Enemy enemy = (Enemy)e.getOtherBody();

            // Apply damage
            player.takeDamage(10);
            System.out.println("Player hit by enemy! Health: " + player.getHealth());

            // Determine push direction based on contact initiator
            if (isEnemyMakingContact(e)) {
                // Enemy initiated contact - push in enemy's facing direction
                pushInEnemyDirection(enemy);
            } else {
                // Player initiated contact - push opposite to player's facing
                pushOppositePlayerDirection(player);
            }
        }
    }

    private boolean isEnemyMakingContact(CollisionEvent e) {
        // Check relative velocities to determine who "hit" whom
        Vec2 playerVel = e.getReportingBody().getLinearVelocity();
        Vec2 enemyVel = e.getOtherBody().getLinearVelocity();

        // If enemy was moving faster toward player, consider it the initiator
        return enemyVel.length() > playerVel.length() * 1.5f;
    }

    private void pushInEnemyDirection(Enemy enemy) {
        float pushDirection = enemy.isFacingRight() ? 1 : -1;
        player.setLinearVelocity(new Vec2(pushDirection * 8f, 3f));
    }

    private void pushOppositePlayerDirection(Player player) {
        float pushDirection = player.isFacingRight() ? -1 : 1;
        player.setLinearVelocity(new Vec2(pushDirection * 8f, 3f));
    }
}