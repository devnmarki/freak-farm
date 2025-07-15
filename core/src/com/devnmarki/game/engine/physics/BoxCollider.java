package com.devnmarki.game.engine.physics;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.devnmarki.game.engine.Engine;
import com.devnmarki.game.engine.math.Vector2;

public class BoxCollider extends Collider {

    public Vector2 size = new Vector2(1f, 1f);
    public Vector2 offset = new Vector2(0f, 0f);
    public boolean solid = true;

    private FixtureDef fixtureDef;

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
            body.getPosition().x * Engine.PPM - (offset.x * Engine.scale),
            body.getPosition().y * Engine.PPM - (offset.y * Engine.scale)
        );
    }

    @Override
    public void createBody() {
        super.createBody();

        if (body != null) return;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = type;
        bodyDef.position.set((entity.getTransform().position.x + (offset.x * Engine.scale)) / Engine.PPM, (entity.getTransform().position.y + (offset.y * Engine.scale)) / Engine.PPM);
        bodyDef.fixedRotation = true;
        bodyDef.bullet = (type == BodyDef.BodyType.DynamicBody);

        body = Engine.WORLD.createBody(bodyDef);

        float halfWidth = size.x / 2f / Engine.PPM;
        float halfHeight = size.y / 2f / Engine.PPM;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(halfWidth * Engine.scale, halfHeight * Engine.scale, new com.badlogic.gdx.math.Vector2(halfWidth * Engine.scale, halfHeight * Engine.scale), 0f);

        fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 0f;

        fixture = body.createFixture(fixtureDef);
        fixture.setSensor(!solid);
        fixture.setUserData(entity);
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

    @Override
    public FixtureDef getFixtureDef() {
        return fixtureDef;
    }

}
