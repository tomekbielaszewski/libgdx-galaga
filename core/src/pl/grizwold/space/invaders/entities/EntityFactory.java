package pl.grizwold.space.invaders.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import pl.grizwold.space.invaders.SpaceInvadersGame;
import pl.grizwold.space.invaders.components.PhysicsComponent;
import pl.grizwold.space.invaders.util.PixelConverter;

public class EntityFactory {
    private final PhysicsComponent physicsComponent;

    public EntityFactory(PhysicsComponent physicsComponent) {
        this.physicsComponent = physicsComponent;
    }

    public Ship createShip(Vector2 position) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(position);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1, 1);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;

        Body body = physicsComponent.getWorld().createBody(bodyDef);
        body.createFixture(fixtureDef);

        Ship ship = new Ship(body, shape);
        ship.setBulletFactory(this::createBullet);
        body.setUserData(ship);

        return ship;
    }

    public Bullet createBullet(Vector2 position, Vector2 velocity) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.bullet = true;
        bodyDef.linearVelocity.set(velocity);
        bodyDef.position.set(position);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.1f, 0.2f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;

        Body body = physicsComponent.getWorld().createBody(bodyDef);
        body.createFixture(fixtureDef);

        Bullet bullet = new Bullet(body, shape);
        body.setUserData(bullet);

        return bullet;
    }

    public EnemyGroup createEnemyGroup() {
        float gameWidth = PixelConverter.toMeters(SpaceInvadersGame.WIDTH);
        float gameHeight = PixelConverter.toMeters(SpaceInvadersGame.HEIGHT);
        float horizontalSpacer = gameWidth / 13;
        float verticalSpacer = gameHeight / 2 / 6;

        Array<Enemy> enemies = new Array<>(12 * 5);
        EnemyGroup enemyGroup = new EnemyGroup(enemies);

        for (int i = 1; i < 6; i++) {
            for (int j = 1; j < 13; j++) {
                Enemy enemy = createEnemy(new Vector2(horizontalSpacer * j - gameWidth, gameHeight - verticalSpacer * i));
                enemies.add(enemy);
            }

        }

        return enemyGroup;
    }

    public Enemy createEnemy(Vector2 position) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.5f, 0.5f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;

        Body body = physicsComponent.getWorld().createBody(bodyDef);
        body.createFixture(fixtureDef);

        Enemy enemy = new Enemy(body, shape);
        body.setUserData(enemy);

        return enemy;
    }
}
