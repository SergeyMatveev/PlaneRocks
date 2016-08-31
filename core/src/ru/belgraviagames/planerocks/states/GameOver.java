package ru.belgraviagames.planerocks.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.belgraviagames.planerocks.PlaneRocks;

/**
 * Created by Sergey on 30.07.2016.
 */
public class GameOver extends State {

    private Texture background;
    private Texture gameOver;

    public GameOver(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, PlaneRocks.WIDTH, PlaneRocks.HEIGHT);
        background = new Texture("background.png");
        gameOver = new Texture("gameOver.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }

    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        sb.draw(gameOver, camera.position.x - (gameOver.getWidth() / 2), camera.position.y);
        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        gameOver.dispose();
        System.out.println("GameOver Dispose");

    }
}