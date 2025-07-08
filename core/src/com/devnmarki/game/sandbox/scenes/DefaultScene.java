package com.devnmarki.game.sandbox.scenes;

import com.devnmarki.game.engine.scenes.Scene;
import com.devnmarki.game.sandbox.characters.FarmerEntity;

public class DefaultScene extends Scene {

    @Override
    public void enter() {
        System.out.println("enter default scene");

        addEntity(new FarmerEntity());
    }

    @Override
    public void update() {

    }

    @Override
    public void leave() {
        System.out.println("leave default scene");
    }

}
