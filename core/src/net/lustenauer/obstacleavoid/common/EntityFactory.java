package net.lustenauer.obstacleavoid.common;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import net.lustenauer.obstacleavoid.assets.AssetDescriptors;
import net.lustenauer.obstacleavoid.assets.RegionNames;
import net.lustenauer.obstacleavoid.entity.ObstacleSprite;
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
    private Pool<ObstacleSprite> obstaclePool;
    private TextureRegion obstacleRegion;

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
        obstacleRegion = gamePlayAtlas.findRegion(RegionNames.OBSTACLE);

        obstaclePool = new Pool<ObstacleSprite>(30) {
            @Override
            protected ObstacleSprite newObject() {
                return new ObstacleSprite(obstacleRegion);
            }
        };
    }

    /*
     * PUBLIC METHODES
     */
    public PlayerSprite createPlayer() {
        TextureRegion playerRegion = gamePlayAtlas.findRegion(RegionNames.PLAYER);
        return new PlayerSprite(playerRegion);
    }

    public ObstacleSprite obtain() {
        ObstacleSprite obstacleSprite = obstaclePool.obtain();
        obstacleSprite.setRegion(obstacleRegion);
        return obstacleSprite;
    }

    public Sprite createBackground(){
        TextureRegion backgroundRegion = gamePlayAtlas.findRegion(RegionNames.BACKGROUND);
        return new Sprite(backgroundRegion);
    }

    public void free(ObstacleSprite obstacleSprite) {
        obstaclePool.free(obstacleSprite);
    }

    public void freeAll(Array<ObstacleSprite> obstacleSprites) {
        obstaclePool.freeAll(obstacleSprites);
    }

}
