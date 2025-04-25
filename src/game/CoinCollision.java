package game;

import city.cs.engine.*;

public class CoinCollision implements CollisionListener {
    private Player player;
    private Level level;

    public CoinCollision(Player player, Level level) {
        this.player = player;
        this.level = level;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getReportingBody() instanceof Player && e.getOtherBody() instanceof Coin) {
            e.getOtherBody().destroy();
            player.incrementCoinCount();

            if (player.getCoinCount() >= 5) {
                level.removeWall();
            }
        }
    }
}
