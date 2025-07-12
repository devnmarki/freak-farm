package com.devnmarki.game.engine.scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.devnmarki.game.engine.ecs.Entity;

public class CameraEntity extends Entity {

    private OrthographicCamera camera;

    public CameraEntity(int viewportWidth, int viewportHeight) {
        super();

        camera = new OrthographicCamera(viewportWidth, viewportHeight);
    }

    public OrthographicCamera getCamera() {
        return camera;
    }
}
