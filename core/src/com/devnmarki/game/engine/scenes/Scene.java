package com.devnmarki.game.engine.scenes;

import com.devnmarki.game.engine.ecs.Entity;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene {

    private int index;
    private List<Entity> entities = new ArrayList<>();

    public abstract void enter();
    public abstract void update();
    public abstract void leave();

    public void addEntity(Entity entity) {
        entities.add(entity);
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

}
