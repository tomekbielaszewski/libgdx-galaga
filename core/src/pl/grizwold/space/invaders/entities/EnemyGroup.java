package pl.grizwold.space.invaders.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import pl.grizwold.space.invaders.SpaceInvadersGame;
import pl.grizwold.space.invaders.util.PixelConverter;

import java.util.Iterator;

public class EnemyGroup {
    private static final float DRIFT_MARGINS = 0.5f;
    private static final float DRIFT_SPEED = 1.5f;
    private static final float DESCENDING_DRIFT_SPEED = 0.5f;
    private static final Vector2 LEFT = new Vector2(-DRIFT_SPEED, 0);
    private static final Vector2 RIGHT = new Vector2(DRIFT_SPEED, 0);
    private final Array<Enemy> enemies;

    boolean enemyGroupFullyAppeared;
    private boolean startedDescending;

    public EnemyGroup(Array<Enemy> enemies) {
        this.enemies = enemies;
    }

    public void render() {
        for (Enemy enemy : this.enemies) {
            enemy.render();
        }
    }

    public void update(float delta) {
        Vector2 driftDirection = RIGHT;
        boolean driftDirectionChanged = false;

        for (Enemy enemy : this.enemies) {
            if (enemy.getBody().getPosition().x < DRIFT_MARGINS) {
                driftDirection = RIGHT;
                driftDirectionChanged = true;
                break;
            }
            if (enemy.getBody().getPosition().x > PixelConverter.toMeters(SpaceInvadersGame.WIDTH) - DRIFT_MARGINS) {
                driftDirection = LEFT;
                driftDirectionChanged = true;
                enemyGroupFullyAppeared = true;
                break;
            }
        }

        Iterator<Enemy> iterator = this.enemies.iterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            if(enemy.isDestroyed) {
                enemy.dispose();
                iterator.remove();
                continue;
            }
            if (driftDirectionChanged) {
                if(enemyGroupFullyAppeared && !startedDescending) {
                    driftDirection.add(0, -DESCENDING_DRIFT_SPEED);
                    startedDescending = true;
                }
                enemy.getBody().setLinearVelocity(driftDirection);
            }

            enemy.update(delta);
        }
    }

    public void dispose() {
        for (Enemy enemy : this.enemies) {
            enemy.dispose();
        }
    }
}
