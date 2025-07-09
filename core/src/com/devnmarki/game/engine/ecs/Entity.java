package com.devnmarki.game.engine.ecs;

import java.util.List;
import java.util.ArrayList;

public class Entity {

    private List<Component> components = new ArrayList<com.devnmarki.game.engine.ecs.Component>();

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
        components.add(component);
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

    public List<Component> getComponents() {
        return components;
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
