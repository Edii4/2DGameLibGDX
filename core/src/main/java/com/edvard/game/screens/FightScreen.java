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
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.edvard.game.MainGame;
import com.edvard.game.units.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.util.Objects;

public class FightScreen implements Screen {

    private final int MAX_ROW = 10;
    private final int MAX_COL = 10;

    ImageButton startButton;

    boolean isPeasantPlaced = false;
    boolean isArcherPlaced = false;
    boolean isWarriorPlaced = false;
    boolean isGryffPlaced = false;
    boolean isWizardPlaced = false;

    Peasant heroPeasant;
    Label heroPeasantQuantity;
    Peasant enemyPeasant;
    Label enemyPeasantQuantity;

    Archer heroArcher;
    Label heroArcherQuantity;
    Archer enemyArcher;
    Label enemyArcherQuantity;

    Warrior heroWarrior;
    Label heroWarriorQuantity;
    Warrior enemyWarrior;
    Label enemyWarriorQuantity;

    Gryff heroGryff;
    Label heroGryffQuantity;
    Gryff enemyGryff;
    Label enemyGryffQuantity;

    Wizard heroWizard;
    Label heroWizardQuantity;
    Wizard enemyWizard;
    Label enemyWizardQuantity;

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

    public void endTurn() {
        for (int i = 0; i < MAX_ROW; i++) {
            for (int j = 0; j < MAX_COL; j++) {
                if(buttons[i][j].getName() == null){
                    buttons[i][j].setTouchable(Touchable.disabled);
                }
                else {
                    buttons[i][j].setTouchable(Touchable.enabled);
                }
                buttons[i][j].clearListeners();
            }
        }
    }

    public void actionUnit(String name, int row, int col) {
        ImageButton attackButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/attackButton.png")))));
        ImageButton abilityButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/abilityButton.png")))));
        attackButton.setPosition(buttons[row][col].getX() + 64, buttons[row][col].getY() + 32);

        attackButton.addListener(new ClickListener() {
           public void clicked(InputEvent event, float x, float y) {
               attackButton.remove();
               abilityButton.remove();

               System.out.println(attackButton.getListeners());
               for(int i = 0; i < MAX_ROW; i++) {
                   for(int j = 0; j < MAX_COL; j++) {
                       if(buttons[i][j].getName() != null) {
                           int finalI = i;
                           int finalJ = j;
                           buttons[finalI][finalJ].addListener(new ClickListener() {
                               public void clicked(InputEvent event, float x, float y) {
                                   System.out.println("suc. hit");
                                   endTurn();
                               }
                           });
                       }
                   }
               }
           }
        });
        stage.addActor(attackButton);
        abilityButton.setPosition(buttons[row][col].getX() + 64, buttons[row][col].getY());
        stage.addActor(abilityButton);
    }

    public void moveUnit(String name, int row, int col) {
        int moveRange = 0;
        if(name == "heroPeasant") {
            moveRange = heroPeasant.getMoveRange();
        }
        else if(name == "heroArcher") {
            moveRange = heroArcher.getMoveRange();
        }
        else if(name == "heroWarrior") {
            moveRange = heroWarrior.getMoveRange();
        }
        else if(name == "heroGryff") {
            moveRange = heroGryff.getMoveRange();
        }
        else if(name == "heroWizard") {
            moveRange = heroWizard.getMoveRange();
        }

        for(int i = row - moveRange; i <= row + moveRange; i++) {
            for(int j = col - moveRange; j <= col + moveRange; j++) {
                if(i < 0 || j < 0 || i >= MAX_ROW || j >= MAX_COL) {}
                else if(buttons[i][j].getName() != null) {}
                else {
                    buttons[i][j].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/move_field.png"))));
                    buttons[i][j].setTouchable(Touchable.enabled);
                    int finalJ = j;
                    int finalI = i;

                    buttons[i][j].addListener(new ClickListener() {
                        public void clicked(InputEvent event, float x, float y) {
                            buttons[finalI][finalJ].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/" + name + ".png"))));;
                            buttons[finalI][finalJ].setName(name);
                            System.out.println("alap: " + name);
                            System.out.println("gomb: " + buttons[finalI][finalJ].getName());

                            if(name == "heroPeasant") {
                                heroPeasantQuantity.setPosition(buttons[finalI][finalJ].getX() + 32, buttons[finalI][finalJ].getY());
                            }
                            else if(name == "heroArcher") {
                                heroArcherQuantity.setPosition(buttons[finalI][finalJ].getX() + 32, buttons[finalI][finalJ].getY());
                            }
                            else if(name == "heroWarrior") {
                                heroWarriorQuantity.setPosition(buttons[finalI][finalJ].getX() + 32, buttons[finalI][finalJ].getY());
                            }
                            else if(name == "heroGryff") {
                                heroGryffQuantity.setPosition(buttons[finalI][finalJ].getX() + 7, buttons[finalI][finalJ].getY() + 45);
                            }
                            else if(name == "heroWizard") {
                                heroWizardQuantity.setPosition(buttons[finalI][finalJ].getX() + 10, buttons[finalI][finalJ].getY());
                            }

                            buttons[row][col].setName(null);

                            for(int i = 0; i < MAX_ROW; i++) {
                                for(int j = 0; j < MAX_COL; j++) {
                                    if(buttons[i][j].getName() == null) {
                                        buttons[i][j].setName(null);
                                        buttons[i][j].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field.png"))));
                                    }
                                }
                            }
                            endTurn();

                        }
                    });
                }
            }
        }
    }

