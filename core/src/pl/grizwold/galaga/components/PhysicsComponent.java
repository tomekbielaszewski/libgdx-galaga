package pl.grizwold.galaga.components;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class PhysicsComponent {
    private World world2d;

    public PhysicsComponent() {
        this.world2d = new World(new Vector2(0, 0), true);
        this.world2d.setContactListener(new HitListener());
    }

    public void update(float delta) {
        world2d.step(1 / 60f, 6, 2);
    }

    public World getWorld() {
        return world2d;
    }

    public void dispose() {
        world2d.dispose();
    }
}
