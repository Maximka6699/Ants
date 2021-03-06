package com.mygdx.game.GameSc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.MyGame;
import com.mygdx.game.UnitsPack.Enemy;
import com.mygdx.game.UnitsPack.Player;
import com.mygdx.game.menu.Menu;

public class GameMap extends Stage {
    int textureSize = 32;
    Texture spriteOne, spriteTwo;
    final Games games;
    int mapArray[][];
    float height = Gdx.graphics.getHeight(), width = Gdx.graphics.getWidth();
    float x, y;
    public boolean A = false;
    Player player;
    Enemy enemy;
    Vector3 touchPos;
    OrthographicCamera camera;
    HealthBar healthBar, enemyHealthBar;

    public GameMap(Games games) {
        this.games = games;

        player = new Player("Player", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        enemy = new Enemy("Enemy", Gdx.graphics.getWidth() / 3 * 2, Gdx.graphics.getHeight() / 2);

        camera = new OrthographicCamera();
        touchPos = new Vector3();
        camera.unproject(touchPos);

        healthBar = new HealthBar(player.getHitpoints(), player.getX(), player.getY());
        addActor(healthBar);

        enemyHealthBar = new HealthBar(enemy.getHitpoints(), enemy.getX(), enemy.getY());
        addActor(healthBar);

        spriteOne = new Texture("spriteOne.png");
        spriteTwo = new Texture("spriteTwo.png");

        mapArray = new int[(int) height / textureSize][(int) width / textureSize];

        for (int i = 0; i < mapArray.length; i++) {
            for (int j = 0; j < mapArray[i].length; j++) {
                if (i <= mapArray.length / 4 || j <= mapArray[i].length / 4 || i >= mapArray.length / 4 * 3 || j >= mapArray[i].length / 4 * 3)
                    mapArray[i][j] = 1;
                else
                    mapArray[i][j] = 0;
            }
        }
    }

    @Override
    public void draw() {
        super.draw();
        x = 0;
        y = 0;
        SpriteBatch batch = new SpriteBatch();
        batch.begin();
        for (int i = 0; i < mapArray.length; i++) {
            for (int j = 0; j < mapArray[i].length; j++) {
                if (mapArray[i][j] == 1)
                    batch.draw(spriteTwo, x, y);
                else
                    batch.draw(spriteOne, x, y);
                x += textureSize;
            }
            y += textureSize;
            x = 0;
        }
        player.draw(batch);
        if (player.B) {
            player.Move(touchPos.x, touchPos.y);
        }
        player.update(enemy);
        enemy.draw(batch);
        enemy.update(player);
        healthBar.draw(batch, 1, player.getX(), player.getY());
        enemyHealthBar.draw(batch, 1, enemy.getX(), enemy.getY());
        batch.end();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
        player.B = true;
        touchPos.set(screenX, screenY, 0);
        return player.B;
    }
}
