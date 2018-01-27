package net.lustenauer.obstacleavoid.screen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import net.lustenauer.obstacleavoid.assets.AssetDescriptors;
import net.lustenauer.obstacleavoid.config.GameConfig;
import net.lustenauer.obstacleavoid.entity.ObstacleSprite;
import net.lustenauer.obstacleavoid.entity.PlayerSprite;
import net.lustenauer.obstacolavoid.util.GdxUtils;
import net.lustenauer.obstacolavoid.util.ViewportUtils;
import net.lustenauer.obstacolavoid.util.debug.DebugCameraController;

/**
 * Created by Patric Hollenstein on 07.01.18.
 *
 * @author Patric Hollenstein
 */
public class GameRenderer implements Disposable {

    // == attributes ==
    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer renderer;

    private OrthographicCamera hudCamera;
    private Viewport hudViewport;

    private BitmapFont font;
    private final GlyphLayout layout = new GlyphLayout();
    private DebugCameraController debugCameraController;
    private final GameController controller;
    private final AssetManager assetManager;
    private final SpriteBatch batch;

    // == constructors ==
    public GameRenderer(SpriteBatch batch, AssetManager assetManager, GameController controller) {
        this.batch = batch;
        this.assetManager = assetManager;
        this.controller = controller;
        init();
    }

    // == init ==
    private void init() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);
        renderer = new ShapeRenderer();

        hudCamera = new OrthographicCamera();
        hudViewport = new FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT, hudCamera);
        font = assetManager.get(AssetDescriptors.FONT);

        // create debug camera controller
        debugCameraController = new DebugCameraController();
        debugCameraController.setStartPosition(GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y);
    }

    // == public methodes ==
    public void render(float delta) {
        // not wrapping inside alive because we want be able to control camera even when game is over
        debugCameraController.handleDebugInput(delta);
        debugCameraController.applyTo(camera);

        if (Gdx.input.isTouched() && !controller.isGameOver()) {
            Vector2 screenTouch = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            Vector2 worldTouch = viewport.unproject(new Vector2(screenTouch));

            PlayerSprite player = controller.getPlayer();
            worldTouch.x = MathUtils.clamp(worldTouch.x, 0, GameConfig.WORLD_WIDTH - player.getWidth());
            player.setX(worldTouch.x);
        }

        // clear screen
        GdxUtils.clearScreen();

        // render gameplay
        renderGamePlay();

        // render hud
        renderHud();

        // render debug graphics
        renderDebug();
    }


    public void resize(int width, int height) {
        viewport.update(width, height, true);
        hudViewport.update(width, height, true);
        ViewportUtils.debugPixelPerUnit(viewport);
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }

    // == private methodes ==
    private void renderGamePlay() {
        viewport.apply();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        //draw background
        Sprite background = controller.getBackground();
        background.draw(batch);

        //draw player
        PlayerSprite player = controller.getPlayer();
        player.draw(batch);

        //draw obstacles
        for (ObstacleSprite obstacle : controller.getObstacles()) {
            obstacle.draw(batch);
        }

        batch.end();
    }


    private void renderHud() {
        hudViewport.apply();
        batch.setProjectionMatrix(hudCamera.combined);
        batch.begin();

        String livesText = "LIVES: " + controller.getLives();
        layout.setText(font, livesText);
        font.draw(batch, livesText, 20, GameConfig.HUD_HEIGHT - layout.height);

        String scoreText = "SCORE: " + controller.getDisplayScore();
        layout.setText(font, scoreText);
        font.draw(batch, scoreText, GameConfig.HUD_WIDTH - layout.width - 20, GameConfig.HUD_HEIGHT - layout.height);

        batch.end();
    }

    private void renderDebug() {
        viewport.apply();
        Color oldColor = renderer.getColor().cpy();
        renderer.setProjectionMatrix(camera.combined);
        renderer.begin(ShapeRenderer.ShapeType.Line);

        drawDebug();

        renderer.end();

        renderer.setColor(oldColor);

        ViewportUtils.drawGrid(viewport, renderer);
    }

    private void drawDebug() {
        renderer.setColor(Color.RED);
        controller.getPlayer().drawDebug(renderer);
        for (ObstacleSprite obstacle : controller.getObstacles()) {
            obstacle.drawDebug(renderer);
        }
    }
}
