package com.devnmarki.game.engine.tilemap;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.devnmarki.game.engine.Engine;
import com.devnmarki.game.engine.data.EntityReader;
import com.devnmarki.game.engine.ecs.Component;
import com.devnmarki.game.engine.ecs.Entity;
import com.devnmarki.game.engine.math.Vector2;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class TilemapEntityLoader extends Component {

    public String layerName = "Entities";

    private Tilemap tilemap;

    private static final Map<String, String> entityRegistry = new HashMap<>();

    public static void register(String name, String entity) {
        entityRegistry.put(name, entity);
    }

    @Override
    public void onStart() {
        super.onStart();

        tilemap = entity.getComponent(Tilemap.class);
        if (tilemap == null) return;

        MapLayer layer = tilemap.getTiledMap().getLayers().get(layerName);
        if (layer == null) return;

        for (MapObject obj : layer.getObjects()) {
            String objName = obj.getName();

            float x = obj.getProperties().get("x", Float.class) * Engine.scale;
            float y = obj.getProperties().get("y", Float.class) * Engine.scale;

            instantiate(EntityReader.loadEntity(entityRegistry.get(objName), new Vector2(x, y)));
        }
    }
}
