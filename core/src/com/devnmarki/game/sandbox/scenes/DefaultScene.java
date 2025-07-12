package com.devnmarki.game.sandbox.scenes;

import com.devnmarki.game.engine.data.EntityReader;
import com.devnmarki.game.engine.ecs.Entity;
import com.devnmarki.game.engine.ecs.EntityRegistry;
import com.devnmarki.game.engine.math.Vector2;
import com.devnmarki.game.engine.scenes.Scene;
import com.devnmarki.game.sandbox.components.CameraController;

public class DefaultScene extends Scene {

    public DefaultScene() {
        super();
    }

    @Override
    public void enter() {
        System.out.println("enter default scene");

        addEntity(EntityReader.loadEntity("assets/data/entities/tilemaps/test_tilemap.json", new Vector2()));

        getCameraEntity().addComponent(new CameraController());
        getCameraEntity().load();
        addEntity(getCameraEntity());
    }

    @Override
    public void update() {

    }

    @Override
    public void leave() {
        System.out.println("leave default scene");
    }

}
