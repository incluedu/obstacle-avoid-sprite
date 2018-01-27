package net.lustenauer.obstacleavoid.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;

/**
 * Created by Patric Hollenstein on 27.01.18.
 *
 * @author Patric Hollenstein
 */
public abstract class GameSpriteBase extends Sprite {

    /*
     * ATTRIBUTES
     */
    protected Circle bounds;

    /*
     * CONSTRUCTORS
     */
    public GameSpriteBase(TextureRegion region, float boundsRadius) {
        super(region);
        bounds = new Circle(getX(), getY(), boundsRadius);
    }


    /*
     * PUBLIC METHODES
     */

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

    @Override
    public void setX(float x) {
        super.setX(x);
        updateBounds();
    }

    @Override
    public void setY(float y) {
        super.setY(y);
        updateBounds();
    }

    public void drawDebug(ShapeRenderer renderer) {
        renderer.circle(bounds.x, bounds.y, bounds.radius, 30);
    }


}
