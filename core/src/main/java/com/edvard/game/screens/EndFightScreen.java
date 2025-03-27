package com.edvard.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.edvard.game.MainGame;

public class EndFightScreen implements Screen {

    int reward;

    Table table;

    private MainGame game;
    Stage stage;

    OrthographicCamera camera;

    public EndFightScreen(MainGame game, boolean won, int reward) {
        this.reward = reward;
        this.game = game;
        stage = new Stage();
        camera = new OrthographicCamera();

        table = new Table();
        table.setBounds(0, 0, 960, 960);

        table.row().colspan(2).expandX();

        if(won) {
            youWon();
        }
        else {
            youLost();
        }

        ImageButton nextButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/after-fight/next01.png")))));
        nextButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/after-fight/next01.png"))));
        nextButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/after-fight/next03.png"))));

        nextButton.addListener(new ClickListener() {
           public void clicked(InputEvent event, float x, float y) {
               game.setScreen(FightScreen.playScreen);
           }
        });
        table.row().colspan(2).expandX();
        table.add(nextButton).align(Align.center);

        stage.addActor(table);

    }

    public void youWon() {
        Label wonLabel = new Label("YOU WON!\n", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        wonLabel.setFontScale(2f);
        table.add(wonLabel).align(Align.center);

        table.row().expandX();
        Label rewardLabel = new Label("Your reward:  " + reward, new Label.LabelStyle(new BitmapFont(), Color.GOLD));
        rewardLabel.setFontScale(1.2f);
        table.add(rewardLabel).align(Align.right).padBottom(5);
        ImageButton goldButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/after-fight/gold.png")))));
        table.add(goldButton).align(Align.left).padBottom(5);

    }

    public void youLost() {
        Label loseLabel = new Label("YOU LOST!\n\n", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        loseLabel.setFontScale(2f);
        table.add(loseLabel).align(Align.center);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
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
        stage.dispose();
        game.batch.dispose();
    }
}
