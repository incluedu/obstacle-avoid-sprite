package net.lustenauer.obstacleavoid.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import net.lustenauer.obstacleavoid.config.GameConfig;

/**
 * Created by Patric Hollenstein on 27.01.18.
 *
 * @author Patric Hollenstein
 */
public class PlayerSprite extends Sprite {

    /*
     * ATTRIBUTES
     */
    private Circle bounds;

    /*
     * CONSTRUCTORS
     */
    public PlayerSprite(TextureRegion region) {
        super(region);
        bounds = new Circle(getX(), getY(), GameConfig.PLAYER_BOUNDS_RADIUS);
        setSize(GameConfig.PLAYER_SIZE, GameConfig.PLAYER_SIZE);
    }

    /*
     * PUBLIC METHODES
     */
    public void drawDebug(ShapeRenderer renderer) {
        renderer.circle(bounds.x, bounds.y, bounds.radius, 30);
    }

    public Circle getBounds() {
        return bounds;
    }

    public void updateBounds() {
        if (bounds != null) {
            bounds.setPosition(getX() + getWidth() / 2f, getY() + getHeight() / 2f);
        }
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        updateBounds();
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        updateBounds();
    }
}
