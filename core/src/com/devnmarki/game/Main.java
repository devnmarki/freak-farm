package com.devnmarki.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.ScreenUtils;
import com.devnmarki.game.engine.Engine;
import com.devnmarki.game.engine.ecs.component.ComponentRegistry;
import com.devnmarki.game.engine.physics.LayerCollision;
import com.devnmarki.game.engine.scenes.SceneManager;
import com.devnmarki.game.engine.tilemap.TilemapEntityLoader;
import com.devnmarki.game.sandbox.Constants;
import com.devnmarki.game.sandbox.EntitiesContainer;
import com.devnmarki.game.sandbox.components.characters.Farmer;
import com.devnmarki.game.sandbox.components.objects.Bullet;
import com.devnmarki.game.sandbox.components.objects.Crate;
import com.devnmarki.game.sandbox.scenes.*;

public class Main extends ApplicationAdapter {

	@Override
	public void create () {
		Engine.getInstance().load();

		ComponentRegistry.register("Farmer", Farmer.class);
		ComponentRegistry.register("Bullet", Bullet.class);
		ComponentRegistry.register("Crate", Crate.class);

		TilemapEntityLoader.register("Farmer", EntitiesContainer.Characters.FARMER);
		TilemapEntityLoader.register("Crate", EntitiesContainer.Objects.CRATE);

        SceneManager.addScene("default", new DefaultScene());
        SceneManager.addScene("main_menu", new MainMenuScene());

        SceneManager.loadScene("default");

		LayerCollision.setCollision(Constants.CollisionLayers.PLAYER, Constants.CollisionLayers.OBJECT, false);
	}

	@Override
	public void render () {
		ScreenUtils.clear(44f/255f, 159f/255f, 216f/255f, 1);

		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}

		Engine.getInstance().update();

		if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
			SceneManager.loadScene("default");
		} else if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
			SceneManager.loadScene("main_menu");
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.TAB)) {
			Engine.debugMode = !Engine.debugMode;
		}
	}
}
