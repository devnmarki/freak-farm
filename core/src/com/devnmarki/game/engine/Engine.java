package com.devnmarki.game.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.devnmarki.game.engine.ecs.EntityDestroyer;
import com.devnmarki.game.engine.graphics.Sprite;
import com.devnmarki.game.engine.scenes.SceneManager;

public class Engine {

    private static final Engine instance = new Engine();

    public static float scale = 4f;
    public static float gravityScale = -9.81f;
    public static boolean debugMode = true;

    public static final SpriteBatch SPRITE_BATCH = new SpriteBatch();
    public static final ShapeRenderer SHAPE_RENDERER = new ShapeRenderer();
    public static final World WORLD = new World(new Vector2(0f, gravityScale), true);
    public static final float PPM = 100f;

    private Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();

    public Engine() {

    }

    public static Engine getInstance() {
        return instance;
    }

    public static void registerDeserializers() {
        Sprite.register();
    }

    public void load() {
        registerDeserializers();
    }

    public void update() {
        WORLD.step(1 / 60f, 6, 2);

        EntityDestroyer.flush();

        SPRITE_BATCH.begin();
        SHAPE_RENDERER.begin(ShapeRenderer.ShapeType.Line);

        SceneManager.updateCurrentScene();

        SHAPE_RENDERER.end();
        SPRITE_BATCH.end();

        if (debugMode) {
            debugRenderer.render(WORLD, SPRITE_BATCH.getProjectionMatrix().cpy().scale(Engine.PPM, Engine.PPM, 1));
        }
    }

}
