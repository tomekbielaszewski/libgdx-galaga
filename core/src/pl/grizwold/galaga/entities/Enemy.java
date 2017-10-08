package pl.grizwold.galaga.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Shape;

public class Enemy {
    private final Body body;
    private final Shape shape;
    public boolean isDestroyed;

    public Enemy(Body body, Shape shape) {
        this.body = body;
        this.shape = shape;
    }

    public void render() {

    }

    public void update(float delta) {

    }

    public Body getBody() {
        return body;
    }

    public void dispose() {
        body.getWorld().destroyBody(body);
        shape.dispose();
    }
}
