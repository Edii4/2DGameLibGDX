package com.edvard.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MenuScreen extends ApplicationAdapter implements Screen {

    Stage stage;
    SpriteBatch batch;
    OrthographicCamera camera;

    //Sprite startButtonSprite;
    //Sprite exitButtonSprite;

    ImageButton startButton;
    ImageButton exitButton;


    MainGame game;

    public MenuScreen(MainGame game) {

        stage = new Stage(new ScreenViewport());
        this.game = game;

        float height = Gdx.graphics.getHeight();
        float width = Gdx.graphics.getWidth();

        camera = new OrthographicCamera(width, height);
        camera.setToOrtho(false);

        batch = new SpriteBatch();

        Drawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/play01.png"))));
        startButton = new ImageButton(drawable);
        drawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/back01.png"))));
        exitButton = new ImageButton(drawable);

        startButton.setSize(790/4, 342/4);
        exitButton.setSize(790/4, 342/4);

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

        //startButtonSprite = new Sprite(new Texture("buttons/play01.png"));
        //exitButtonSprite = new Sprite(new Texture("buttons/back01.png"));

        //startButtonSprite.setSize(790/4, 342/4);
        //exitButtonSprite.setSize(790/4, 342/4);

        //startButtonSprite.setPosition((float) (width/2.7), (float) (height/1.8));
        //exitButtonSprite.setPosition((float) (width/2.7), (float) (height/3.2));

    }

    @Override
    public void show() {


    }

    @Override
    public void render(float v) {

        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        stage.act();
        stage.draw();
        batch.end();

    }

    @Override
    public void resize(int i, int i1) {
        stage.getViewport().update(i, i1, true);
        startButton.setPosition((float) Gdx.graphics.getWidth()/2 - startButton.getWidth()/2, ((float) Gdx.graphics.getHeight() /2) + startButton.getHeight()/2);
        exitButton.setPosition((float) Gdx.graphics.getWidth()/2 - exitButton.getWidth()/2, ((float) Gdx.graphics.getHeight() /2) - exitButton.getHeight());
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
        batch.dispose();


    }
}
