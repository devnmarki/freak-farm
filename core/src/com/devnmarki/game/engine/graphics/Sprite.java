package com.devnmarki.game.engine.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.devnmarki.game.engine.data.EntityReaderConfig;
import com.devnmarki.game.engine.math.Vector2;
import com.google.gson.*;

public class Sprite {

    private Texture texture;
    private TextureRegion textureRegion;
    private boolean flip;

    public Sprite(Texture texture, boolean flip) {
        this.texture = texture;
        this.textureRegion = new TextureRegion(texture);
        this.flip = flip;
    }

    public Sprite(Texture texture) {
        this(texture, false);
    }

    public TextureRegion getTexture() {
        return textureRegion;
    }

    public static void register() {
        EntityReaderConfig.registerDeserializer(Sprite.class, new JsonDeserializer<Sprite>() {
            @Override
            public Sprite deserialize(JsonElement json, java.lang.reflect.Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                JsonObject obj = json.getAsJsonObject();
                String texture = obj.get("texture").getAsString();
                boolean flip = obj.get("flip").getAsBoolean();

                return new Sprite(new Texture(texture), flip);
            }
        });
    }

    public void setFlip(boolean flip) {
        this.flip = flip;
    }

    public boolean isFlip() {
        return flip;
    }

}
