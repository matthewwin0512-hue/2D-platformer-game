package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class PlayerExplodingEnemyCollision implements CollisionListener {
    private final Player player;

    public PlayerExplodingEnemyCollision(Player player) {
        this.player = player;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getReportingBody() instanceof Player &&
                e.getOtherBody() instanceof ExplodingEnemy) {

            //ExplodingEnemy enemy = (ExplodingEnemy)e.getOtherBody();

            //  Apply contact damage
            player.takeDamage(15);

            //  Push player BACKWARDS relative to their facing direction
            float pushDirection = player.isFacingRight() ? -1 : 1; // Opposite of facing
            player.setLinearVelocity(new Vec2(pushDirection * 8f, 3f));
        }
    }
}