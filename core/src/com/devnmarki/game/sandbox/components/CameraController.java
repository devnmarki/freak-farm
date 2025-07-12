package com.devnmarki.game.sandbox.components;

import com.devnmarki.game.engine.ecs.component.Component;
import com.devnmarki.game.engine.ecs.Entity;
import com.devnmarki.game.engine.ecs.EntityRegistry;
import com.devnmarki.game.engine.scenes.SceneManager;

public class CameraController extends Component {

    private Entity player;

    @Override
    public void onStart() {
        super.onStart();

        player = EntityRegistry.findFirstByTag("player");
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (player == null) return;

        SceneManager.currentScene.getCamera().position.x = player.getTransform().position.x + (player.getTransform().size.x / 2f);
        SceneManager.currentScene.getCamera().position.y = player.getTransform().position.y + (player.getTransform().size.y / 2f);
        SceneManager.currentScene.getCamera().update();
    }
}
