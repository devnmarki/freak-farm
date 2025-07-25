package com.devnmarki.game.engine.ecs;

import com.devnmarki.game.engine.math.Vector2;
import com.devnmarki.game.engine.scenes.SceneManager;

public abstract class Component {

    protected Entity entity = null;

    public void onStart() { }

    public void onUpdate() { }

    protected void instantiate(Entity entity) {
        SceneManager.currentScene.addEntity(entity);
    }

    protected void destroy() {
        if (entity == null) return;

        destroy(entity);
    }

    protected void destroy(Entity e) {
        if (e == null) return;

        EntityDestroyer.queue(e);
    }

    public void onCollisionEnter(Entity other, Vector2 normal) { }
    public void onCollisionExit(Entity other) { }
    public void onPreCollision(Entity other) { }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }

}
