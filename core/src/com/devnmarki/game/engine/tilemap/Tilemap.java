package com.devnmarki.game.engine.tilemap;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.devnmarki.game.engine.Engine;
import com.devnmarki.game.engine.ecs.Component;
import com.devnmarki.game.engine.scenes.SceneManager;

public class Tilemap extends Component {

    public String mapPath;

    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;

    @Override
    public void onStart() {
        super.onStart();

        map = new TmxMapLoader().load(mapPath);
        mapRenderer = new OrthogonalTiledMapRenderer(map, Engine.scale);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        mapRenderer.setView(SceneManager.currentScene.getCamera());

        mapRenderer.render();
    }

    public TiledMap getTiledMap() {
        return map;
    }

}
