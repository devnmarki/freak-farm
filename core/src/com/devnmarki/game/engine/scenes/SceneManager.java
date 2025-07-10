package com.devnmarki.game.engine.scenes;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import com.devnmarki.game.engine.Engine;
import com.devnmarki.game.engine.ecs.Entity;
import com.devnmarki.game.engine.ecs.EntityRegistry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SceneManager {

    public static Map<String, Scene> scenes = new HashMap<String, Scene>();
    public static Scene currentScene = null;

    private static Scene previousScene = null;

    public static void addScene(String sceneName, Scene scene) {
        scenes.put(sceneName, scene);
    }

    public static void updateCurrentScene() {
        if (currentScene == null) return;

        List<Entity> entitiesCopy = new ArrayList<>(currentScene.getEntities());
        for (Entity e : entitiesCopy) {
            e.update();
        }

        currentScene.update();
    }

    public static void reloadScene() {
        if (currentScene == null) return;

        currentScene.getEntities().clear();

        Array<Body> bodyArray = new Array<>();
        Engine.WORLD.getBodies(bodyArray);

        for (Body body : bodyArray) {
            Engine.WORLD.destroyBody(body);
        }

        EntityRegistry.clear();
        for (Entity e : currentScene.getEntities()) {
            EntityRegistry.register(e);
        }

        currentScene.enter();
    }

    public static void loadScene(String sceneName) {
        Scene newScene = scenes.get(sceneName);

        if (newScene == null) return;

        if (currentScene != newScene) {
            previousScene = currentScene;
            currentScene = newScene;

            if (previousScene != null)
                previousScene.leave();

            reloadScene();
        }
    }

}
