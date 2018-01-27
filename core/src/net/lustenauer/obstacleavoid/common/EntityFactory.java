package net.lustenauer.obstacleavoid.common;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import net.lustenauer.obstacleavoid.assets.AssetDescriptors;
import net.lustenauer.obstacleavoid.assets.RegionNames;
import net.lustenauer.obstacleavoid.entity.PlayerSprite;

/**
 * Created by Patric Hollenstein on 27.01.18.
 *
 * @author Patric Hollenstein
 */
public class EntityFactory {

    /*
     * ATTRIBUTES
     */
    private final AssetManager assetManager;
    private TextureAtlas gamePlayAtlas;

    /*
     * CONSTRUCTORS
     */
    public EntityFactory(AssetManager assetManager) {
        this.assetManager = assetManager;
        init();
    }

    /*
     * INIT
     */
    private void init() {
        gamePlayAtlas = assetManager.get(AssetDescriptors.GAME_PLAY);
    }

    /*
     * PUBLIC METHODES
     */
    public PlayerSprite createPlayer() {
        TextureRegion playerRegion = gamePlayAtlas.findRegion(RegionNames.PLAYER);
        return new PlayerSprite(playerRegion);
    }

}
