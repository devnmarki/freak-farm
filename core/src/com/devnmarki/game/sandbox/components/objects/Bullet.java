package com.devnmarki.game.sandbox.components.objects;

import com.devnmarki.game.engine.ecs.Component;
import com.devnmarki.game.engine.math.Vector2;
import com.devnmarki.game.engine.physics.Rigidbody;

public class Bullet extends Component {

    private Rigidbody rb;

    @Override
    public void onStart() {
        super.onStart();

        rb = getComponent(Rigidbody.class);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
    }

}
