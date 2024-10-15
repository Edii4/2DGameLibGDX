package com.edvard.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.edvard.game.MainGame;
import com.edvard.game.scenes.Hud;

public class PlayScreen implements Screen {

    private MainGame game;
    private OrthographicCamera camera;
    private Viewport gameport;

    private TmxMapLoader mapLoader;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer renderer;
    private Hud hud;

    public PlayScreen(MainGame game) {
        this.game = game;
        camera = new OrthographicCamera();

        hud = new Hud(game.batch);

        gameport = new FitViewport(MainGame.V_WIDTH, MainGame.V_HEIGHT, camera);
        gameport.apply();

        mapLoader = new TmxMapLoader();
        tiledMap = mapLoader.load("maps/first-map.tmx");
        renderer = new OrthogonalTiledMapRenderer(tiledMap);

        camera.position.set(gameport.getWorldWidth() / 2, gameport.getWorldHeight() / 2, 0);

    }

    @Override
    public void show() {

    }

    public void handleInput(float delta) {
        if(Gdx.input.isTouched()) {
            camera.position.x += 100 * delta;
        }
    }

    public void update(float delta) {
        handleInput(delta);

        camera.update();

        renderer.setView(camera);
    }

    @Override
    public void render(float v) {
        update(v);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        //hud.stage.draw();

        renderer.render();
        hud.stage.draw();

    }

    @Override
    public void resize(int i, int i1) {
        gameport.update(i, i1);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
