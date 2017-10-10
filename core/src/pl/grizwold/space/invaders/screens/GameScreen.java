package pl.grizwold.space.invaders.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import pl.grizwold.space.invaders.util.PixelConverter;
import pl.grizwold.space.invaders.SpaceInvadersGame;
import pl.grizwold.space.invaders.components.PhysicsComponent;
import pl.grizwold.space.invaders.entities.EnemyGroup;
import pl.grizwold.space.invaders.entities.EntityFactory;
import pl.grizwold.space.invaders.entities.Ship;

public class GameScreen extends ScreenAdapter {
    private final float SCREEN_CENTER_X;
    private final SpaceInvadersGame game;
    private final OrthographicCamera camera;
    private final PhysicsComponent physics;
    private final EntityFactory entityFactory;

    private final Box2DDebugRenderer renderer;

    private final EnemyGroup enemyGroup;
    private final Ship ship;

    public GameScreen(SpaceInvadersGame game) {
        this.SCREEN_CENTER_X = SpaceInvadersGame.WIDTH / 2f;

        this.game = game;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, PixelConverter.toMeters(SpaceInvadersGame.WIDTH), PixelConverter.toMeters(SpaceInvadersGame.HEIGHT));
        this.physics = new PhysicsComponent();
        this.entityFactory = new EntityFactory(physics);

        this.renderer = new Box2DDebugRenderer(true, true, true, true, true, true);

        float shipCenterX = 32 / 2f;
        this.ship = entityFactory.createShip(new Vector2(PixelConverter.toMeters(SCREEN_CENTER_X - shipCenterX), 1));
        this.enemyGroup = entityFactory.createEnemyGroup();
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
        enemyGroup.update(delta);
        physics.update(delta);
    }

    @Override
    public void dispose() {
        ship.dispose();
        enemyGroup.dispose();
        physics.dispose();
    }
}
