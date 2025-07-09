package com.devnmarki.game.engine.physics;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.devnmarki.game.engine.Engine;
import com.devnmarki.game.engine.ecs.Component;
import com.devnmarki.game.engine.math.Vector2;

public class Rigidbody extends Component {

    public Vector2 velocity = new Vector2();
    public boolean useGravity = true;
    public boolean isKinematic = false;
    public float gravityScale = 1f;

    private Collider collider;

    @Override
    public void onStart() {
        super.onStart();

        collider = getComponent(Collider.class);

        if (collider == null || collider.body == null) return;

        if (isKinematic) {
            collider.body.setType(BodyDef.BodyType.KinematicBody);
        } else {
            collider.body.setType(useGravity ? BodyDef.BodyType.DynamicBody : BodyDef.BodyType.StaticBody);
        }

        collider.body.setGravityScale(useGravity ? gravityScale : 0f);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (collider.body == null) return;

        if (isKinematic) {
            collider.body.setLinearVelocity(velocity.x / Engine.PPM, velocity.y / Engine.PPM);
        } else {
            velocity.x = collider.body.getLinearVelocity().x * Engine.PPM;
            velocity.y = collider.body.getLinearVelocity().y * Engine.PPM;
        }
    }

    public void applyForce(Vector2 force) {
        if (collider.body != null) {
            collider.body.applyForceToCenter(force.x, force.y, true);
        }
    }

    public void setVelocity(Vector2 vel) {
        this.velocity = vel;
        if (collider.getBody() != null) {
            collider.getBody().setLinearVelocity(vel.x, vel.y);
        }
    }

    public Vector2 getVelocity() {
        return new Vector2(collider.getBody().getLinearVelocity().x, collider.getBody().getLinearVelocity().y);
    }
}
