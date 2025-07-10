package com.devnmarki.game.engine.tilemap;

import com.badlogic.gdx.maps.*;
import com.devnmarki.game.engine.Engine;
import com.devnmarki.game.engine.ecs.Component;
import com.devnmarki.game.engine.ecs.Entity;
import com.devnmarki.game.engine.math.Vector2;
import com.devnmarki.game.engine.physics.BoxCollider;

public class TilemapCollider extends Component {

    private Tilemap tilemap;

    @Override
    public void onStart() {
        super.onStart();

        tilemap = entity.getComponent(Tilemap.class);

        if (tilemap == null) return;

        MapLayer collisionLayer = tilemap.getTiledMap().getLayers().get("Collision");
        MapObjects colliderObjects = collisionLayer.getObjects();

        for (MapObject colliderObj : colliderObjects) {
            float w = colliderObj.getProperties().get("width", Float.class);
            float h = colliderObj.getProperties().get("height", Float.class);
            float x = (colliderObj.getProperties().get("x", Float.class) * Engine.scale);
            float y = (colliderObj.getProperties().get("y", Float.class) * Engine.scale);

            Vector2 colliderPos = new Vector2(x, y);
            Vector2 colliderSize = new Vector2(w, h);

            Entity colliderEntity = new Entity();
            colliderEntity.getTransform().position = colliderPos;
            colliderEntity.getTransform().size = colliderSize;

            BoxCollider colliderComp = new BoxCollider();
            colliderComp.size = colliderSize;
            colliderEntity.addComponent(colliderComp);

            colliderEntity.load();
            instantiate(colliderEntity);
        }
    }

}
