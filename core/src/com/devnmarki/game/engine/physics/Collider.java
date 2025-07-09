package com.devnmarki.game.engine.physics;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.devnmarki.game.engine.ecs.Component;
import com.devnmarki.game.engine.math.Vector2;

public abstract class Collider extends Component {

    public Body body;
    public BodyDef.BodyType type = BodyDef.BodyType.DynamicBody;

    protected Fixture fixture;

    public void setPosition(Vector2 position) { }

    public void setOffset(Vector2 offset) { }

    public void setType(BodyDef.BodyType type) {
        this.type = type;
    }

}
