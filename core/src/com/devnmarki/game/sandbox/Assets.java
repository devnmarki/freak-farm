package com.devnmarki.game.sandbox;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.devnmarki.game.engine.graphics.Sprite;
import com.devnmarki.game.engine.graphics.Spritesheet;
import com.devnmarki.game.engine.math.Vector2;

public class Assets {

    public static class Textures {

        public static final TextureRegion FARMER_TEXTURE = new TextureRegion(new Texture("sprites/characters/farmer.png"));

        public static final TextureRegion CRATE_TEXTURE = new TextureRegion(new Texture("sprites/objects/crate.png"));

    }

    public static class Spritesheets {

        public static final Spritesheet FARMER_SHEET = new Spritesheet(Textures.FARMER_TEXTURE, 8, 2, new Vector2(32), false);

        public static final Spritesheet CRATE_SHEET = new Spritesheet(Textures.CRATE_TEXTURE, 1, 2, new Vector2(16), false);

    }

}
