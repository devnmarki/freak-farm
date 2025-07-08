package com.devnmarki.game.sandbox.characters;

import com.badlogic.gdx.graphics.Texture;
import com.devnmarki.game.engine.ecs.Entity;
import com.devnmarki.game.engine.graphics.Sprite;
import com.devnmarki.game.engine.graphics.SpriteRenderer;
import com.devnmarki.game.engine.math.Vector2;
import com.devnmarki.game.sandbox.Assets;

public class FarmerEntity extends Entity {

    @Override
    public void load() {
        super.load();

        transform.position = new Vector2(100f, 100f);
        transform.size = new Vector2(32f, 32f);

        addComponent(new SpriteRenderer(new Sprite(Assets.Characters.FARMER_TEXTURE)));
    }

    @Override
    public void update() {
        super.update();
    }

}
