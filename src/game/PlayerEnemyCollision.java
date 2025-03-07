package game;

import city.cs.engine.*;

public class PlayerEnemyCollision implements CollisionListener {
    private Player player;

    public PlayerEnemyCollision(Player player) {
        this.player = player;
    }

    @Override
    public void collide(CollisionEvent e) {
        // Check if the collision is between the player and an enemy
        if (e.getReportingBody() instanceof Player && e.getOtherBody() instanceof Enemy) {
            player.takeDamage(10); // Decrement player health
            System.out.println("Player hit by enemy! Health: " + player.getHealth());
        }
    }
}