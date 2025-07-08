package com.devnmarki.game.engine.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.devnmarki.game.engine.math.Vector2;

public class Sprite {

    private Texture texture;
    private TextureRegion textureRegion;

    public Sprite(Texture texture) {
        this.texture = texture;
        this.textureRegion = new TextureRegion(texture);
    }

    public TextureRegion getTexture() {
        return textureRegion;
    }

}
