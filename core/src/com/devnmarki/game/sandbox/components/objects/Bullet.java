package com.devnmarki.game.sandbox.components.objects;

import com.badlogic.gdx.Gdx;
import com.devnmarki.game.engine.ecs.Component;
import com.devnmarki.game.engine.ecs.Entity;
import com.devnmarki.game.engine.ecs.EntityRegistry;
import com.devnmarki.game.engine.math.Vector2;
import com.devnmarki.game.engine.physics.Rigidbody;
import com.devnmarki.game.sandbox.components.characters.Farmer;

public class Bullet extends Component {

    private Rigidbody rb;
    private Farmer farmer;

    private Entity farmerEntity;

    public float speed;
    public float lifetime;

    private float velocity = 0f;
    private float timer = 0f;

    @Override
    public void onStart() {
        super.onStart();

        farmerEntity = EntityRegistry.findFirstByTag("player");

        rb = entity.getComponent(Rigidbody.class);
        farmer = farmerEntity.getComponent(Farmer.class);

        velocity = farmer.getFacingDir() == 0 ? 1f : -1f;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        timer += Gdx.graphics.getDeltaTime();
        if (timer >= lifetime) {
            destroy();
        }

        rb.setVelocity(new Vector2(velocity * speed, rb.getVelocity().y));
    }

    @Override
    public void onCollisionEnter(Entity other, Vector2 normal) {
        super.onCollisionEnter(other, normal);

        destroy();
    }
}
