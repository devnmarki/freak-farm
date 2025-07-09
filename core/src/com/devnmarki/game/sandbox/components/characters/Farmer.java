package com.devnmarki.game.sandbox.components.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.devnmarki.game.engine.ecs.Component;
import com.devnmarki.game.engine.math.Vector2;
import com.devnmarki.game.engine.physics.Rigidbody;

public class Farmer extends Component {

    public float speed;
    public float jumpForce;

    private Rigidbody rb;

    private float input = 0f;

    @Override
    public void onStart() {
        super.onStart();

        rb = getComponent(Rigidbody.class);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        handleInputs();
        move();
    }

    private void handleInputs() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            input = -1f;
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            input = 1f;
        } else {
            input = 0f;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            jump();
        }
    }

    private void move() {
        rb.setVelocity(new Vector2(input * speed, rb.getVelocity().y));
    }

    private void jump() {
        rb.setVelocity(new Vector2(rb.getVelocity().x, jumpForce));
    }

}
