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

	private Engine engine;

	@Override
	public void create () {
		engine = new Engine();
		engine.load();

        SceneManager.addScene("default", new DefaultScene());
        SceneManager.addScene("main_menu", new MainMenuScene());

        SceneManager.loadScene("default");
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);

		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}

		engine.update();

		if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
			SceneManager.loadScene("default");
		} else if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
			SceneManager.loadScene("main_menu");
		}
	}
	
	@Override
	public void dispose () {

	}
}
