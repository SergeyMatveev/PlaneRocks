package ru.belgraviagames.planerocks.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import sun.security.provider.PolicySpiFile;

/**
 * Created by Sergey on 19.08.2016.
 */
public class Plane {
    private static final int MOVEMENT = 150;
    private static final int GRAVITY = -15;
    private Vector3 position;
    private Vector3 velocity;
    public Rectangle bounds;
    private Animation planeAnimation;
    private Music propeller;

    private Texture texture;

    public Plane(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        texture = new Texture("planeAnim.png");
        planeAnimation = new Animation(new TextureRegion(texture), 3, 0.2f);
        bounds = new Rectangle(x, y, (texture.getWidth() / 3) - 25, texture.getHeight());
        propeller = Gdx.audio.newMusic(Gdx.files.internal("plane.mp3"));
        propeller.setVolume(0.3f);
        propeller.setLooping(true);
        propeller.play();
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getPlane() {
        return planeAnimation.getFrame();
    }

    public void update(float dt){
        planeAnimation.update(dt);
        if (position.y > 0)
            velocity.add(0, GRAVITY, 0);
        velocity.scl(dt);
        position.add(MOVEMENT * dt, velocity.y, 0);
        if (position.y < 0)
            position.y = 0;

        velocity.scl(1 / dt);
        bounds.setPosition(position.x, position.y);


    }
    public void jump(){
        velocity.y = 250;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void dispose() {
        texture.dispose();
        propeller.dispose();
    }
}
