package com.devnmarki.game.engine.ecs;

public abstract class Component {

    protected Entity entity = null;

    public void onStart() {
    }

    public void onUpdate() {
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
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
