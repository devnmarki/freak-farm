package com.devnmarki.game.sandbox.components.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.devnmarki.game.engine.Engine;
import com.devnmarki.game.engine.data.EntityReader;
import com.devnmarki.game.engine.ecs.Component;
import com.devnmarki.game.engine.ecs.Entity;
import com.devnmarki.game.engine.graphics.SpriteRenderer;
import com.devnmarki.game.engine.graphics.Spritesheet;
import com.devnmarki.game.engine.math.Vector2;
import com.devnmarki.game.engine.physics.Rigidbody;
import com.devnmarki.game.sandbox.Assets;

public class Farmer extends Component {

    private Rigidbody rb;
    private SpriteRenderer sr;

    public float speed;
    public float jumpForce;
    public float shootTime;

    private float input = 0f;
    private int facingDir = 0;

    private Vector2 shootPoint = new Vector2();
    private float shootTimer = 0f;

    private Spritesheet animSheet;

    @Override
    public void onStart() {
        super.onStart();

        rb = entity.getComponent(Rigidbody.class);
        sr = entity.getComponent(SpriteRenderer.class);

        shootPoint = new Vector2(entity.getTransform().position.x + (26f * Engine.scale), entity.getTransform().position.y + (9f * Engine.scale));

        shootTimer = shootTime;

        animSheet = new Spritesheet(new TextureRegion(Assets.Characters.FARMER_TEXTURE), 8, 2, new Vector2(32), false);
        sr.sprite = animSheet.getSprite(0);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        handleInputs();
        move();

        if (facingDir == 0) {
            shootPoint = new Vector2(entity.getTransform().position.x + (22f * Engine.scale), entity.getTransform().position.y);
        } else {
            shootPoint = new Vector2(entity.getTransform().position.x - (16f * Engine.scale), entity.getTransform().position.y);
        }
    }

    private void handleInputs() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            input = -1f;
            facingDir = 1;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            input = 1f;
            facingDir = 0;
        } else {
            input = 0f;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.Z)) {
            jump();
        }

        shootTimer += Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.X) && shootTimer >= shootTime) {
            shoot();
        }
    }

    private void move() {
        rb.setVelocity(new Vector2(input * speed, rb.getVelocity().y));

        if (sr.sprite != null)
            sr.sprite.setFlip(facingDir != 0);
    }

    private void jump() {
        rb.setVelocity(new Vector2(rb.getVelocity().x, jumpForce));
    }

    private void shoot() {
        Entity bulletEntity = EntityReader.loadEntity("assets/data/entities/bullet.json", shootPoint);
        SpriteRenderer bulletSr = bulletEntity.getComponent(SpriteRenderer.class);
        bulletSr.sprite.setFlip(facingDir != 0);

        instantiate(bulletEntity);

        shootTimer = 0f;
    }

    public int getFacingDir() {
        return facingDir;
    }

}
