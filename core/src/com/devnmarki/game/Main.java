package com.devnmarki.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.devnmarki.game.engine.Engine;
import com.devnmarki.game.engine.scenes.Scene;
import com.devnmarki.game.engine.scenes.SceneManager;
import com.devnmarki.game.sandbox.scenes.*;

public class Main extends ApplicationAdapter {

	@Override
	public void create () {
		Engine.getInstance().load();

        SceneManager.addScene("default", new DefaultScene());
        SceneManager.addScene("main_menu", new MainMenuScene());

        SceneManager.loadScene("default");
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
