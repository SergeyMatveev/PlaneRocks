package ru.belgraviagames.planerocks.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;



/**
 * Created by Sergey on 27.08.2016.
 */
public class Rock {
    public static final int ROCK_WIDTH = 40;
    public static final int FLUCTUATION = 150;
    public static final int ROCK_GAP = 150;
    public static final int LOWEST_OPENING = 80;
    public static final int BOUND_WIDTH = 5;
    private Texture topRock, btmRock;
    private Vector2 posTopRock, posBtmRock;
    private Random rand;
    private Rectangle boundsTop, boundsBtm;

    public Texture getTopRock() {
        return topRock;
    }

    public Texture getBtmRock() {
        return btmRock;
    }

    public Vector2 getPosTopRock() {
        return posTopRock;
    }

    public Vector2 getPosBtmRock() {
        return posBtmRock;
    }

    public Rock(float x){
        topRock = new Texture("topRock.png");
        btmRock = new Texture("btmRock.png");
        rand = new Random();

        posTopRock = new Vector2(x, rand.nextInt(FLUCTUATION) + ROCK_GAP + LOWEST_OPENING);
        posBtmRock = new Vector2(x, posTopRock.y - ROCK_GAP - btmRock.getHeight());

        boundsTop = new Rectangle(posTopRock.x + ROCK_WIDTH, posTopRock.y, BOUND_WIDTH, topRock.getHeight());
        boundsBtm = new Rectangle(posBtmRock.x + ROCK_WIDTH, posBtmRock.y, BOUND_WIDTH, btmRock.getHeight());

    }
    public void reposition (float x){
        posTopRock.set(x, rand.nextInt(FLUCTUATION) + ROCK_GAP + LOWEST_OPENING);
        posBtmRock.set(x, posTopRock.y - ROCK_GAP - btmRock.getHeight());
        boundsTop.setPosition(posTopRock.x + ROCK_WIDTH, posTopRock.y);
        boundsBtm.setPosition(posBtmRock.x + ROCK_WIDTH, posBtmRock.y);
    }

    public boolean collides (Rectangle player){
        return player.overlaps(boundsTop) || player.overlaps(boundsBtm);
    }

    public void dispose() {
        topRock.dispose();
        btmRock.dispose();
    }

}
