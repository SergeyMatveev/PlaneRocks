package ru.belgraviagames.planerocks.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Sergey on 28.08.2016.
 */
public class Animation {
    private Array<TextureRegion> frames;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frame;
    private int frameCount;

    public Animation(TextureRegion region, int frameCount, float cycleTime) {
        frames = new Array<TextureRegion>();
        int frameWidth = region.getRegionWidth() / frameCount;
        for (int i = 0; i < frameCount; i++){
            frames.add(new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight()));
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }
    public void update (float dt){
        currentFrameTime += dt;
        if (currentFrameTime > maxFrameTime){
            frame++;
            currentFrameTime = 0;
        }
        if (frame >= frameCount){
            frame = 0;
        }
    }
    public TextureRegion getFrame (){
        return frames.get(frame);
    }
}
