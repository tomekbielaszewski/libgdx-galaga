package pl.grizwold.space.invaders.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Shape;

public class Bullet {
    private static final float SPEED = 10;

    private final Shape shape;
    private Body body;
    public boolean isDestroyed;

    public Bullet(Body body, Shape shape) {
        this.shape = shape;
        this.body = body;
    }

    public void render() {

    }

    public void update() {
        destroyFarBullets();
    }

    private void destroyFarBullets() {
        if (body.getPosition().y > 20) {
            isDestroyed = true;
        }
    }

    public void dispose() {
        body.getWorld().destroyBody(body);
        shape.dispose();
    }
}
