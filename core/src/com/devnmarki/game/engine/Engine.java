package com.devnmarki.game.engine;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.devnmarki.game.engine.scenes.SceneManager;

public class Engine {

    public static final SpriteBatch SPRITE_BATCH = new SpriteBatch();
    public static final ShapeRenderer SHAPE_RENDERER = new ShapeRenderer();

    public static float scale = 4f;

    public Engine() {

    }

    public void load() {

    }

    public void update() {
        SPRITE_BATCH.begin();

        SHAPE_RENDERER.begin(ShapeRenderer.ShapeType.Line);

        SceneManager.updateCurrentScene();

        SHAPE_RENDERER.end();

        SPRITE_BATCH.end();
    }

}
