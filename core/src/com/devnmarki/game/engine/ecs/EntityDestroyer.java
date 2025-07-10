package com.devnmarki.game.engine.ecs;

import com.devnmarki.game.engine.scenes.SceneManager;

import java.util.ArrayList;
import java.util.List;

public class EntityDestroyer {

    private static final List<Entity> pendingDestroy = new ArrayList<>();

    public static void queue(Entity e) {
        pendingDestroy.add(e);
    }

    public static void flush() {
        for (Entity e : pendingDestroy) {
            e.clearColliders();
            EntityRegistry.unregister(e);
            SceneManager.currentScene.getEntities().remove(e);
        }
        pendingDestroy.clear();
    }

}
