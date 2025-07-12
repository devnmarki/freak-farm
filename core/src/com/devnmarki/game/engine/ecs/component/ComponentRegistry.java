package com.devnmarki.game.engine.ecs.component;

import java.util.HashMap;
import java.util.Map;

public class ComponentRegistry {

    private static final Map<String, Class<? extends Component>> registry = new HashMap<>();

    public static void register(String name, Class<? extends Component> clazz) {
        registry.put(name, clazz);
    }

    public static Class<? extends Component> get(String name) {
        return registry.get(name);
    }

    public static boolean contains(String name) {
        return registry.containsKey(name);
    }
    
}
