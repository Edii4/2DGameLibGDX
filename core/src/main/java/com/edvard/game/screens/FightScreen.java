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

    private int round = 1;

    int invisibleRow = -1;
    int invisibleCol = -1;
    boolean wasInvisible = false;
    boolean isEnemyPeasantMoved = false;
    boolean isEnemyArcherMoved = false;
    boolean isEnemyWarriorMoved = false;
    boolean isEnemyGryffMoved = false;
    boolean isEnemyWizardMoved = false;

    boolean isHeroPeasantMoved = false;
    boolean isHeroArcherMoved = false;
    boolean isHeroWarriorMoved = false;
    boolean isHeroGryffMoved = false;
    boolean isHeroWizardMoved = false;

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

    public void enemyTurn() {
        if(enemyGryff.getQuantity() > 0 && !isEnemyGryffMoved) {
            for(int i = 0; i < MAX_ROW; i++) {
                for(int j = 0; j < MAX_COL; j++) {
                    if(buttons[i][j].getName() == "enemyGryff") {
                        for(int k = i - enemyGryff.getMoveRange() -1; k <= i + enemyGryff.getMoveRange() + 1; k++) {
                            for(int l = j - enemyGryff.getMoveRange() - 1; l <= j + enemyGryff.getMoveRange() + 1; l++) {
                                if(k < 0 || l < 0 || k >= MAX_ROW || l >= MAX_COL) {}
                                else if(buttons[k][l].getName() == "heroArcher" || buttons[k][l].getName() == "heroWizard") {
                                    for(int m = k -1; m <= k + 1; m++) {
                                        for(int n = l -1; n <= l + 1; n++) {
                                            if(m < 0 || n < 0 || m >= MAX_ROW || n >= MAX_COL) {}
                                            else if(buttons[m][n].getName() == null && ((m - i) <= enemyGryff.getMoveRange() && m - i >= 0 || (i - m) <= enemyGryff.getMoveRange() && i - m >= 0) && ((n - j) <= enemyGryff.getMoveRange() && n - j >= 0 || (j - n) <= enemyGryff.getMoveRange() && j - n >= 0)){
                                                buttons[m][n].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/enemyGryff.png"))));
                                                buttons[i][j].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field.png"))));
                                                buttons[i][j].setName(null);
                                                buttons[m][n].setName("enemyGryff");
                                                enemyGryffQuantity.setPosition(buttons[m][n].getX() + 7, buttons[m][n].getY() + 45);
                                                isEnemyGryffMoved = true;
                                                startTurn();
                                                return;
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        for(int k = i - enemyGryff.getMoveRange() - 1; k <= i + enemyGryff.getMoveRange() + 1; k++) {
                            for(int l = j - enemyGryff.getMoveRange() -1 ; l <= j + enemyGryff.getMoveRange() + 1; l++) {
                                if(k < 0 || l < 0 || k >= MAX_ROW || l >= MAX_COL) {}
                                else if(buttons[k][l].getName() == "heroPeasant" || buttons[k][l].getName() == "heroWarrior" || buttons[k][l].getName() == "heroGryff") {
                                    for(int m = k -1; m <= k + 1; m++) {
                                        for(int n = l -1; n <= l + 1; n++) {
                                            if(m < 0 || n < 0 || m >= MAX_ROW || n >= MAX_COL) {}
                                            else if(buttons[m][n].getName() == null && ((m - i) <= enemyGryff.getMoveRange() && m - i >= 0 || (i - m) <= enemyGryff.getMoveRange() && i - m >= 0) && ((n - j) <= enemyGryff.getMoveRange() && n - j >= 0 || (j - n) <= enemyGryff.getMoveRange() && j - n >= 0)){
                                                buttons[m][n].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/enemyGryff.png"))));
                                                buttons[i][j].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field.png"))));
                                                buttons[i][j].setName(null);
                                                buttons[m][n].setName("enemyGryff");
                                                enemyGryffQuantity.setPosition(buttons[m][n].getX() + 7, buttons[m][n].getY() + 45);
                                                isEnemyGryffMoved = true;
                                                startTurn();
                                                return;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            while(!isEnemyGryffMoved) {
                int row = (int)(Math.random() * enemyGryff.getMoveRange() + 1);
                int col = (int)(Math.random() * enemyGryff.getMoveRange() + 1);
                int rowPlus = (int)((Math.random() * 2) + 1);
                int colPlus = (int)((Math.random() * 2) + 1);

                for(int i = 0; i < MAX_ROW; i++) {
                    for(int j = 0; j < MAX_COL; j++) {
                        if(buttons[i][j].getName() == "enemyGryff") {
                            if(rowPlus == 1) {
                                if(i - row >= 0) {
                                    if(colPlus == 1) {
                                        if(j - col >= 0) {
                                            buttons[i - row][j - col].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/enemyGryff.png"))));
                                            buttons[i][j].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field.png"))));
                                            buttons[i][j].setName(null);
                                            buttons[i - row][j - col].setName("enemyGryff");
                                            enemyGryffQuantity.setPosition(buttons[i - row][j - col].getX() + 7, buttons[i - row][j - col].getY() + 45);
                                            isEnemyGryffMoved = true;
                                            startTurn();
                                            return;
                                        }
                                    }
                                    else if(colPlus == 2) {
                                        if(j + col < MAX_COL) {
                                            buttons[i - row][j + col].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/enemyGryff.png"))));
                                            buttons[i][j].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field.png"))));
                                            buttons[i][j].setName(null);
                                            buttons[i - row][j + col].setName("enemyGryff");
                                            enemyGryffQuantity.setPosition(buttons[i - row][j + col].getX() + 7, buttons[i - row][j + col].getY() + 45);
                                            isEnemyGryffMoved = true;
                                            startTurn();
                                            return;
                                        }
                                    }
                                }
                            }
                            else if(rowPlus == 2) {
                                if(i + row < MAX_ROW) {
                                    if(colPlus == 1) {
                                        if(j - col >= 0) {
                                            buttons[i + row][j - col].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/enemyGryff.png"))));
                                            buttons[i][j].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field.png"))));
                                            buttons[i][j].setName(null);
                                            buttons[i + row][j - col].setName("enemyGryff");
                                            enemyGryffQuantity.setPosition(buttons[i + row][j - col].getX() + 7, buttons[i + row][j - col].getY() + 45);
                                            isEnemyGryffMoved = true;
                                            startTurn();
                                            return;
                                        }
                                    }
                                    else if(colPlus == 2) {
                                        if(j + col < MAX_COL) {
                                            buttons[i + row][j + col].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/enemyGryff.png"))));
                                            buttons[i][j].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field.png"))));
                                            buttons[i][j].setName(null);
                                            buttons[i + row][j + col].setName("enemyGryff");
                                            enemyGryffQuantity.setPosition(buttons[i + row][j + col].getX() + 7, buttons[i + row][j + col].getY() + 45);
                                            isEnemyGryffMoved = true;
                                            startTurn();
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        else if(enemyWarrior.getQuantity() > 0 && !isEnemyWarriorMoved) {
            for(int i = 0; i < MAX_ROW; i++) {
                for(int j = 0; j < MAX_COL; j++) {
                    if(buttons[i][j].getName() == "enemyWarrior") {
                        for(int k = i - enemyWarrior.getMoveRange() -1; k <= i + enemyWarrior.getMoveRange() + 1; k++) {
                            for(int l = j - enemyWarrior.getMoveRange() - 1; l <= j + enemyWarrior.getMoveRange() + 1; l++) {
                                if(k < 0 || l < 0 || k >= MAX_ROW || l >= MAX_COL) {}
                                else if(buttons[k][l].getName() == "heroArcher" || buttons[k][l].getName() == "heroWizard") {
                                    for(int m = k -1; m <= k + 1; m++) {
                                        for(int n = l -1; n <= l + 1; n++) {
                                            if(m < 0 || n < 0 || m >= MAX_ROW || n >= MAX_COL) {}
                                            else if(buttons[m][n].getName() == null && ((m - i) <= enemyWarrior.getMoveRange() && m - i >= 0 || (i - m) <= enemyWarrior.getMoveRange() && i - m >= 0) && ((n - j) <= enemyWarrior.getMoveRange() && n - j >= 0 || (j - n) <= enemyWarrior.getMoveRange() && j - n >= 0)){
                                                buttons[m][n].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/enemyWarrior.png"))));
                                                buttons[i][j].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field.png"))));
                                                buttons[i][j].setName(null);
                                                buttons[m][n].setName("enemyWarrior");
                                                enemyWarriorQuantity.setPosition(buttons[m][n].getX() + 32, buttons[m][n].getY());
                                                isEnemyWarriorMoved = true;
                                                startTurn();
                                                return;
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        for(int k = i - enemyWarrior.getMoveRange() - 1; k <= i + enemyWarrior.getMoveRange() + 1; k++) {
                            for(int l = j - enemyWarrior.getMoveRange() - 1; l <= j + enemyWarrior.getMoveRange() + 1; l++) {
                                if(k < 0 || l < 0 || k >= MAX_ROW || l >= MAX_COL) {}
                                else if(buttons[k][l].getName() == "heroPeasant" || buttons[k][l].getName() == "heroWarrior" || buttons[k][l].getName() == "heroGryff") {
                                    for(int m = k -1; m <= k + 1; m++) {
                                        for(int n = l -1; n <= l + 1; n++) {
                                            if(m < 0 || n < 0 || m >= MAX_ROW || n >= MAX_COL) {}
                                            else if(buttons[m][n].getName() == null && ((m - i) <= enemyWarrior.getMoveRange() && m - i >= 0 || (i - m) <= enemyWarrior.getMoveRange() && i - m >= 0) && ((n - j) <= enemyWarrior.getMoveRange() && n - j >= 0 || (j - n) <= enemyWarrior.getMoveRange() && j - n >= 0)){
                                                buttons[m][n].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/enemyWarrior.png"))));
                                                buttons[i][j].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field.png"))));
                                                buttons[i][j].setName(null);
                                                buttons[m][n].setName("enemyWarrior");
                                                enemyWarriorQuantity.setPosition(buttons[m][n].getX() + 32, buttons[m][n].getY());
                                                isEnemyWarriorMoved = true;
                                                startTurn();
                                                return;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            while(!isEnemyWarriorMoved) {
                int row = (int)(Math.random() * enemyWarrior.getMoveRange() + 1);
                int col = (int)(Math.random() * enemyWarrior.getMoveRange() + 1);
                int rowPlus = (int)((Math.random() * 2) + 1);
                int colPlus = (int)((Math.random() * 2) + 1);

                for(int i = 0; i < MAX_ROW; i++) {
                    for(int j = 0; j < MAX_COL; j++) {
                        if(buttons[i][j].getName() == "enemyWarrior") {
                            if(rowPlus == 1) {
                                if(i - row >= 0) {
                                    if(colPlus == 1) {
                                        if(j - col >= 0) {
                                            buttons[i - row][j - col].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/enemyWarrior.png"))));
                                            buttons[i][j].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field.png"))));
                                            buttons[i][j].setName(null);
                                            buttons[i - row][j - col].setName("enemyWarrior");
                                            enemyWarriorQuantity.setPosition(buttons[i - row][j - col].getX() + 32, buttons[i - row][j - col].getY());
                                            isEnemyWarriorMoved = true;
                                            startTurn();
                                            return;
                                        }
                                    }
                                    else if(colPlus == 2) {
                                        if(j + col < MAX_COL) {
                                            buttons[i - row][j + col].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/enemyWarrior.png"))));
                                            buttons[i][j].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field.png"))));
                                            buttons[i][j].setName(null);
                                            buttons[i - row][j + col].setName("enemyWarrior");
                                            enemyWarriorQuantity.setPosition(buttons[i - row][j + col].getX() + 32, buttons[i - row][j + col].getY());
                                            isEnemyWarriorMoved = true;
                                            startTurn();
                                            return;
                                        }
                                    }
                                }
                            }
                            else if(rowPlus == 2) {
                                if(i + row < MAX_ROW) {
                                    if(colPlus == 1) {
                                        if(j - col >= 0) {
                                            buttons[i + row][j - col].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/enemyWarrior.png"))));
                                            buttons[i][j].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field.png"))));
                                            buttons[i][j].setName(null);
                                            buttons[i + row][j - col].setName("enemyWarrior");
                                            enemyWarriorQuantity.setPosition(buttons[i + row][j - col].getX() + 32, buttons[i + row][j - col].getY());
                                            isEnemyWarriorMoved = true;
                                            startTurn();
                                            return;
                                        }
                                    }
                                    else if(colPlus == 2) {
                                        if(j + col < MAX_COL) {
                                            buttons[i + row][j + col].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/enemyWarrior.png"))));
                                            buttons[i][j].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field.png"))));
                                            buttons[i][j].setName(null);
                                            buttons[i + row][j + col].setName("enemyWarrior");
                                            enemyWarriorQuantity.setPosition(buttons[i + row][j + col].getX() + 32, buttons[i + row][j + col].getY());
                                            isEnemyWarriorMoved = true;
                                            startTurn();
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }
        else if(enemyWizard.getQuantity() > 0 && !isEnemyWizardMoved) {
            boolean isEnemySurround = false;

            for(int i = 0; i < MAX_ROW; i++) {
                for(int j = 0; j < MAX_COL; j++) {
                    if(buttons[i][j].getName() == "enemyWizard") {
                        for(int k = i - 1; k <= i + 1; k++) {
                            for(int l = j - 1; l <= j + 1; l++) {
                                if(k < 0 || k >= MAX_ROW || l < 0 || l >= MAX_COL) {}
                                else if(buttons[k][l].getName() == "heroPeasant" || buttons[k][l].getName() == "heroArcher" || buttons[k][l].getName() == "heroWarrior" || buttons[k][l].getName() == "heroWizard" || buttons[k][l].getName() == "heroGryff") {
                                    isEnemySurround = true;
                                }
                            }
                        }

                        if(isEnemySurround) {
                            for(int k = i - enemyWizard.getMoveRange(); k <= i + enemyWizard.getMoveRange(); k++) {
                                for(int l = j - enemyWizard.getMoveRange(); l <= j + enemyWizard.getMoveRange(); l++) {
                                    isEnemySurround = false;
                                    if(k < 0 || k >= MAX_ROW || l < 0 || l >= MAX_COL) {}
                                    else {
                                        for(int m = k - 1; m <= k + 1; m++) {
                                            for(int n = l - 1; n <= l + 1; n++) {
                                                if(m < 0 || m >= MAX_ROW || n < 0 || n >= MAX_COL) {}
                                                else if(buttons[m][n].getName() == "heroPeasant" || buttons[m][n].getName() == "heroArcher" || buttons[m][n].getName() == "heroWarrior" || buttons[m][n].getName() == "heroWizard" || buttons[m][n].getName() == "heroGryff") {
                                                    isEnemySurround = true;
                                                }
                                            }
                                        }
                                        if(!isEnemySurround && buttons[k][l].getName() == null) {
                                            buttons[k][l].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/enemyWizard.png"))));
                                            buttons[i][j].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field.png"))));
                                            buttons[i][j].setName(null);
                                            buttons[k][l].setName("enemyWizard");
                                            enemyWizardQuantity.setPosition(buttons[k][l].getX() + 10, buttons[k][l].getY());
                                            isEnemyWizardMoved = true;
                                            startTurn();
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            isEnemyWizardMoved = true;
            startTurn();

        }
        else if(enemyArcher.getQuantity() > 0 && !isEnemyArcherMoved) {
            boolean isEnemySurround = false;

            for(int i = 0; i < MAX_ROW; i++) {
                for(int j = 0; j < MAX_COL; j++) {
                    if(buttons[i][j].getName() == "enemyArcher") {
                        for(int k = i - 1; k <= i + 1; k++) {
                            for(int l = j - 1; l <= j + 1; l++) {
                                if(k < 0 || k >= MAX_ROW || l < 0 || l >= MAX_COL) {}
                                else if(buttons[k][l].getName() == "heroPeasant" || buttons[k][l].getName() == "heroArcher" || buttons[k][l].getName() == "heroWarrior" || buttons[k][l].getName() == "heroWizard" || buttons[k][l].getName() == "heroGryff") {
                                    isEnemySurround = true;
                                }
                            }
                        }

                        if(isEnemySurround) {
                            for(int k = i - enemyArcher.getMoveRange(); k <= i + enemyArcher.getMoveRange(); k++) {
                                for(int l = j - enemyArcher.getMoveRange(); l <= j + enemyArcher.getMoveRange(); l++) {
                                    isEnemySurround = false;
                                    if(k < 0 || k >= MAX_ROW || l < 0 || l >= MAX_COL) {}
                                    else {
                                        for(int m = k - 1; m <= k + 1; m++) {
                                            for(int n = l - 1; n <= l + 1; n++) {
                                                if(m < 0 || m >= MAX_ROW || n < 0 || n >= MAX_COL) {}
                                                else if(buttons[m][n].getName() == "heroPeasant" || buttons[m][n].getName() == "heroArcher" || buttons[m][n].getName() == "heroWarrior" || buttons[m][n].getName() == "heroWizard" || buttons[m][n].getName() == "heroGryff") {
                                                    isEnemySurround = true;
                                                }
                                            }
                                        }
                                        if(!isEnemySurround && buttons[k][l].getName() == null) {
                                            buttons[k][l].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/enemyArcher.png"))));
                                            buttons[i][j].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field.png"))));
                                            buttons[i][j].setName(null);
                                            buttons[k][l].setName("enemyArcher");
                                            enemyArcherQuantity.setPosition(buttons[k][l].getX() + 32, buttons[k][l].getY());
                                            isEnemyArcherMoved = true;
                                            startTurn();
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            isEnemyArcherMoved = true;
            startTurn();

        }
        else if(enemyPeasant.getQuantity() > 0 && !isEnemyPeasantMoved) {
            for(int i = 0; i < MAX_ROW; i++) {
                for(int j = 0; j < MAX_COL; j++) {
                    if(buttons[i][j].getName() == "enemyPeasant") {
                        for(int k = i - enemyPeasant.getMoveRange() - 1; k <= i + enemyPeasant.getMoveRange() + 1; k++) {
                            for(int l = j - enemyPeasant.getMoveRange() - 1; l <= j + enemyPeasant.getMoveRange() + 1; l++) {
                                if(k < 0 || l < 0 || k >= MAX_ROW || l >= MAX_COL) {}
                                else if(buttons[k][l].getName() == "heroArcher" || buttons[k][l].getName() == "heroWizard") {
                                    for(int m = k -1; m <= k + 1; m++) {
                                        for(int n = l -1; n <= l + 1; n++) {
                                            if(m < 0 || n < 0 || m >= MAX_ROW || n >= MAX_COL) {}
                                            else if(buttons[m][n].getName() == null && ((m - i) <= enemyPeasant.getMoveRange() && m - i >= 0 || (i - m) <= enemyPeasant.getMoveRange() && i - m >= 0) && ((n - j) <= enemyPeasant.getMoveRange() && n - j >= 0 || (j - n) <= enemyPeasant.getMoveRange() && j - n >= 0)){
                                                buttons[m][n].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/enemyPeasant.png"))));
                                                buttons[i][j].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field.png"))));
                                                buttons[i][j].setName(null);
                                                buttons[m][n].setName("enemyPeasant");
                                                enemyPeasantQuantity.setPosition(buttons[m][n].getX() + 32, buttons[m][n].getY());
                                                isEnemyPeasantMoved = true;
                                                startTurn();
                                                return;
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        for(int k = i - enemyPeasant.getMoveRange() - 1; k <= i + enemyPeasant.getMoveRange() + 1; k++) {
                            for(int l = j - enemyPeasant.getMoveRange() - 1; l <= j + enemyPeasant.getMoveRange() + 1; l++) {
                                if(k < 0 || l < 0 || k >= MAX_ROW || l >= MAX_COL) {}
                                else if(buttons[k][l].getName() == "heroPeasant" || buttons[k][l].getName() == "heroWarrior" || buttons[k][l].getName() == "heroGryff") {
                                    for(int m = k -1; m <= k + 1; m++) {
                                        for(int n = l -1; n <= l + 1; n++) {
                                            if(m < 0 || n < 0 || m >= MAX_ROW || n >= MAX_COL) {}
                                            else if(buttons[m][n].getName() == null && ((m - i) <= enemyPeasant.getMoveRange() && m - i >= 0 || (i - m) <= enemyPeasant.getMoveRange() && i - m >= 0) && ((n - j) <= enemyPeasant.getMoveRange() && n - j >= 0 || (j - n) <= enemyPeasant.getMoveRange() && j - n >= 0)){
                                                buttons[m][n].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/enemyPeasant.png"))));
                                                buttons[i][j].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field.png"))));
                                                buttons[i][j].setName(null);
                                                buttons[m][n].setName("enemyPeasant");
                                                enemyPeasantQuantity.setPosition(buttons[m][n].getX() + 32, buttons[m][n].getY());
                                                isEnemyPeasantMoved = true;
                                                startTurn();
                                                return;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            while(!isEnemyPeasantMoved) {
                int row = (int)(Math.random() * enemyPeasant.getMoveRange() + 1);
                int col = (int)(Math.random() * enemyPeasant.getMoveRange() + 1);
                int rowPlus = (int)((Math.random() * 2) + 1);
                int colPlus = (int)((Math.random() * 2) + 1);

                for(int i = 0; i < MAX_ROW; i++) {
                    for(int j = 0; j < MAX_COL; j++) {
                        if(buttons[i][j].getName() == "enemyPeasant") {
                            if(rowPlus == 1) {
                                if(i - row >= 0) {
                                    if(colPlus == 1) {
                                        if(j - col >= 0) {
                                            buttons[i - row][j - col].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/enemyPeasant.png"))));
                                            buttons[i][j].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field.png"))));
                                            buttons[i][j].setName(null);
                                            buttons[i - row][j - col].setName("enemyPeasant");
                                            enemyPeasantQuantity.setPosition(buttons[i - row][j - col].getX() + 32, buttons[i - row][j - col].getY());
                                            isEnemyPeasantMoved = true;
                                            startTurn();
                                            return;
                                        }
                                    }
                                    else if(colPlus == 2) {
                                        if(j + col < MAX_COL) {
                                            buttons[i - row][j + col].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/enemyPeasant.png"))));
                                            buttons[i][j].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field.png"))));
                                            buttons[i][j].setName(null);
                                            buttons[i - row][j + col].setName("enemyPeasant");
                                            enemyPeasantQuantity.setPosition(buttons[i - row][j + col].getX() + 32, buttons[i - row][j + col].getY());
                                            isEnemyPeasantMoved = true;
                                            startTurn();
                                            return;
                                        }
                                    }
                                }
                            }
                            else if(rowPlus == 2) {
                                if(i + row < MAX_ROW) {
                                    if(colPlus == 1) {
                                        if(j - col >= 0) {
                                            buttons[i + row][j - col].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/enemyPeasant.png"))));
                                            buttons[i][j].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field.png"))));
                                            buttons[i][j].setName(null);
                                            buttons[i + row][j - col].setName("enemyPeasant");
                                            enemyPeasantQuantity.setPosition(buttons[i + row][j - col].getX() + 32, buttons[i + row][j - col].getY());
                                            isEnemyPeasantMoved = true;
                                            startTurn();
                                            return;
                                        }
                                    }
                                    else if(colPlus == 2) {
                                        if(j + col < MAX_COL) {
                                            buttons[i + row][j + col].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/enemyPeasant.png"))));
                                            buttons[i][j].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field.png"))));
                                            buttons[i][j].setName(null);
                                            buttons[i + row][j + col].setName("enemyPeasant");
                                            enemyPeasantQuantity.setPosition(buttons[i + row][j + col].getX() + 32, buttons[i + row][j + col].getY());
                                            isEnemyPeasantMoved = true;
                                            startTurn();
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }
        else {
            System.out.println("nyertél");
            startTurn();
        }
    }

    public void startTurn() {
        if(isEnemyPeasantMoved && isEnemyArcherMoved && isEnemyWarriorMoved && isEnemyWizardMoved && isEnemyGryffMoved) {
            round++;
            System.out.println("round: " + round);
            if(wasInvisible && invisibleRow != -1 && invisibleCol != -1) {
                buttons[invisibleRow][invisibleCol].setName("heroArcher");
                buttons[invisibleRow][invisibleCol].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/heroArcher.png"))));
                invisibleRow = -1;
                invisibleCol = -1;
            }
        }
        selectUnit();
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

        if(isHeroPeasantMoved && isHeroArcherMoved && isHeroWarriorMoved && isHeroWizardMoved && isHeroGryffMoved) {
            isHeroPeasantMoved = false;
            isHeroArcherMoved = false;
            isHeroWarriorMoved = false;
            isHeroWizardMoved = false;
            isHeroGryffMoved = false;
        }
        if(isEnemyPeasantMoved && isEnemyArcherMoved && isEnemyWarriorMoved && isEnemyWizardMoved && isEnemyGryffMoved) {
            isEnemyPeasantMoved = false;
            isEnemyArcherMoved = false;
            isEnemyWarriorMoved = false;
            isEnemyWizardMoved = false;
            isEnemyGryffMoved = false;
        }

        enemyTurn();
    }

    public void actionUnit(String name, int row, int col) {
        ImageButton attackButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/attackButton.png")))));
        ImageButton abilityButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/abilityButton.png")))));
        ImageButton moveButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/moveButton.png")))));
        attackButton.setPosition(buttons[row][col].getX() + 64, buttons[row][col].getY() + 32);

        boolean canAttack = false;
        if(name == "heroPeasant" || name == "heroWarrior" || name == "heroGryff") {
            for(int i = row-1; i <= row+1; i++) {
                for(int j = col-1; j <= col+1; j++) {
                    if(i >= 0 && i < MAX_ROW && j >= 0 && j < MAX_COL)  {
                        //System.out.println(buttons[i][j].getName());
                        if(buttons[i][j].getName() == "enemyPeasant" || buttons[i][j].getName() == "enemyArcher" || buttons[i][j].getName() == "enemyWizard" || buttons[i][j].getName() == "enemyWarrior" || buttons[i][j].getName() == "enemyGryff") {
                            canAttack = true;
                        }
                    }
                }
            }
        }
        else {
            canAttack = true;
        }
        System.out.println(canAttack);

        if(!canAttack) {
            attackButton.setTouchable(Touchable.disabled);
            attackButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/unavailableAttackButton.png"))));
            //TODO: this doesnt work, fix
            for(int i = 0; i < MAX_ROW; i++) {
                for(int j = 0; j < MAX_COL; j++) {
                    if(buttons[i][j].getName() == "heroPeasant" || buttons[i][j].getName() == "heroArcher" || buttons[i][j].getName() == "heroWarrior" || buttons[i][j].getName() == "heroGryff" || buttons[i][j].getName() == "heroWizard") {
                        buttons[i][j].setTouchable(Touchable.enabled);
                    }
                }
            }
            startTurn();
        }

        attackButton.addListener(new ClickListener() {
           public void clicked(InputEvent event, float x, float y) {
               float dmg = 0;

               if(name == "heroPeasant") {
                   dmg = heroPeasant.getDamage() * heroPeasant.getQuantity();
                   isHeroPeasantMoved = true;
               }
               else if(name == "heroArcher") {
                   dmg = heroArcher.getDamage() * heroArcher.getQuantity();
                   isHeroArcherMoved = true;
               }
               else if(name == "heroWarrior") {
                   dmg = heroWarrior.getDamage() * heroWarrior.getQuantity();
                   isHeroWarriorMoved = true;
               }
               else if(name == "heroWizard") {
                   dmg = heroWizard.getDamage() * heroWizard.getQuantity();
                   isHeroWizardMoved = true;
               }
               else if(name == "heroGryff") {
                   dmg = heroGryff.getDamage() * heroGryff.getQuantity();
                   isHeroGryffMoved = true;
               }

               attackButton.remove();
               abilityButton.remove();
               moveButton.remove();

               //System.out.println(attackButton.getListeners());
               for(int i = 0; i < MAX_ROW; i++) {
                   for(int j = 0; j < MAX_COL; j++) {
                       if(buttons[i][j].getName() != null) {
                           int finalI = i;
                           int finalJ = j;
                           float finalDmg = dmg;
                           buttons[finalI][finalJ].addListener(new ClickListener() {
                               public void clicked(InputEvent event, float x, float y) {
                                   float dmg = 0;
                                   int count = 0;
                                   if(buttons[finalI][finalJ].getName() == "enemyPeasant") {
                                       dmg = finalDmg - finalDmg * enemyPeasant.getDefense() / 20;
                                       for(int i = 0; i < dmg; i = i + enemyPeasant.getHp()) {
                                           count++;
                                           System.out.println(count);
                                       }
                                       enemyPeasant.setQuantity(enemyPeasant.getQuantity() - count);
                                       enemyPeasantQuantity.setText(enemyPeasant.getQuantity());
                                       //TODO: CREATE HP*QUANTITY (MAX HP)
                                   }
                                   else if(buttons[finalI][finalJ].getName() == "enemyArcher") {
                                       dmg = finalDmg - finalDmg * enemyArcher.getDefense() / 20;
                                       for(int i = 0; i < dmg; i = i + enemyArcher.getHp()) {
                                           count++;
                                           System.out.println(count);
                                       }
                                       enemyArcher.setQuantity(enemyArcher.getQuantity() - count);
                                       enemyArcherQuantity.setText(enemyArcher.getQuantity());
                                       //TODO: CREATE HP*QUANTITY (MAX HP)
                                   }
                                   else if(buttons[finalI][finalJ].getName() == "enemyWarrior") {
                                       dmg = finalDmg - finalDmg * enemyWarrior.getDefense() / 20;
                                       for(int i = 0; i < dmg; i = i + enemyWarrior.getHp()) {
                                           count++;
                                           System.out.println(count);
                                       }
                                       enemyWarrior.setQuantity(enemyWarrior.getQuantity() - count);
                                       enemyWarriorQuantity.setText(enemyWarrior.getQuantity());
                                       //TODO: CREATE HP*QUANTITY (MAX HP)
                                   }
                                   else if(buttons[finalI][finalJ].getName() == "enemyWizard") {
                                       dmg = finalDmg - finalDmg * enemyWizard.getDefense() / 20;
                                       for(int i = 0; i < dmg; i = i + enemyWizard.getHp()) {
                                           count++;
                                           System.out.println(count);
                                       }
                                       enemyWizard.setQuantity(enemyWizard.getQuantity() - count);
                                       enemyWizardQuantity.setText(enemyWizard.getQuantity());
                                       //TODO: CREATE HP*QUANTITY (MAX HP)
                                   }
                                   else if(buttons[finalI][finalJ].getName() == "enemyGryff") {
                                       dmg = finalDmg - finalDmg * enemyGryff.getDefense() / 20;
                                       for(int i = 0; i < dmg; i = i + enemyGryff.getHp()) {
                                           count++;
                                           System.out.println(count);
                                       }
                                       enemyGryff.setQuantity(enemyGryff.getQuantity() - count);
                                       enemyGryffQuantity.setText(enemyGryff.getQuantity());
                                       //TODO: CREATE HP*QUANTITY (MAX HP)
                                   }

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
        if((wasInvisible && name == "heroArcher") || name == "heroPeasant") {
            abilityButton.setTouchable(Touchable.disabled);
            abilityButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/unavailableAbilityButton.png"))));
        }
        abilityButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                float dmg = 0;

                attackButton.remove();
                abilityButton.remove();
                moveButton.remove();

                if(name == "heroPeasant") {
                    isHeroPeasantMoved = true;
                }
                else if(name == "heroArcher") {
                    isHeroArcherMoved = true;

                    buttons[row][col].setName("invisibleHeroArcher");
                    buttons[row][col].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/invisibleHeroArcher.png"))));
                    wasInvisible = true;
                    invisibleRow = row;
                    invisibleCol = col;

                    endTurn();
                }
                else if(name == "heroWarrior") {
                    isHeroWarriorMoved = true;

                    dmg = heroWarrior.getDamage() * heroWarrior.getQuantity() * 2;

                    for(int j = col+1; j <= col+3; j++) {
                        System.out.println(j + "," + row);
                        if(j >= 0 && j < MAX_COL) {
                            if(buttons[j][row].getName() == "enemyPeasant") {
                                int count = 0;
                                dmg -= dmg * enemyPeasant.getDefense() / 20;
                                for(int k = 0; k < dmg; k = k + enemyPeasant.getHp()) {
                                    count++;
                                    System.out.println(count);
                                }
                                enemyPeasant.setQuantity(enemyPeasant.getQuantity() - count);
                                enemyPeasantQuantity.setText(enemyPeasant.getQuantity());
                                //TODO: CREATE HP*QUANTITY (MAX HP)
                            }
                            else if(buttons[j][row].getName() == "enemyArcher") {
                                int count = 0;
                                dmg -= dmg * enemyArcher.getDefense() / 20;
                                for(int k = 0; k < dmg; k = k + enemyArcher.getHp()) {
                                    count++;
                                    System.out.println(count);
                                }
                                enemyArcher.setQuantity(enemyArcher.getQuantity() - count);
                                enemyArcherQuantity.setText(enemyArcher.getQuantity());
                                //TODO: CREATE HP*QUANTITY (MAX HP)
                            }
                            else if(buttons[j][row].getName() == "enemyWarrior") {
                                int count = 0;
                                dmg -= dmg * enemyWarrior.getDefense() / 20;
                                for(int k = 0; k < dmg; k = k + enemyWarrior.getHp()) {
                                    count++;
                                    System.out.println(count);
                                }
                                enemyWarrior.setQuantity(enemyWarrior.getQuantity() - count);
                                enemyWarriorQuantity.setText(enemyWarrior.getQuantity());
                                //TODO: CREATE HP*QUANTITY (MAX HP)
                            }
                            else if(buttons[j][row].getName() == "enemyWizard") {
                                int count = 0;
                                dmg -= dmg * enemyWizard.getDefense() / 20;
                                for(int k = 0; k < dmg; k = k + enemyWizard.getHp()) {
                                    count++;
                                    System.out.println(count);
                                }
                                enemyWizard.setQuantity(enemyWizard.getQuantity() - count);
                                enemyWizardQuantity.setText(enemyWizard.getQuantity());
                                //TODO: CREATE HP*QUANTITY (MAX HP)
                            }
                            else if(buttons[j][row].getName() == "enemyGryff") {
                                int count = 0;
                                dmg -= dmg * enemyGryff.getDefense() / 20;
                                for(int k = 0; k < dmg; k = k + enemyGryff.getHp()) {
                                    count++;
                                    System.out.println(count);
                                }
                                enemyGryff.setQuantity(enemyGryff.getQuantity() - count);
                                enemyGryffQuantity.setText(enemyGryff.getQuantity());
                                //TODO: CREATE HP*QUANTITY (MAX HP)
                            }
                        }
                    }
                    endTurn();
                }
                else if(name == "heroWizard") {
                    isHeroWizardMoved = true;
                    System.out.println("wizard ability");

                    for(int i = 0; i < MAX_ROW; i++) {
                        for(int j = 0; j < MAX_COL; j++) {
                            if(buttons[i][j].getName() == "heroPeasant" || buttons[i][j].getName() == "heroArcher" || buttons[i][j].getName() == "heroWarrior" || buttons[i][j].getName() == "heroGryff") {
                                buttons[i][j].setTouchable(Touchable.enabled);
                                int finalI = i;
                                int finalJ = j;
                                float dmgBuff = (float) (heroWizard.getDamage() * heroWizard.getQuantity()) / 10;
                                float defBuff = (float) (heroWizard.getDamage() * heroWizard.getQuantity()) / 20;
                                System.out.println("button is ready");
                                buttons[finalI][finalJ].addListener(new ClickListener(Input.Buttons.RIGHT) {
                                    public void clicked(InputEvent event, float x, float y) {
                                        if(buttons[finalI][finalJ].getName() == "heroPeasant") {
                                            heroPeasant.setDamage((int) (heroPeasant.getDamage() + dmgBuff));
                                            heroPeasant.setDefense((int) (heroPeasant.getDefense() + defBuff));
                                            System.out.println("peasant buffed");
                                        }
                                        else if(buttons[finalI][finalJ].getName() == "heroArcher") {
                                            heroArcher.setDamage((int) (heroArcher.getDamage() + dmgBuff));
                                            heroArcher.setDefense((int) (heroArcher.getDefense() + defBuff));
                                        }
                                        else if(buttons[finalI][finalJ].getName() == "heroWarrior") {
                                            heroWarrior.setDamage((int) (heroWarrior.getDamage() + dmgBuff));
                                            heroWarrior.setDefense((int) (heroWarrior.getDefense() + defBuff));
                                        }
                                        else if(buttons[finalI][finalJ].getName() == "heroGryff") {
                                            heroGryff.setDamage((int) (heroGryff.getDamage() + dmgBuff));
                                            heroGryff.setDefense((int) (heroGryff.getDefense() + defBuff));
                                        }
                                        endTurn();
                                    }
                                });
                            }
                        }
                    }

                }
                else if(name == "heroGryff") {
                    dmg = (float) (heroGryff.getDamage() * heroGryff.getQuantity()) / 2;
                    isHeroGryffMoved = true;

                    for(int i = row-1; i <= row+1; i ++) {
                        for(int j = col-1; j <= col+1; j ++) {
                            if(i >= 0 && i < MAX_ROW && j >= 0 && j < MAX_COL) {
                                if(buttons[i][j].getName() == "enemyPeasant") {
                                    int count = 0;
                                    dmg -= dmg * enemyPeasant.getDefense() / 20;
                                    for(int k = 0; k < dmg; k = k + enemyPeasant.getHp()) {
                                        count++;
                                        System.out.println(count);
                                    }
                                    enemyPeasant.setQuantity(enemyPeasant.getQuantity() - count);
                                    enemyPeasantQuantity.setText(enemyPeasant.getQuantity());
                                    //TODO: CREATE HP*QUANTITY (MAX HP)
                                }
                                else if(buttons[i][j].getName() == "enemyArcher") {
                                    int count = 0;
                                    dmg -= dmg * enemyArcher.getDefense() / 20;
                                    for(int k = 0; k < dmg; k = k + enemyArcher.getHp()) {
                                        count++;
                                        System.out.println(count);
                                    }
                                    enemyArcher.setQuantity(enemyArcher.getQuantity() - count);
                                    enemyArcherQuantity.setText(enemyArcher.getQuantity());
                                    //TODO: CREATE HP*QUANTITY (MAX HP)
                                }
                                else if(buttons[i][j].getName() == "enemyWarrior") {
                                    int count = 0;
                                    dmg -= dmg * enemyWarrior.getDefense() / 20;
                                    for(int k = 0; k < dmg; k = k + enemyWarrior.getHp()) {
                                        count++;
                                        System.out.println(count);
                                    }
                                    enemyWarrior.setQuantity(enemyWarrior.getQuantity() - count);
                                    enemyWarriorQuantity.setText(enemyWarrior.getQuantity());
                                    //TODO: CREATE HP*QUANTITY (MAX HP)
                                }
                                else if(buttons[i][j].getName() == "enemyWizard") {
                                    int count = 0;
                                    dmg -= dmg * enemyWizard.getDefense() / 20;
                                    for(int k = 0; k < dmg; k = k + enemyWizard.getHp()) {
                                        count++;
                                        System.out.println(count);
                                    }
                                    enemyWizard.setQuantity(enemyWizard.getQuantity() - count);
                                    enemyWizardQuantity.setText(enemyWizard.getQuantity());
                                    //TODO: CREATE HP*QUANTITY (MAX HP)
                                }
                                else if(buttons[i][j].getName() == "enemyGryff") {
                                    int count = 0;
                                    dmg -= dmg * enemyGryff.getDefense() / 20;
                                    for(int k = 0; k < dmg; k = k + enemyGryff.getHp()) {
                                        count++;
                                        System.out.println(count);
                                    }
                                    enemyGryff.setQuantity(enemyGryff.getQuantity() - count);
                                    enemyGryffQuantity.setText(enemyGryff.getQuantity());
                                    //TODO: CREATE HP*QUANTITY (MAX HP)
                                }
                            }
                        }
                    }
                    endTurn();
                }
            }
        });
        stage.addActor(abilityButton);

        moveButton.setPosition(buttons[row][col].getX() + 64, buttons[row][col].getY() - 32);
        moveButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                attackButton.remove();
                abilityButton.remove();
                moveButton.remove();
                moveUnit(name, row, col);
            }
        });
        stage.addActor(moveButton);
    }

    public void moveUnit(String name, int row, int col) {
        System.out.println("moving started");
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
                                isHeroPeasantMoved = true;
                            }
                            else if(name == "heroArcher") {
                                heroArcherQuantity.setPosition(buttons[finalI][finalJ].getX() + 32, buttons[finalI][finalJ].getY());
                                isHeroArcherMoved = true;
                            }
                            else if(name == "heroWarrior") {
                                heroWarriorQuantity.setPosition(buttons[finalI][finalJ].getX() + 32, buttons[finalI][finalJ].getY());
                                isHeroWarriorMoved = true;
                            }
                            else if(name == "heroGryff") {
                                heroGryffQuantity.setPosition(buttons[finalI][finalJ].getX() + 7, buttons[finalI][finalJ].getY() + 45);
                                isHeroGryffMoved = true;
                            }
                            else if(name == "heroWizard") {
                                heroWizardQuantity.setPosition(buttons[finalI][finalJ].getX() + 10, buttons[finalI][finalJ].getY());
                                isHeroWizardMoved = true;
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
                if((buttons[i][j].getName() == "heroPeasant" && !isHeroPeasantMoved) || (buttons[i][j].getName() == "heroArcher" && !isHeroArcherMoved) || (buttons[i][j].getName() == "heroWarrior" && !isHeroWarriorMoved) || (buttons[i][j].getName() == "heroGryff" && !isHeroGryffMoved) || (buttons[i][j].getName() == "heroWizard" && !isHeroWizardMoved)) {
                    int finalI = i;
                    int finalJ = j;
                    buttons[i][j].setTouchable(Touchable.enabled);

                    //move with left mouse button, i dont need this for now
                   /* buttons[i][j].addListener(new ClickListener() {
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
                    }); */

                    buttons[finalI][finalJ].addListener(new ClickListener(Input.Buttons.RIGHT) {
                        public void clicked(InputEvent event, float x, float y) {
                            actionUnit(buttons[finalI][finalJ].getName(), finalI, finalJ);
                            for(int i = 0; i < MAX_ROW; i++) {
                                for(int j = 0; j < MAX_COL; j++) {
                                    if(buttons[i][j].getName() == "heroPeasant" || buttons[i][j].getName() == "heroArcher" || buttons[i][j].getName() == "heroWarrior" || buttons[i][j].getName() == "heroGryff" || buttons[i][j].getName() == "heroWizard") {
                                        buttons[i][j].setTouchable(Touchable.disabled);
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
