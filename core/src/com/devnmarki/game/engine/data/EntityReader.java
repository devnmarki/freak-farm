package com.devnmarki.game.engine.data;

import com.badlogic.gdx.graphics.Texture;
import com.devnmarki.game.engine.ecs.Component;
import com.devnmarki.game.engine.ecs.Entity;
import com.devnmarki.game.engine.graphics.Sprite;
import com.devnmarki.game.engine.math.Vector2;
import com.google.gson.*;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;

public class EntityReader {

    public static Entity loadEntity(String file, Vector2 position) {
        Gson gson = EntityReaderConfig.getGson(); // use dynamic gson
        JsonObject json;

        try (FileReader reader = new FileReader(file)) {
            json = JsonParser.parseReader(reader).getAsJsonObject();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read entity file: " + file, e);
        }

        Entity entity = new Entity();

        entity.setTag(json.get("tag").getAsString());
        entity.setName(json.get("name").getAsString());

        entity.getTransform().position = position;

        Vector2 size = gson.fromJson(json.get("size"), Vector2.class);
        if (size != null) entity.getTransform().size = size;

        JsonArray components = json.getAsJsonArray("components");

        for (JsonElement compElement : components) {
            JsonObject componentJson = compElement.getAsJsonObject();
            String className = componentJson.get("class").getAsString();

            try {
                Class<?> clazz = Class.forName(className);
                Object rawInstance = clazz.getDeclaredConstructor().newInstance();

                if (!(rawInstance instanceof Component)) {
                    throw new IllegalArgumentException(className + " is not a Component");
                }

                Component component = (Component) rawInstance;

                populateFields(gson, componentJson, component);

                entity.addComponent(component);

            } catch (Exception e) {
                System.err.println("Failed to load component: " + className);
                e.printStackTrace();
            }
        }

        entity.load();
        return entity;
    }

    private static void populateFields(Gson gson, JsonObject json, Object target) {
        for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
            if (entry.getKey().equals("class")) continue;

            try {
                Field field = target.getClass().getField(entry.getKey());
                Object value = gson.fromJson(entry.getValue(), field.getGenericType());
                field.set(target, value);
            } catch (NoSuchFieldException | IllegalAccessException ignored) {
            }
        }
    }

}
