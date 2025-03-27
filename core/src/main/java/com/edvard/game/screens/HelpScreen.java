package com.edvard.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.edvard.game.MainGame;

public class HelpScreen implements Screen {

    Label descriptionLabel;

    Label unitLabel;

    Label lineLabel;

    Label peasantLabel;
    Label archerLabel;
    Label warriorLabel;
    Label wizardLabel;
    Label gryffLabel;

    ImageButton peasant;
    ImageButton archer;
    ImageButton warrior;
    ImageButton wizard;
    ImageButton gryff;

    Table table;
    ScrollPane scrollPane;

    Label statLabel;

    private MainGame game;
    Stage stage;

    OrthographicCamera camera;

    public HelpScreen(MainGame game) {
        this.game = game;
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false);
        game.batch = new SpriteBatch();

        stage = new Stage(new ScreenViewport());

        table = new Table();

        scrollPane = new ScrollPane(table);
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollBarPositions(false, true);
        scrollPane.setForceScroll(false, true);
        scrollPane.setBounds(0, 0, 960, 960);
        scrollPane.setScrollY(960);

        lineLabel = new Label("\n\n\n\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n\n\n\n", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        createStatLabel();
        createUnitLabel();
        createFightLabel();

        stage.addActor(scrollPane);
    }

    public void createFightLabel() {
        table.row().colspan(2).expandX();
        Label startFightLabel = new Label("First you have to place your units in the bottom two row\n\nAfter you've placed your units, you can start the fight\n\n\n", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        table.add(startFightLabel).align(Align.center);

        table.row().colspan(2).expandX();
        Label turnLabel = new Label("You can move, attack, or use an ability by right clicking on one of your units\n", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        table.add(turnLabel).align(Align.center);
        table.row().colspan(2).expandX();
        Label turnLabel2 = new Label("After you made an action with a unit, your turn is over\n", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        table.add(turnLabel2).align(Align.center);
        table.row().colspan(2).expandX();
        Label turnLabel3 = new Label("You can use every unit once in a round\n\n\n", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        table.add(turnLabel3).align(Align.center);


        table.row().colspan(2).expandX();
        Label spellLabel = new Label("You can use your spells once in every round, but you can only use one spell in a turn\n\n\n", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        table.add(spellLabel).align(Align.center);

        table.row().colspan(2).expandX();
        Label fightOverLabel = new Label("The fight is over when all unit dies on one side", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        table.add(fightOverLabel).align(Align.center);

        table.row().colspan(2).expandX();
        Label lineLabel3 = new Label("\n\n\n\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n\n\n\n", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        table.add(lineLabel3).align(Align.center);
    }

    public void createUnitLabel() {
        peasant = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/help/peasant.png")))));
        archer = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/help/archer.png")))));
        warrior = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/help/warrior.png")))));
        wizard = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/help/wizard.png")))));
        gryff = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/help/gryff.png")))));

        table.row().expandX();
        table.add(peasant).align(Align.right);
        peasantLabel = new Label("  -  Peasant", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        table.add(peasantLabel).align(Align.left);

        table.row().expandX();
        table.add(archer).align(Align.right);
        archerLabel = new Label("  -  Archer", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        table.add(archerLabel).align(Align.left);

        table.row().expandX();
        table.add(warrior).align(Align.right);
        warriorLabel = new Label("  -  Warrior", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        table.add(warriorLabel).align(Align.left);

        table.row().expandX();
        table.add(wizard).align(Align.right);
        wizardLabel = new Label("  -  Wizard", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        table.add(wizardLabel).align(Align.left);

        table.row().expandX();
        table.add(gryff).align(Align.right);
        gryffLabel = new Label("  -  Gryff", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        table.add(gryffLabel).align(Align.left);

        table.row().colspan(2).expandX();
        unitLabel = new Label("\n\n", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        unitLabel.setText(unitLabel.getText() + "Peasant  -  This unit is rather weak, is melee, has a movement speed of 2, and has no ability\n");
        unitLabel.setText(unitLabel.getText() + "\nArcher  -  This unit deals heavy damage, but dies easily, is ranged, has a movement speed of 2, and can turn invisible for 1 round\n");
        unitLabel.setText(unitLabel.getText() + "\nWarrior  -  This unit has decent damage and defense, is melee, has a movement speed of 2, and can slash the enemy in a 1x3 area\n");
        unitLabel.setText(unitLabel.getText() + "\nWizard  -  This unit is doesn't deal much damage and can die fast, but it can buff ally units, is ranged, and has a movement speed of 1\n");
        unitLabel.setText(unitLabel.getText() + "\nGryff  -  This unit has good damage and defense, is melee, has a movement speed of 3, and can damage every enemy unit in a circle around it\n");
        table.add(unitLabel).align(Align.center);

        table.row().colspan(2).expandX();
        descriptionLabel = new Label("\n\nmelee :  can only attack enemy units when they are next to it\n\nranged : can attack enemy units from everywhere\n\nmovement speed : how many squares can it move", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        table.add(descriptionLabel).align(Align.center);
        table.row().colspan(2).expandX();
        Label lineLabel2 = new Label("\n\n\n\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n\n\n\n", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        table.add(lineLabel2).align(Align.center);

    }

    public void createStatLabel() {
        table.row().colspan(2).expandX();
        Label lineLabel4 = new Label("\n\n\n\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n\n\n\n", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        table.add(lineLabel4).align(Align.center);

        statLabel = new Label("", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        statLabel.setText("Vitality  -  It increases the defense of your units\n");
        statLabel.setText(statLabel.getText() + "\nIntelligence  -  It increases your mana\n");
        statLabel.setText(statLabel.getText() + "\nStrength  -  It increases the damage of your units\n");
        statLabel.setText(statLabel.getText() + "\nMagic Power  -  It increases the power of spells\n");
        statLabel.setText(statLabel.getText() + "\nLuck  -  It increases your chance to land a critical hit");

        table.row().colspan(2).expandX();
        table.add(statLabel).align(Align.center);
        table.row().colspan(2).expandX();
        table.add(lineLabel).align(Align.center);
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
