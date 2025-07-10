package com.devnmarki.game.engine.physics;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.devnmarki.game.engine.ecs.Entity;
import com.devnmarki.game.engine.math.Vector2;

public class ColliderEntity extends Entity {

    private BoxCollider collider;

    private Vector2 offset;

    public ColliderEntity() {
        this(Vector2.ZERO);
    }

    public ColliderEntity(Vector2 offset) {
        this.offset = offset;
    }

    @Override
    public void load() {
        collider = new BoxCollider();
        collider.type = BodyDef.BodyType.StaticBody;

        addComponent(collider);

        super.load();
    }

    public static ColliderEntity create(Vector2 position, Vector2 size, Vector2 offset) {
        ColliderEntity colliderEntity = new ColliderEntity(offset);
        colliderEntity.transform.position = position;
        colliderEntity.transform.size = size;

        return Entity.createEntity(colliderEntity);
    }
}
