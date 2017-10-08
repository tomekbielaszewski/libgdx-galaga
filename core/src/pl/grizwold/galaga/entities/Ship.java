package pl.grizwold.galaga.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Shape;

public class Ship {
    private final Shape shape;
    private Body body;

    public Ship(Body body, Shape shape) {
        this.shape = shape;
        this.body = body;
    }

    public void render() {

    }

    public void update() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            body.setLinearVelocity(-10, 0);
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            body.setLinearVelocity(10, 0);
        } else if (Gdx.input.isKeyPressed(Input.Keys.X)) {
            if (!body.getWorld().isLocked())
                body.getWorld().destroyBody(body);
        } else {
            body.setLinearVelocity(0, 0);
        }
    }

    public void dispose() {
        shape.dispose();
    }
}
