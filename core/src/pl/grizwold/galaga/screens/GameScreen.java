package pl.grizwold.galaga.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import pl.grizwold.galaga.GalagaGame;
import pl.grizwold.galaga.components.PhysicsComponent;
import pl.grizwold.galaga.entities.EntityFactory;
import pl.grizwold.galaga.entities.Ship;

import static pl.grizwold.galaga.util.PixelConverter.toMeters;

public class GameScreen extends ScreenAdapter {
    private final GalagaGame game;
    private final OrthographicCamera camera;
    private final PhysicsComponent physics;
    private final EntityFactory entityFactory;

    private final Box2DDebugRenderer renderer;

    private final Ship ship;

    public GameScreen(GalagaGame game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, toMeters(game.WIDTH), toMeters(game.HEIGHT));
        this.physics = new PhysicsComponent();
        this.entityFactory = new EntityFactory(physics);

        this.renderer = new Box2DDebugRenderer(true, true, true, true, true, true);

        ship = entityFactory.createShip(new Vector2(toMeters(800 / 2f - 32 / 2f), 1));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        render();
        game.batch.end();
        logic(delta);
    }

    private void render() {
        renderer.render(physics.getWorld(), camera.combined);
        ship.render();
    }

    private void logic(float delta) {
        ship.update();
        physics.update(delta);
    }

    @Override
    public void dispose() {
        ship.dispose();
        physics.dispose();
    }
}
