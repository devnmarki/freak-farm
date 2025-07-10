package com.devnmarki.game.engine.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

public class EntityReaderConfig {

    private static final Map<Class<?>, JsonDeserializer<?>> customDeserializers = new HashMap<>();
    private static boolean built = false;
    private static Gson gson;

    public static <T> void registerDeserializer(Class<T> clazz, JsonDeserializer<T> deserializer) {
        if (built) {
            throw new IllegalStateException("Cannot register deserializers after EntityReader has been initialized.");
        }
        customDeserializers.put(clazz, deserializer);
    }

    public static Gson getGson() {
        if (gson == null) {
            GsonBuilder builder = new GsonBuilder();
            for (Map.Entry<Class<?>, JsonDeserializer<?>> entry : customDeserializers.entrySet()) {
                builder.registerTypeAdapter(entry.getKey(), entry.getValue());
            }
            gson = builder.create();
            built = true;
        }
        return gson;
    }

    // For testing or resetting the state in dev tools
    public static void reset() {
        gson = null;
        built = false;
        customDeserializers.clear();
    }
}
