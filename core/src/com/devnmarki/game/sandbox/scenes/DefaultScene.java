package com.devnmarki.game.sandbox.scenes;

import com.devnmarki.game.engine.data.EntityReader;
import com.devnmarki.game.engine.ecs.Entity;
import com.devnmarki.game.engine.math.Vector2;
import com.devnmarki.game.engine.physics.ColliderEntity;
import com.devnmarki.game.engine.scenes.Scene;
import com.devnmarki.game.sandbox.characters.FarmerEntity;

public class DefaultScene extends Scene {

    @Override
    public void enter() {
        System.out.println("enter default scene");

        addEntity(EntityReader.loadEntity("assets/data/entities/farmer.json", new Vector2(400, 300)));
        addEntity(EntityReader.loadEntity("assets/data/entities/ground.json", new Vector2(100, 100)));
    }

    @Override
    public void update() {

    }

    @Override
    public void leave() {
        System.out.println("leave default scene");
    }

}
