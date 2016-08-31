package ru.belgraviagames.planerocks.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import ru.belgraviagames.planerocks.PlaneRocks;
import ru.belgraviagames.planerocks.sprites.Plane;
import ru.belgraviagames.planerocks.sprites.Rock;

/**
 * Created by Sergey on 01.08.2016.
 */
public class PlayState extends State {

    public static final int ROCK_SPACING = 300;
    public static final int ROCK_COUNT = 5;
    public static final int GROUND_Y_OFFSET = -10;
    public static final int GROUND_X_OFFSET = -150;

    private Plane plane;
    private Texture bg;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;

    private Array<Rock> rocks;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        plane = new Plane(50, 200);
        camera.setToOrtho(false, PlaneRocks.WIDTH ,PlaneRocks.HEIGHT);
        bg = new Texture("background.png");
        ground = new Texture("ground.png");
        groundPos1 = new Vector2(camera.position.x - camera.viewportWidth / 2 + GROUND_X_OFFSET, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((camera.position.x - camera.viewportWidth / 2) + ground.getWidth() + GROUND_X_OFFSET, GROUND_Y_OFFSET);
        rocks = new Array<Rock>();

        for (int i = 0; i < ROCK_COUNT; i++){
            rocks.add(new Rock(i * (ROCK_SPACING + Rock.ROCK_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched())
            plane.jump();

    }

    @Override
    public void update(float dt) {
        handleInput();
        plane.update(dt);
        updateGround();
        camera.position.x = plane.getPosition().x + 180;

        for (int i = 0; i < rocks.size; i++){
            Rock rock = rocks.get(i);
            if (camera.position.x - (camera.viewportWidth / 2) > rock.getPosTopRock().x + rock.getTopRock().getWidth()){
                rock.reposition(rock.getPosTopRock().x + ((Rock.ROCK_WIDTH + ROCK_SPACING) * ROCK_COUNT));
            }
            if (rock.collides(plane.getBounds())){
                gsm.set(new GameOver(gsm));
            }
        }
        camera.update();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(bg, camera.position.x - (camera.viewportWidth / 2), 0);
        sb.draw(plane.getPlane(), plane.getPosition().x, plane.getPosition().y);
        for (Rock rock : rocks){
                sb.draw(rock.getTopRock(), rock.getPosTopRock().x, rock.getPosTopRock().y);
                sb.draw(rock.getBtmRock(), rock.getPosBtmRock().x, rock.getPosBtmRock().y);
        }
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);

        sb.end();

    }

    @Override
    public void dispose() {
        bg.dispose();
        plane.dispose();
        ground.dispose();
        for (Rock rock : rocks)
            rock.dispose();
        System.out.println("PlayState Dispose");

    }
    public void updateGround() {
        if (camera.position.x - (camera.viewportWidth / 2) > groundPos1.x + ground.getWidth())
            groundPos1.add(ground.getWidth() * 2, 0);
        if (camera.position.x - (camera.viewportWidth / 2) > groundPos2.x + ground.getWidth())
            groundPos2.add(ground.getWidth() * 2, 0);
    }




}
