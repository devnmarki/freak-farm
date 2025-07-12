package com.devnmarki.game.engine.ecs;

import com.devnmarki.game.engine.ecs.component.Component;

import java.util.*;

public class EntityRegistry {

    private static final Map<String, List<Entity>> tagMap = new HashMap<>();
    private static final Map<String, Entity> nameMap = new HashMap<>();

    public static void register(Entity entity) {
        if (entity.getName() != null) {
            nameMap.put(entity.getName(), entity);
        }

        if (entity.getTag() != null) {
            List<Entity> list = tagMap.get(entity.getTag());
            if (list == null) {
                list = new ArrayList<Entity>();
                tagMap.put(entity.getTag(), list);
            }
            list.add(entity);
        }
    }

    public static void unregister(Entity entity) {
        nameMap.remove(entity.getName());

        List<Entity> tagged = tagMap.get(entity.getTag());
        if (tagged != null) {
            tagged.remove(entity);
            if (tagged.isEmpty())
                tagMap.remove(entity.getTag());
        }
    }

    public static Entity findFirstByName(String name) {
        return nameMap.get(name);
    }

    public static Entity findFirstByTag(String tag) {
        List<Entity> entities = tagMap.get(tag);
        return (entities != null && !entities.isEmpty()) ? entities.get(0) : null;
    }

    public static List<Entity> findByTag(String tag) {
        List<Entity> result = tagMap.get(tag);
        return result != null ? result : Collections.<Entity>emptyList();
    }

    public static <T extends Component> List<T> findComponentsByType(Class<T> type) {
        List<T> result = new ArrayList<>();
        for (Entity entity : nameMap.values()) {
            for (Component c : entity.getComponents()) {
                if (type.isInstance(c))
                    result.add(type.cast(c));
            }
        }
        return result;
    }

    public static void clear() {
        tagMap.clear();
        nameMap.clear();
    }

}
