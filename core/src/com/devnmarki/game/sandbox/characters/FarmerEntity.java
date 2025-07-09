package com.devnmarki.game.sandbox.characters;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.devnmarki.game.engine.Engine;
import com.devnmarki.game.engine.ecs.Entity;
import com.devnmarki.game.engine.graphics.Sprite;
import com.devnmarki.game.engine.graphics.SpriteRenderer;
import com.devnmarki.game.engine.math.Vector2;
import com.devnmarki.game.engine.physics.BoxCollider;
import com.devnmarki.game.sandbox.Assets;

public class FarmerEntity extends Entity {

    @Override
    public void load() {
        transform.position = new Vector2(100f, 100f);
        transform.size = new Vector2(32f, 32f);

        addComponent(new SpriteRenderer(new Sprite(Assets.Characters.FARMER_TEXTURE)));
        addComponent(new BoxCollider(new Vector2(6f * Engine.scale, 16f * Engine.scale), new Vector2(13f * Engine.scale, 8f * Engine.scale)));

        super.load();

        System.out.println("hello farmer");
    }

    @Override
    public void update() {
        super.update();
    }

}
