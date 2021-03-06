package com.mygdx.game.GameSc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGame;
import com.mygdx.game.menu.Menu;

public class Games implements Screen {
    final MyGame game;
    final Menu menu;
    private SpriteBatch batch = new SpriteBatch();
    private Texture pause;
    private Texture inventor;
    OrthographicCamera camera = new OrthographicCamera();
    GameMap gameMap;
    BattleButtons battleButtons;
    InputMultiplexer inputMultiplexer;

    public Games(MyGame myGame, Menu menu) {
        game = myGame;
        this.menu = menu;
        this.camera.setToOrtho(false, 800.0F, 480.0F);
        this.gameMap = new GameMap(this);
        battleButtons = new BattleButtons(game, this.menu);
        inputMultiplexer = new InputMultiplexer();
    }

    public void show() {
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 1.0F);
        Gdx.gl.glClear(16384);
        inputMultiplexer.addProcessor(battleButtons);
        inputMultiplexer.addProcessor(this.gameMap);
        Gdx.input.setInputProcessor(inputMultiplexer);
        this.camera.update();
        this.gameMap.draw();
        battleButtons.draw();
        this.batch.begin();
        this.batch.end();
    }

    public void resize(int width, int height) {
    }

    public void pause() {
    }

    public void resume() {
    }

    public void hide() {
    }

    public void dispose() {
        this.batch.dispose();
        this.pause.dispose();
    }
}