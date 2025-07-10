package com.devnmarki.game.engine.physics;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.devnmarki.game.engine.Engine;
import com.devnmarki.game.engine.ecs.Component;
import com.devnmarki.game.engine.math.Vector2;

public abstract class Collider extends Component {

    public Body body;

    protected BodyDef.BodyType type = BodyDef.BodyType.StaticBody;

    protected Fixture fixture;

    @Override
    public void onStart() {
        super.onStart();
    }

    public void createBody() {

    }

    public void destroyBody() {
        if (body != null) {
            Engine.WORLD.destroyBody(body);
        }
    }

    public void setPosition(Vector2 position) { }

    public void setOffset(Vector2 offset) { }

    public void setType(BodyDef.BodyType type) {
        this.type = type;
    }

    public Body getBody() {
        return body;
    }

}
