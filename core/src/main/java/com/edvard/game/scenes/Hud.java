package com.edvard.game.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.edvard.game.MainGame;
import com.edvard.game.screens.PlayScreen;
import com.edvard.game.sprites.Hero;

public class Hud implements Disposable {
    public Stage stage;
    private Viewport viewport;

    private Integer level;
    private Integer hp;
    private Integer mp;

    Label levelLabel;
    Label hpLabel;
    Label mpLabel;

    public Hud(SpriteBatch batch) {
        level = 1;
        hp = 10;
        mp = 5;

        viewport = new FitViewport(MainGame.V_WIDTH, MainGame.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, batch);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        levelLabel = new Label(String.format("%02d", level), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        hpLabel = new Label(String.format("%02d", hp), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        mpLabel = new Label(String.format("%02d", mp), new Label.LabelStyle(new BitmapFont(), Color.WHITE));



        table.add(levelLabel).expandX().padTop(10);
        table.add(hpLabel).expandX().padTop(10);
        table.add(mpLabel).expandX().padTop(10);

        stage.addActor(table);

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
