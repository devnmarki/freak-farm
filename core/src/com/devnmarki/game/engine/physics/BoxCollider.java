package com.devnmarki.game.engine.physics;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.devnmarki.game.engine.Engine;
import com.devnmarki.game.engine.math.Vector2;

public class BoxCollider extends Collider {

    public Vector2 size;
    public Vector2 offset;
    public boolean solid;

    public BoxCollider(Vector2 size, Vector2 offset, boolean solid) {
        this.size = size;
        this.offset = offset;
        this.solid = solid;
    }

    public BoxCollider(Vector2 size, Vector2 offset) {
        this(size, offset, true);
    }

    public BoxCollider(Vector2 size) {
        this(size, new Vector2(0f), true);
    }

    @Override
    public void onStart() {
        super.onStart();

        createBody();
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (body == null) return;

        // set entity transfrom position to body position
        entity.getTransform().position = new Vector2(
            body.getPosition().x * Engine.PPM - offset.x,
            body.getPosition().y * Engine.PPM - offset.y
        );
    }

    private void createBody() {
        if (body != null) return;

        System.out.println("Creating body at: " + entity.getTransform().position);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = type;
        bodyDef.position.set((entity.getTransform().position.x + offset.x) / Engine.PPM, (entity.getTransform().position.y + offset.y) / Engine.PPM);
        bodyDef.fixedRotation = true;
        bodyDef.bullet = (type == BodyDef.BodyType.DynamicBody);

        body = Engine.WORLD.createBody(bodyDef);

        float halfWidth = size.x / 2f / Engine.PPM;
        float halfHeight = size.y / 2f / Engine.PPM;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(halfWidth, halfHeight, new com.badlogic.gdx.math.Vector2(halfWidth, halfHeight), 0f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 0f;

        fixture = body.createFixture(fixtureDef);
        fixture.setSensor(solid);
        fixture.setUserData(this);
        body.setUserData(entity);

        shape.dispose();
    }

    @Override
    public void setPosition(Vector2 position) {
        super.setPosition(position);

        if (body != null) {
            body.setTransform(
                    (position.x + offset.x) / Engine.PPM,
                    (position.y + offset.y) / Engine.PPM,
                    body.getAngle()
            );
        }
    }

    @Override
    public void setOffset(Vector2 offset) {
        super.setOffset(offset);

        this.offset = offset;
    }
}
