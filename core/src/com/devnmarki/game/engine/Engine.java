package com.devnmarki.game.engine;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.devnmarki.game.engine.animation.Animator;
import com.devnmarki.game.engine.ecs.EntityDestroyer;
import com.devnmarki.game.engine.ecs.Transform;
import com.devnmarki.game.engine.ecs.component.ComponentRegistry;
import com.devnmarki.game.engine.graphics.Sprite;
import com.devnmarki.game.engine.graphics.SpriteRenderer;
import com.devnmarki.game.engine.physics.BoxCollider;
import com.devnmarki.game.engine.physics.CollisionContactListener;
import com.devnmarki.game.engine.physics.Rigidbody;
import com.devnmarki.game.engine.scenes.SceneManager;
import com.devnmarki.game.engine.tilemap.Tilemap;
import com.devnmarki.game.engine.tilemap.TilemapCollider;
import com.devnmarki.game.engine.tilemap.TilemapEntityLoader;

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

    public void load() {
        registerDeserializers();
        registerComponents();
    }

    private void registerComponents() {
        ComponentRegistry.register("Transform", Transform.class);

        ComponentRegistry.register("Sprite Renderer", SpriteRenderer.class);
        ComponentRegistry.register("Animator", Animator.class);

        ComponentRegistry.register("Box Collider", BoxCollider.class);
        ComponentRegistry.register("Rigidbody", Rigidbody.class);

        ComponentRegistry.register("Tilemap", Tilemap.class);
        ComponentRegistry.register("Tilemap Collider", TilemapCollider.class);
        ComponentRegistry.register("Tilemap Entity Loader", TilemapEntityLoader.class);
    }

    public static void registerDeserializers() {
        Sprite.register();
    }

    public void update() {
        WORLD.step(1 / 60f, 6, 2);
        WORLD.setContactListener(new CollisionContactListener());

        EntityDestroyer.flush();

        SPRITE_BATCH.begin();
        SHAPE_RENDERER.begin(ShapeRenderer.ShapeType.Line);

        SceneManager.updateCurrentScene();

        SHAPE_RENDERER.end();
        SPRITE_BATCH.end();

        SPRITE_BATCH.setProjectionMatrix(SceneManager.currentScene.getCamera().combined);
        SHAPE_RENDERER.setProjectionMatrix(SceneManager.currentScene.getCamera().combined);

        if (debugMode) {
            debugRenderer.render(WORLD, SPRITE_BATCH.getProjectionMatrix().cpy().scale(Engine.PPM, Engine.PPM, 1));
        }
    }

}
