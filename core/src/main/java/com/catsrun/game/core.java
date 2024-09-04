package com.catsrun.game;

import static com.catsrun.game.GameSettings.POSITION_ITERATIONS;
import static com.catsrun.game.GameSettings.SCREEN_HEIGHT;
import static com.catsrun.game.GameSettings.SCREEN_WIDTH;
import static com.catsrun.game.GameSettings.STEP_TIME;
import static com.catsrun.game.GameSettings.VELOCITY_ITERATIONS;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class core extends Game {
    GameScreen gameScreen;
    public SpriteBatch batch;

    public OrthographicCamera camera;


    float accumulator = 0;
    public World world;
    @Override
    public void create() {
        Box2D.init();
        world = new World(new Vector2(0,0),true);

        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCREEN_WIDTH,SCREEN_HEIGHT);

        gameScreen = new GameScreen(this);


        setScreen(gameScreen);
    }
    public void stepWorld(){
        float delta = Gdx.graphics.getDeltaTime();
        accumulator += Math.min(delta,0.25f);

        if (accumulator >= STEP_TIME){
            accumulator -= STEP_TIME;
            world.step(STEP_TIME,VELOCITY_ITERATIONS,POSITION_ITERATIONS);

        }

    }
}
