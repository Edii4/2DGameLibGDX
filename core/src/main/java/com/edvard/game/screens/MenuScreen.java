package com.edvard.game.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.edvard.game.MainGame;

public class MenuScreen implements Screen {

    Stage stage;
    OrthographicCamera camera;

    ImageButton startButton;
    ImageButton exitButton;

    private MainGame game;

    public MenuScreen(MainGame game) {

        stage = new Stage(new ScreenViewport());
        this.game = game;

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false);

        game.batch = new SpriteBatch();

        Drawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/play01.png"))));
        startButton = new ImageButton(drawable);
        drawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/back01.png"))));
        exitButton = new ImageButton(drawable);

        startButton.setSize((float) Gdx.graphics.getWidth() /4, (float) Gdx.graphics.getHeight() /4);
        exitButton.setSize((float) Gdx.graphics.getWidth() /4, (float) Gdx.graphics.getHeight() /4);

        startButton.setPosition((float) Gdx.graphics.getWidth()/2 - startButton.getWidth()/2, ((float) Gdx.graphics.getHeight() /2) + startButton.getHeight()/2);
        exitButton.setPosition((float) Gdx.graphics.getWidth()/2 - exitButton.getWidth()/2, ((float) Gdx.graphics.getHeight() /2) - exitButton.getHeight());

        startButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/play01.png"))));
        startButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/play03.png"))));
        exitButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/back01.png"))));
        exitButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/back03.png"))));

        startButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/button-clicked-sound.mp3"));
                sound.play();
                startButton.remove();
                exitButton.remove();
                game.setScreen(new PlayScreen(game));
                System.out.println("clicked");
            }
        });
        exitButton.addListener(new ClickListener() {
           public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
               Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/button-clicked-sound.mp3"));
               sound.play();
               return true;
           }
           public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
               Gdx.app.exit();
           }
        });

        stage.addActor(startButton);
        stage.addActor(exitButton);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        stage.act();
        stage.draw();
        game.batch.end();
    }

    @Override
    public void resize(int i, int i1) {
        stage.getViewport().update(i, i1, true);
        startButton.setPosition((float) Gdx.graphics.getWidth()/2 - startButton.getWidth()/2, ((float) Gdx.graphics.getHeight() /2) + startButton.getHeight()/2);
        exitButton.setPosition((float) Gdx.graphics.getWidth()/2 - exitButton.getWidth()/2, ((float) Gdx.graphics.getHeight()/2) - exitButton.getHeight());
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
        game.batch.dispose();
    }
}