    public void selectUnit() {
        for (int i = 0; i < MAX_ROW; i++) {
            for (int j = 0; j < MAX_COL; j++) {
                if(buttons[i][j].getName() == "heroPeasant" || buttons[i][j].getName() == "heroArcher" || buttons[i][j].getName() == "heroWarrior" || buttons[i][j].getName() == "heroGryff" | buttons[i][j].getName() == "heroWizard") {
                    int finalI = i;
                    int finalJ = j;
                    buttons[i][j].setTouchable(Touchable.enabled);

                    buttons[i][j].addListener(new ClickListener() {
                        public void clicked(InputEvent event, float x, float y) {
                            moveUnit(buttons[finalI][finalJ].getName(), finalI, finalJ);
                            for(int i = 0; i < MAX_ROW; i++) {
                                for (int j = 0; j < MAX_COL; j++) {
                                    if (buttons[i][j].getName() != null) {
                                        buttons[i][j].setTouchable(Touchable.disabled);
                                    }
                                }
                            }
                        }
                    });

                    buttons[finalI][finalJ].addListener(new ClickListener(Input.Buttons.RIGHT) {
                        public void clicked(InputEvent event, float x, float y) {
                            actionUnit(buttons[finalI][finalJ].getName(), finalI, finalJ);
                            for(int i = 0; i < MAX_ROW; i++) {
                                for(int j = 0; j < MAX_COL; j++) {
                                    if(buttons[i][j].getName() != null) {
                                    }
                                }
                            }
                        }
                    });

                }
            }
        }
    }

