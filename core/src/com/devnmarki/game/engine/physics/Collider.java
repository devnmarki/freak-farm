package com.devnmarki.game.engine.physics;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.devnmarki.game.engine.Engine;
import com.devnmarki.game.engine.ecs.Component;
import com.devnmarki.game.engine.math.Vector2;

public abstract class Collider extends Component {

    public Body body;
    public String bodyType = "static";

    protected BodyDef.BodyType type;

    protected Fixture fixture;

    @Override
    public void onStart() {
        super.onStart();

        switch(bodyType) {
            case "static":
                type = BodyDef.BodyType.StaticBody;
                break;
            case "dynamic":
                type = BodyDef.BodyType.DynamicBody;
                break;
            default:
                break;
        }
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

}
