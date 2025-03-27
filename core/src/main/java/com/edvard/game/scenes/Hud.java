package com.edvard.game.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
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

    Label moveLabel;
    Label openShopLabel;
    Label openHelpLabel;
    Label openFightLabel;

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

        //levelLabel = new Label(String.format("%02d", level), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //hpLabel = new Label(String.format("%02d", hp), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //mpLabel = new Label(String.format("%02d", mp), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.row().expandX();
        moveLabel = new Label("W, A, S, D  -  Move", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        moveLabel.setFontScale(0.3f);
        table.add(moveLabel).align(Align.left).padLeft(5).padTop(5);

        table.row().expandX();
        openFightLabel = new Label("SPACE  -  Start  battle", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        openFightLabel.setFontScale(0.3f);
        table.add(openFightLabel).align(Align.left).padLeft(5);

        table.row().expandX();
        openShopLabel = new Label("C  -  Open  shop", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        openShopLabel.setFontScale(0.3f);
        table.add(openShopLabel).align(Align.left).padLeft(5);

        table.row().expandX();
        openHelpLabel = new Label("H  -  Open  help", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        openHelpLabel.setFontScale(0.3f);
        table.add(openHelpLabel).align(Align.left).padLeft(5);

        //table.add(levelLabel).expandX().padTop(10);
        //table.add(hpLabel).expandX().padTop(10);
        //table.add(mpLabel).expandX().padTop(10);

        stage.addActor(table);

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
