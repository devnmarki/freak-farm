package com.devnmarki.game.engine.ecs;

import com.devnmarki.game.engine.Engine;
import com.devnmarki.game.engine.physics.Collider;

import java.util.List;
import java.util.ArrayList;

public class Entity {

    private List<Component> components = new ArrayList<com.devnmarki.game.engine.ecs.Component>();
    private List<Collider> colliders = new ArrayList<>();

    private String tag;
    private String name;

    protected Transform transform;

    public Entity() {
        this.tag = "untagged";
        this.name = "Entity";

        this.transform = new Transform();
        this.addComponent(transform);
    }

    public void load() {
        for (Component c : components) {
            c.onStart();
        }
    }

    public void update() {
        for (Component c : components) {
            c.onUpdate();
        }
    }

    public void addComponent(Component component) {
        component.setEntity(this);

        if (component instanceof Collider) {
            colliders.add((Collider) component);
        }

        components.add(component);
    }

    public void clearColliders() {
        for (Collider collider : colliders) {
            if (collider != null && collider.getBody() != null) {
                Engine.WORLD.destroyBody(collider.getBody());
            }
        }
        colliders.clear();
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static <T extends Entity> T createEntity(T entity) {
        entity.load();
        return entity;
    }

    public <T extends Component> T getComponent(Class<T> type) {
        for (Component comp : components) {
            if (type.isInstance(comp)) {
                return type.cast(comp);
            }
        }

        return null;
    }

    public List<Component> getComponents() {
        return components;
    }

    public List<Collider> getColliders() {
        return colliders;
    }

    public String getTag() {
        return tag;
    }

    public String getName() {
        return name;
    }

    public Transform getTransform() {
        return transform;
    }

}
