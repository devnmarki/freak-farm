package com.devnmarki.game.engine.ecs;

import com.devnmarki.game.engine.Engine;
import com.devnmarki.game.engine.math.Vector2;
import com.devnmarki.game.engine.scenes.SceneManager;

public abstract class Component {

    protected Entity entity = null;

    public void onStart() {
    }

    public void onUpdate() {
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public void instantiate(Entity entity) {
        SceneManager.currentScene.addEntity(entity);
    }

    public <T extends Component> T getComponent(Class<T> type) {
        for (Component comp : entity.getComponents()) {
            if (type.isInstance(comp)) {
                return type.cast(comp);
            }
        }

        return null;
    }

    public Entity getEntity() {
        return entity;
    }

}
