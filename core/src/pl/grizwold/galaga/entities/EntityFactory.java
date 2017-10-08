package pl.grizwold.galaga.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import pl.grizwold.galaga.components.PhysicsComponent;

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

        return ship;
    }

    Bullet createBullet(Vector2 position, Vector2 velocity) {
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

        return new Bullet(body, shape);
    }
}
