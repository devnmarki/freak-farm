package com.devnmarki.game.engine.ecs;

import com.badlogic.gdx.physics.box2d.Contact;
import com.devnmarki.game.engine.Engine;
import com.devnmarki.game.engine.math.Vector2;
import com.devnmarki.game.engine.physics.Collider;

import java.util.List;
import java.util.ArrayList;

public class Entity implements IEntity {

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

    @Override
    public void collisionBegin(Entity other, Vector2 normal, Contact contact) {
        for (Component c : components) {
            c.onCollisionEnter(other, normal);
        }
    }

    @Override
    public void collisionEnd(Entity other) {
        for (Component c : components) {
            c.onCollisionExit(other);
        }
    }

    @Override
    public void collisionPreSolve(Entity other, Contact contact) {
        for (Component c : components) {
            c.onPreCollision(other);
        }
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

    public boolean hasComponent(Class<? extends Component> type) {
        for (Component comp : components) {
            if (type.isInstance(comp)) {
                return true;
            }
        }

        return false;
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
