package net.lustenauer.obstacleavoid.screen.game._old;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import net.lustenauer.obstacleavoid.ObstacleAvoidGame;
import net.lustenauer.obstacleavoid.screen.menu.MenuScreen;

/**
 * Created by Patric Hollenstein on 07.01.18.
 *
 * @author Patric Hollenstein
 */
@Deprecated
public class GameScreenOld implements Screen {

    private final ObstacleAvoidGame game;
    private final AssetManager assetManager;

    private GameControllerOld controller;
    private GameRendererOld renderer;

    public GameScreenOld(ObstacleAvoidGame game) {
        this.game = game;
        this.assetManager = game.getAssetManager();
    }

    @Override
    public void show() {

        controller = new GameControllerOld(game);
        renderer = new GameRendererOld(game.getBatch(), assetManager, controller);
    }

    @Override
    public void render(float delta) {
        controller.update(delta);
        renderer.render(delta);

        if (controller.isGameOver()) {
            game.setScreen(new MenuScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) {
        renderer.resize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }
}
