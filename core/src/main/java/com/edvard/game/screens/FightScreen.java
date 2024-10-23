package com.edvard.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.edvard.game.MainGame;
import com.edvard.game.units.Peasant;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.awt.*;

public class FightScreen implements Screen {

    private final int MAX_ROW = 10;
    private final int MAX_COL = 10;

    Peasant heroPeasant;
    Label heroPeasantQuantity;
    Peasant enemyPeasant;

    Stage stage;
    OrthographicCamera camera;

    private MainGame game;

    ImageButton[][] buttons = new ImageButton[MAX_ROW][MAX_COL];

    public static PlayScreen playScreen;

    public FightScreen(MainGame game) {

        stage = new Stage(new ScreenViewport());
        this.game = game;
        game.batch = new SpriteBatch();
        createField();
        createUnits();
        placeUnits();

    }

    public void placeUnits() {
        for(int i = 0; i < MAX_ROW; i++) {
            for(int j = 0; j < MAX_COL; j++) {
                int finalI = i;
                int finalJ = j;
                buttons[i][j].addListener(new ClickListener() {
                    public void clicked(InputEvent event, float x, float y) {
                        buttons[finalI][finalJ].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/peasant.png"))));
                        heroPeasantQuantity = new Label(String.valueOf(heroPeasant.getQuantity()), new Label.LabelStyle(new BitmapFont(), Color.CYAN));
                        heroPeasantQuantity.setPosition(buttons[finalI][finalJ].getX() + 32, buttons[finalI][finalJ].getY());
                        stage.addActor(heroPeasantQuantity);
                    }
                });
            }
        }
    }

    public void createUnits() {
        heroPeasant = new Peasant(true, 100, 3, 5, 10);
        enemyPeasant = new Peasant(false, 100, 3, 5, 10);
    }

    public void createField() {
        for(int i = 0; i < MAX_COL; i++) {
            for(int j = 0; j < MAX_ROW; j++) {
                buttons[i][j] = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field.png")))));
                //buttons[i][j].setColor(0, 0, 0, 1);
                buttons[i][j].setPosition(160 + i * 64, 160 + j * 64);
                buttons[i][j].setSize(64, 64);
                buttons[i][j].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field.png"))));
                buttons[i][j].getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field_down.png"))));

                stage.addActor(buttons[i][j]);
            }
        }
    }

    public void gameScreen() {
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            game.setScreen(playScreen);
            System.out.println("esc");

        }
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float v) {
        gameScreen();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        stage.draw();
        game.batch.end();

    }

    @Override
    public void resize(int i, int i1) {

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