    public void placeUnits() {

        int row = (int)(Math.random() * 3);

        buttons[row][8].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/enemyPeasant.png"))));
        enemyPeasantQuantity = new Label(String.valueOf(enemyPeasant.getQuantity()), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        enemyPeasantQuantity.setPosition(buttons[row][8].getX() + 32, buttons[row][8].getY());
        stage.addActor(enemyPeasantQuantity);
        buttons[row][8].setName("enemyPeasant");

        row = (int)(Math.random() * 5);

        buttons[row][9].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/enemyArcher.png"))));
        enemyArcherQuantity = new Label(String.valueOf(enemyArcher.getQuantity()), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        enemyArcherQuantity.setPosition(buttons[row][9].getX() + 32, buttons[row][9].getY());
        stage.addActor(enemyArcherQuantity);
        buttons[row][9].setName("enemyArcher");

        row = (int)(Math.random() * (MAX_ROW - 7) + 7);

        buttons[row][8].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/enemyWarrior.png"))));
        enemyWarriorQuantity = new Label(String.valueOf(enemyWarrior.getQuantity()), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        enemyWarriorQuantity.setPosition(buttons[row][8].getX() + 32, buttons[row][8].getY());
        stage.addActor(enemyWarriorQuantity);
        buttons[row][8].setName("enemyWarrior");

        row = (int)(Math.random() * (MAX_ROW - 5) + 5);

        buttons[row][9].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/enemyWizard.png"))));
        enemyWizardQuantity = new Label(String.valueOf(enemyWizard.getQuantity()), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        enemyWizardQuantity.setPosition(buttons[row][9].getX() + 10, buttons[row][9].getY());
        stage.addActor(enemyWizardQuantity);
        buttons[row][9].setName("enemyWizard");

        row = (int)(Math.random() * (7 - 3) + 3);

        buttons[row][8].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/enemyGryff.png"))));
        enemyGryffQuantity = new Label(String.valueOf(enemyGryff.getQuantity()), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        enemyGryffQuantity.setPosition(buttons[row][8].getX() + 7, buttons[row][8].getY() + 45);
        stage.addActor(enemyGryffQuantity);
        buttons[row][8].setName("enemyGryff");

        for(int i = 0; i < MAX_ROW; i++) {
            for(int j = 0; j < 2; j++) {
                int finalI = i;
                int finalJ = j;
                buttons[i][j].addListener(new ClickListener() {
                    public void clicked(InputEvent event, float x, float y) {
                        if(!isPeasantPlaced) {
                            isPeasantPlaced = true;
                            buttons[finalI][finalJ].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/heroPeasant.png"))));
                            heroPeasantQuantity = new Label(String.valueOf(heroPeasant.getQuantity()), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
                            heroPeasantQuantity.setPosition(buttons[finalI][finalJ].getX() + 32, buttons[finalI][finalJ].getY());
                            stage.addActor(heroPeasantQuantity);
                            buttons[finalI][finalJ].setName("heroPeasant");
                        }
                        else if(!isArcherPlaced && !Objects.equals(buttons[finalI][finalJ].getName(), "heroPeasant")) {
                            isArcherPlaced = true;
                            buttons[finalI][finalJ].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/heroArcher.png"))));
                            heroArcherQuantity = new Label(String.valueOf(heroArcher.getQuantity()), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
                            heroArcherQuantity.setPosition(buttons[finalI][finalJ].getX() + 32, buttons[finalI][finalJ].getY());
                            stage.addActor(heroArcherQuantity);
                            buttons[finalI][finalJ].setName("heroArcher");
                        }
                        else if(!isWarriorPlaced && !Objects.equals(buttons[finalI][finalJ].getName(), "heroPeasant") && !Objects.equals(buttons[finalI][finalJ].getName(), "heroArcher")) {
                            isWarriorPlaced = true;
                            buttons[finalI][finalJ].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/heroWarrior.png"))));
                            heroWarriorQuantity = new Label(String.valueOf(heroWarrior.getQuantity()), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
                            heroWarriorQuantity.setPosition(buttons[finalI][finalJ].getX() + 32, buttons[finalI][finalJ].getY());
                            stage.addActor(heroWarriorQuantity);
                            buttons[finalI][finalJ].setName("heroWarrior");
                        }
                        else if(!isWizardPlaced && !Objects.equals(buttons[finalI][finalJ].getName(), "heroPeasant") && !Objects.equals(buttons[finalI][finalJ].getName(), "heroArcher") && !Objects.equals(buttons[finalI][finalJ].getName(), "heroWarrior")) {
                            isWizardPlaced = true;
                            buttons[finalI][finalJ].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/heroWizard.png"))));
                            heroWizardQuantity = new Label(String.valueOf(heroWizard.getQuantity()), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
                            heroWizardQuantity.setPosition(buttons[finalI][finalJ].getX() + 10, buttons[finalI][finalJ].getY());
                            stage.addActor(heroWizardQuantity);
                            buttons[finalI][finalJ].setName("heroWizard");
                        }
                        else if(!isGryffPlaced && !Objects.equals(buttons[finalI][finalJ].getName(), "heroPeasant") && !Objects.equals(buttons[finalI][finalJ].getName(), "heroArcher") && !Objects.equals(buttons[finalI][finalJ].getName(), "heroWarrior") && !Objects.equals(buttons[finalI][finalJ].getName(), "heroWizard")) {
                            isGryffPlaced = true;
                            buttons[finalI][finalJ].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/heroGryff.png"))));
                            heroGryffQuantity = new Label(String.valueOf(heroGryff.getQuantity()), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
                            heroGryffQuantity.setPosition(buttons[finalI][finalJ].getX() + 7, buttons[finalI][finalJ].getY() + 45);
                            stage.addActor(heroGryffQuantity);
                            buttons[finalI][finalJ].setName("heroGryff");

                            startButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/play01.png")))));
                            startButton.setSize(158, 68.4f);
                            startButton.setPosition(Gdx.graphics.getWidth() / 2 - startButton.getWidth() / 2, Gdx.graphics.getHeight() / 8 - startButton.getHeight() / 2);
                            startButton.addListener(new ClickListener() {
                                public void clicked(InputEvent event, float x, float y) {
                                    System.out.println("started");
                                    selectUnit();
                                }
                            });
                            stage.addActor(startButton);
                        }
                    }
                });
            }
        }
    }

    public void createUnits() {
        heroPeasant = new Peasant(true, 100, 3, 5, 10, 2);
        enemyPeasant = new Peasant(false, 100, 3, 5, 10, 2);
        heroArcher = new Archer(true, 100, 3, 5, 10, 2);
        enemyArcher = new Archer(false, 100, 3, 5, 10, 2);
        heroWarrior = new Warrior(true, 100, 3, 5, 10, 2);
        enemyWarrior  = new Warrior(false, 100, 3, 5, 10, 2);
        heroWizard = new Wizard(true, 100, 3, 5, 10, 1);
        enemyWizard = new Wizard(false, 100, 3, 5, 10, 1);
        heroGryff = new Gryff(true, 100, 3, 5, 10, 3);
        enemyGryff = new Gryff(false, 100, 3, 5, 10, 3);
    }

    public void createField() {
        for(int i = 0; i < MAX_COL; i++) {
            for(int j = 0; j < MAX_ROW; j++) {
                buttons[i][j] = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field.png")))));
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
