package com.devnmarki.game.engine.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.devnmarki.game.engine.ecs.Entity;
import com.devnmarki.game.engine.ecs.EntityRegistry;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene {

    private int index;
    private List<Entity> entities = new ArrayList<>();
    private OrthographicCamera camera;

    protected Scene() {
        this.createCamera();
    }

    private void createCamera() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.x = Gdx.graphics.getWidth() / 2f;
        camera.position.y = Gdx.graphics.getHeight() / 2f;
        camera.update();
    }

    public abstract void enter();
    public abstract void update();
    public abstract void leave();

    public void addEntity(Entity entity) {
        entities.add(entity);
        EntityRegistry.register(entity);
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

}
