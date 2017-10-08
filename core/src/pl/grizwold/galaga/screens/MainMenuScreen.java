package pl.grizwold.galaga.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import pl.grizwold.galaga.GalagaGame;

public class MainMenuScreen extends ScreenAdapter {
    private final GalagaGame game;
    private OrthographicCamera camera;

    public MainMenuScreen(GalagaGame game) {
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
        game.font.draw(game.batch, "Galaga by Grizwold", game.WIDTH/2 - 50, game.HEIGHT/2 - 40);
        game.font.draw(game.batch, "Tap anywhere to begin!", game.WIDTH/2 - 60, game.HEIGHT/2);
        game.batch.end();

        if (Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }
}
