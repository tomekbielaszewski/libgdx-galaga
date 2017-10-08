package pl.grizwold.space.invaders.components;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import pl.grizwold.space.invaders.entities.Enemy;
import pl.grizwold.space.invaders.util.ContactAdapter;
import pl.grizwold.space.invaders.entities.Bullet;
import pl.grizwold.space.invaders.entities.Ship;

public class HitListener extends ContactAdapter {
    @Override
    public void beginContact(Contact contact) {
        if(enemyTouchesShip(contact)) {
            System.out.println("buuuurn");
        }

        if(bulletHitEnemy(contact)) {
            Enemy hitEnemy = (Enemy) getBodyOf(Enemy.class, contact).getUserData();
            hitEnemy.isDestroyed = true;

            Bullet bullet = (Bullet) getBodyOf(Bullet.class, contact).getUserData();
            bullet.isDestroyed = true;
        }
    }

    private Body getBodyOf(Class clazz, Contact contact) {
        if(isInstanceOf(contact.getFixtureA(), clazz)) return contact.getFixtureA().getBody();
        if(isInstanceOf(contact.getFixtureB(), clazz)) return contact.getFixtureB().getBody();
        return null;
    }

    private boolean enemyTouchesShip(Contact contact) {
        return isInstanceOf(contact.getFixtureA(), Enemy.class) && isInstanceOf(contact.getFixtureB(), Ship.class) ||
                isInstanceOf(contact.getFixtureA(), Ship.class) && isInstanceOf(contact.getFixtureB(), Enemy.class);
    }

    private boolean bulletHitEnemy(Contact contact) {
        return isInstanceOf(contact.getFixtureA(), Enemy.class) && isInstanceOf(contact.getFixtureB(), Bullet.class) ||
                isInstanceOf(contact.getFixtureA(), Bullet.class) && isInstanceOf(contact.getFixtureB(), Enemy.class);
    }

    private boolean isInstanceOf(Fixture fixture, Class clazz) {
        return fixture.getBody().getUserData().getClass().isAssignableFrom(clazz);
    }
}
