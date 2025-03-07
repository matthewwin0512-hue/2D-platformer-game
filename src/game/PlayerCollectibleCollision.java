package game;

import city.cs.engine.*;

public class PlayerCollectibleCollision implements CollisionListener {
    private Player player;

    public PlayerCollectibleCollision(Player player) {
        this.player = player;
    }

    @Override
    public void collide(CollisionEvent e) {
        // Check if the collision is between the player and a collectible
        if (e.getReportingBody() instanceof Player && e.getOtherBody() instanceof Collectible) {
            Collectible collectible = (Collectible) e.getOtherBody();
            collectible.destroy(); // Destroy the collectible

            // Regain 20 health (ensure it doesn't exceed 100)
            player.regainHealth(20);

            System.out.println("Collectible collected! Health: " + player.getHealth());
        }
    }
}