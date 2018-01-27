package net.lustenauer.obstacleavoid.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Pool;
import net.lustenauer.obstacleavoid.config.GameConfig;

/**
 * Created by Patric Hollenstein on 27.01.18.
 *
 * @author Patric Hollenstein
 */
public class ObstacleSprite extends GameSpriteBase implements Pool.Poolable {
    private float ySpeed = GameConfig.MEDIUM_OBSTACLE_SPEED;
    private boolean hit;


    public ObstacleSprite(TextureRegion region) {
        super(region, GameConfig.OBSTACLE_BOUNDS_RADIUS);
        setSize(GameConfig.OBSTACLE_SIZE, GameConfig.OBSTACLE_SIZE);
    }

    public void update() {
        setPosition(getX(), getY() - ySpeed);
    }

    public boolean isPlayerColliding(PlayerSprite player) {
        Circle playerBounds = player.getBounds();
        // check if player bounds overlap with obstacle bounds
        boolean overlaps = Intersector.overlaps(playerBounds, getBounds());

        if (overlaps) hit = true; // set hit to true

        return overlaps;
    }

    public boolean isNotHit() {
        return !hit;
    }

    public boolean isHit() {
        return hit;
    }

    public void setYSpeed(float ySpeed) {
        this.ySpeed = ySpeed;
    }

    @Override
    public void reset() {
        hit = false;
    }
}
