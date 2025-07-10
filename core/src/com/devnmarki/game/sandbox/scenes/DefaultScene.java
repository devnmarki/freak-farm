package com.devnmarki.game.sandbox.scenes;

import com.devnmarki.game.engine.Engine;
import com.devnmarki.game.engine.data.EntityReader;
import com.devnmarki.game.engine.ecs.Entity;
import com.devnmarki.game.engine.ecs.EntityRegistry;
import com.devnmarki.game.engine.math.Vector2;
import com.devnmarki.game.engine.scenes.Scene;

public class DefaultScene extends Scene {

    private Entity farmerEntity;

    @Override
    public void enter() {
        System.out.println("enter default scene");

        addEntity(EntityReader.loadEntity("assets/data/entities/tilemaps/test_tilemap.json", new Vector2()));
        addEntity(EntityReader.loadEntity("assets/data/entities/farmer.json", new Vector2(600, 300)));

        farmerEntity = EntityRegistry.findFirstByTag("player");
    }

    @Override
    public void update() {
        getCamera().position.x = farmerEntity.getTransform().position.x + (farmerEntity.getTransform().size.x / 2f);
        getCamera().position.y = farmerEntity.getTransform().position.y + (farmerEntity.getTransform().size.y / 2f);
        getCamera().update();
    }

    @Override
    public void leave() {
        System.out.println("leave default scene");
    }

}
