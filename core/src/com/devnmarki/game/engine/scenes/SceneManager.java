package com.devnmarki.game.engine.scenes;

import com.devnmarki.game.engine.ecs.Entity;

import java.util.HashMap;
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

        for (Entity e : currentScene.getEntities()) {
            e.update();
        }

        currentScene.update();
    }

    public static void reloadScene() {
        if (currentScene == null) return;

        currentScene.getEntities().clear();
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
