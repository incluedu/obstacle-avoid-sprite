package net.lustenauer.obstacleavoid.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import net.lustenauer.obstacleavoid.config.GameConfig;

/**
 * Created by Patric Hollenstein on 27.01.18.
 *
 * @author Patric Hollenstein
 */
public class PlayerSprite extends GameSpriteBase {

    /*
     * CONSTRUCTORS
     */
    public PlayerSprite(TextureRegion region) {
        super(region, GameConfig.PLAYER_BOUNDS_RADIUS);
        setSize(GameConfig.PLAYER_SIZE, GameConfig.PLAYER_SIZE);
    }

}
