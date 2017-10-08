package pl.grizwold.space.invaders.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import pl.grizwold.space.invaders.SpaceInvadersGame;

public class MainMenuScreen extends ScreenAdapter {
    private final SpaceInvadersGame game;
    private OrthographicCamera camera;

    public MainMenuScreen(SpaceInvadersGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "Space Invaders by Grizwold", SpaceInvadersGame.WIDTH /2 - 50, SpaceInvadersGame.HEIGHT /2 - 40);
        game.font.draw(game.batch, "Tap anywhere to begin!", SpaceInvadersGame.WIDTH /2 - 60, SpaceInvadersGame.HEIGHT /2);
        game.batch.end();

        if (Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }
}
