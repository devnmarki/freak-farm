package com.devnmarki.game.engine.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.devnmarki.game.engine.data.EntityReaderConfig;
import com.google.gson.*;

public class Sprite {

    private TextureRegion texture;
    private boolean flip;

    public Sprite(TextureRegion texture, boolean flip) {
        this.texture = texture;
        this.flip = flip;
    }

    public Sprite(TextureRegion texture) {
        this(texture, false);
    }

    public static void register() {
        EntityReaderConfig.registerDeserializer(Sprite.class, new JsonDeserializer<Sprite>() {
            @Override
            public Sprite deserialize(JsonElement json, java.lang.reflect.Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                JsonObject obj = json.getAsJsonObject();
                String texture = obj.get("texture").getAsString();
                boolean flip = obj.get("flip").getAsBoolean();

                return new Sprite(new TextureRegion(new Texture(texture)), flip);
            }
        });
    }

    public void setFlip(boolean flip) {
        texture.flip(false, false);
        if (this.flip != flip) {
            texture.flip(true, false);
            this.flip = flip;
        }
    }

    public TextureRegion getTexture() {
        return texture;
    }

    public boolean isFlip() {
        return flip;
    }

}
