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
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.edvard.game.MainGame;
import com.edvard.game.units.*;

import java.util.Objects;

public class FightScreen implements Screen {

    private final int MAX_ROW = 10;
    private final int MAX_COL = 10;

    ImageButton startButton;

    TextArea gameFlow;
    ScrollPane scrollPane;
    private int round = 1;

    int invisibleRow = -1;
    int invisibleCol = -1;

    boolean wasEnemyInvisible = false;
    int invisibleEnemyRow = -2;
    int invisibleEnemyCol = -2;
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

    public void isUnitDead(int row, int col) {
        if(buttons[row][col].getName() == "heroPeasant" && heroPeasant.getQuantity() <= 0) {
            isHeroPeasantMoved = true;
            heroPeasantQuantity.setVisible(false);
            buttons[row][col].setName(null);
            buttons[row][col].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field.png"))));
        }
        if(buttons[row][col].getName() == "heroArcher" && heroArcher.getQuantity() <= 0) {
            isHeroArcherMoved = true;
            heroArcherQuantity.setVisible(false);
            buttons[row][col].setName(null);
            buttons[row][col].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field.png"))));
        }
        if(buttons[row][col].getName() == "heroWarrior" && heroWarrior.getQuantity() <= 0) {
            isHeroWarriorMoved = true;
            heroWarriorQuantity.setVisible(false);
            buttons[row][col].setName(null);
            buttons[row][col].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field.png"))));
        }
        if(buttons[row][col].getName() == "heroWizard" && heroWizard.getQuantity() <= 0) {
            isHeroWizardMoved = true;
            heroWizardQuantity.setVisible(false);
            buttons[row][col].setName(null);
            buttons[row][col].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field.png"))));
        }
        if(buttons[row][col].getName() == "heroGryff" && heroGryff.getQuantity() <= 0) {
            isHeroGryffMoved = true;
            heroGryffQuantity.setVisible(false);
            buttons[row][col].setName(null);
            buttons[row][col].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field.png"))));
        }

        if(buttons[row][col].getName() == "enemyPeasant" && enemyPeasant.getQuantity() <= 0) {
            isEnemyPeasantMoved = true;
            enemyPeasantQuantity.setVisible(false);
            buttons[row][col].setName(null);
            buttons[row][col].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field.png"))));
        }
        if(buttons[row][col].getName() == "enemyArcher" && enemyArcher.getQuantity() <= 0) {
            isEnemyArcherMoved = true;
            enemyArcherQuantity.setVisible(false);
            buttons[row][col].setName(null);
            buttons[row][col].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field.png"))));
        }
        if(buttons[row][col].getName() == "enemyWarrior" && enemyWarrior.getQuantity() <= 0) {
            isEnemyWarriorMoved = true;
            enemyWarriorQuantity.setVisible(false);
            buttons[row][col].setName(null);
            buttons[row][col].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field.png"))));
        }
        if(buttons[row][col].getName() == "enemyWizard" && enemyWizard.getQuantity() <= 0) {
            isEnemyWizardMoved = true;
            enemyWizardQuantity.setVisible(false);
            buttons[row][col].setName(null);
            buttons[row][col].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field.png"))));
        }
        if(buttons[row][col].getName() == "enemyGryff" && enemyGryff.getQuantity() <= 0) {
            isEnemyGryffMoved = true;
            enemyGryffQuantity.setVisible(false);
            buttons[row][col].setName(null);
            buttons[row][col].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field.png"))));
        }

        if(enemyGryff.getQuantity() <= 0 && enemyWarrior.getQuantity() <= 0 && enemyWizard.getQuantity() <= 0 && enemyArcher.getQuantity() <= 0 && enemyPeasant.getQuantity() <= 0) {
            gameFlow.appendText("\nYOU WON\n");
        }
        else if(heroGryff.getQuantity() <= 0 && heroWarrior.getQuantity() <= 0 && heroWizard.getQuantity() <= 0 && heroArcher.getQuantity() <= 0 && heroPeasant.getQuantity() <= 0) {
            gameFlow.appendText("\nYOU LOST\n");
        }

    }

    public boolean enemyAttack(float dmg, String name, int row, int col, int range) {

        for(int i = row-range; i <= row+range; i++) {
            for(int j = col-range; j <= col+range; j++) {
                if(i >= 0 && i < MAX_ROW && j >= 0 && j < MAX_COL) {
                    if(buttons[i][j].getName() == "heroPeasant") {
                        int count = 0;
                        dmg -= dmg * heroPeasant.getDefense() / 20;
                        for(int k = 0; k < dmg; k = k + heroPeasant.getHp()) {
                            count++;
                            System.out.println(count);
                        }
                        heroPeasant.setQuantity(heroPeasant.getQuantity() - count);
                        heroPeasantQuantity.setText(heroPeasant.getQuantity());
                        gameFlow.appendText("\nEnemy's " + name + "s killed " + count + " of Hero's Peasants\n");
                        //TODO: CREATE HP*QUANTITY (MAX HP)

                        isUnitDead(i, j);
                        return true;
                    }
                    else if(buttons[i][j].getName() == "heroArcher") {
                        int count = 0;
                        dmg -= dmg * heroArcher.getDefense() / 20;
                        for(int k = 0; k < dmg; k = k + heroArcher.getHp()) {
                            count++;
                            System.out.println(count);
                        }
                        heroArcher.setQuantity(heroArcher.getQuantity() - count);
                        heroArcherQuantity.setText(heroArcher.getQuantity());
                        gameFlow.appendText("\nEnemy's " + name + "s killed " + count + " of Hero's Archers\n");
                        //TODO: CREATE HP*QUANTITY (MAX HP)

                        isUnitDead(i, j);
                        return true;
                    }
                    else if(buttons[i][j].getName() == "heroWarrior") {
                        int count = 0;
                        dmg -= dmg * heroWarrior.getDefense() / 20;
                        for(int k = 0; k < dmg; k = k + heroWarrior.getHp()) {
                            count++;
                            System.out.println(count);
                        }
                        heroWarrior.setQuantity(heroWarrior.getQuantity() - count);
                        heroWarriorQuantity.setText(heroWarrior.getQuantity());
                        gameFlow.appendText("\nEnemy's " + name +"s killed " + count + " of Hero's Warriors\n");
                        //TODO: CREATE HP*QUANTITY (MAX HP)

                        isUnitDead(i, j);
                        return true;
                    }
                    else if(buttons[i][j].getName() == "heroWizard") {
                        int count = 0;
                        dmg -= dmg * heroWizard.getDefense() / 20;
                        for(int k = 0; k < dmg; k = k + heroWizard.getHp()) {
                            count++;
                            System.out.println(count);
                        }
                        heroWizard.setQuantity(heroWizard.getQuantity() - count);
                        heroWizardQuantity.setText(heroWizard.getQuantity());
                        gameFlow.appendText("\nEnemy's " + name + "s killed " + count + " of Hero's Wizards\n");
                        //TODO: CREATE HP*QUANTITY (MAX HP)

                        isUnitDead(i, j);
                        return true;
                    }
                    else if(buttons[i][j].getName() == "heroGryff") {
                        int count = 0;
                        dmg -= dmg * heroGryff.getDefense() / 20;
                        for(int k = 0; k < dmg; k = k + heroGryff.getHp()) {
                            count++;
                            System.out.println(count);
                        }
                        heroGryff.setQuantity(heroGryff.getQuantity() - count);
                        heroGryffQuantity.setText(heroGryff.getQuantity());
                        gameFlow.appendText("\nEnemy's " + name + "s killed " + count + " of Hero's Gryffs\n");
                        //TODO: CREATE HP*QUANTITY (MAX HP)

                        isUnitDead(i, j);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void enemyTurn() {
        if(enemyGryff.getQuantity() <= 0) {
            isEnemyGryffMoved = true;
        }
        if(enemyWarrior.getQuantity() <= 0) {
            isEnemyWarriorMoved = true;
        }
        if(enemyWizard.getQuantity() <= 0) {
            isEnemyWizardMoved = true;
        }
        if(enemyArcher.getQuantity() <= 0) {
            isEnemyArcherMoved = true;
        }
        if(enemyPeasant.getQuantity() <= 0) {
            isEnemyPeasantMoved = true;
        }

        if(enemyGryff.getQuantity() > 0 && !isEnemyGryffMoved) {
            int heroUnitCount = 0;

            for(int i = 0; i < MAX_ROW; i++) {
                for(int j = 0; j < MAX_COL; j++) {
                    if(buttons[i][j].getName() == "enemyGryff") {

                        for(int o = i-1; o <= i+1; o++) {
                            for(int p = j-1; p <= j+1; p++) {
                                if(o >= 0 && o < MAX_ROW && p >= 0 && p < MAX_COL) {
                                    if(buttons[o][p].getName() == "heroPeasant" || buttons[o][p].getName() == "heroArcher" || buttons[o][p].getName() == "heroWarrior" || buttons[o][p].getName() == "heroWizard" || buttons[o][p].getName() == "heroGryff") {
                                        heroUnitCount++;
                                    }
                                }
                            }
                        }

                        //ability
                        if(heroUnitCount >= 2) {
                            float dmg = (float) (heroGryff.getDamage() * heroGryff.getQuantity()) / 2;

                            for(int q = i-1; q <= i+1; q++) {
                                for(int r = j-1; r <= j+1; r++) {
                                    if(q >= 0 && q < MAX_ROW && r >= 0 && r < MAX_COL) {
                                        if(buttons[q][r].getName() == "heroPeasant") {
                                            int count = 0;
                                            dmg -= dmg * heroPeasant.getDefense() / 20;
                                            for(int k = 0; k < dmg; k = k + heroPeasant.getHp()) {
                                                count++;
                                                System.out.println(count);
                                            }
                                            heroPeasant.setQuantity(heroPeasant.getQuantity() - count);
                                            heroPeasantQuantity.setText(heroPeasant.getQuantity());
                                            gameFlow.appendText("\nEnemy's Gryffs killed " + count + " of Hero's Peasants\n");
                                            //TODO: CREATE HP*QUANTITY (MAX HP)
                                            isUnitDead(q, r);
                                        }
                                        else if(buttons[q][r].getName() == "heroArcher") {
                                            int count = 0;
                                            dmg -= dmg * heroArcher.getDefense() / 20;
                                            for(int k = 0; k < dmg; k = k + heroArcher.getHp()) {
                                                count++;
                                                System.out.println(count);
                                            }
                                            heroArcher.setQuantity(heroArcher.getQuantity() - count);
                                            heroArcherQuantity.setText(heroArcher.getQuantity());
                                            gameFlow.appendText("\nEnemy's Gryffs killed " + count + " of Hero's Archers\n");
                                            //TODO: CREATE HP*QUANTITY (MAX HP)
                                            isUnitDead(q, r);
                                        }
                                        else if(buttons[q][r].getName() == "heroWarrior") {
                                            int count = 0;
                                            dmg -= dmg * heroWarrior.getDefense() / 20;
                                            for(int k = 0; k < dmg; k = k + heroWarrior.getHp()) {
                                                count++;
                                                System.out.println(count);
                                            }
                                            heroWarrior.setQuantity(heroWarrior.getQuantity() - count);
                                            heroWarriorQuantity.setText(heroWarrior.getQuantity());
                                            gameFlow.appendText("\nEnemy's Gryffs killed " + count + " of Hero's Warriors\n");
                                            //TODO: CREATE HP*QUANTITY (MAX HP)
                                            isUnitDead(q, r);
                                        }
                                        else if(buttons[q][r].getName() == "heroWizard") {
                                            int count = 0;
                                            dmg -= dmg * heroWizard.getDefense() / 20;
                                            for(int k = 0; k < dmg; k = k + heroWizard.getHp()) {
                                                count++;
                                                System.out.println(count);
                                            }
                                            heroWizard.setQuantity(heroWizard.getQuantity() - count);
                                            heroWizardQuantity.setText(heroWizard.getQuantity());
                                            gameFlow.appendText("\nEnemy's Gryffs killed " + count + " of Hero's Wizards\n");
                                            //TODO: CREATE HP*QUANTITY (MAX HP)
                                            isUnitDead(q, r);
                                        }
                                        else if(buttons[q][r].getName() == "heroGryff") {
                                            int count = 0;
                                            dmg -= dmg * heroGryff.getDefense() / 20;
                                            for(int k = 0; k < dmg; k = k + heroGryff.getHp()) {
                                                count++;
                                                System.out.println(count);
                                            }
                                            heroGryff.setQuantity(heroGryff.getQuantity() - count);
                                            heroGryffQuantity.setText(heroGryff.getQuantity());
                                            gameFlow.appendText("\nEnemy's Gryffs killed " + count + " of Hero's Gryffs\n");
                                            //TODO: CREATE HP*QUANTITY (MAX HP)
                                            isUnitDead(q, r);
                                        }
                                    }
                                }
                            }
                            isEnemyGryffMoved = true;
                            startTurn();
                            return;
                        }

                        //attack
                        else if(heroUnitCount == 1) {
                            float dmg = enemyGryff.getDamage() * enemyGryff.getQuantity();

                            if(enemyAttack(dmg, "Gryff", i, j, 1)) {
                                isEnemyGryffMoved = true;
                                startTurn();
                                return;
                            }
                        }

                        //move
                        else {
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
                                                    gameFlow.appendText("\nEnemy's Gryffs moved to [" + m + "][ " + n + "]\n");
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
                                                    gameFlow.appendText("\nEnemy's Gryffs moved to [" + m + "][" + n + "]\n");
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
                                        if(j - col >= 0 && buttons[i - row][j - col].getName() == null) {
                                            buttons[i - row][j - col].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/enemyGryff.png"))));
                                            buttons[i][j].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field.png"))));
                                            buttons[i][j].setName(null);
                                            buttons[i - row][j - col].setName("enemyGryff");
                                            enemyGryffQuantity.setPosition(buttons[i - row][j - col].getX() + 7, buttons[i - row][j - col].getY() + 45);
                                            isEnemyGryffMoved = true;
                                            gameFlow.appendText("\nEnemy's Gryffs moved to [" + (i - row) + "][" + (j - col) + "]\n");
                                            startTurn();
                                            return;
                                        }
                                    }
                                    else if(colPlus == 2) {
                                        if(j + col < MAX_COL && buttons[i - row][j + col].getName() == null) {
                                            buttons[i - row][j + col].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/enemyGryff.png"))));
                                            buttons[i][j].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field.png"))));
                                            buttons[i][j].setName(null);
                                            buttons[i - row][j + col].setName("enemyGryff");
                                            enemyGryffQuantity.setPosition(buttons[i - row][j + col].getX() + 7, buttons[i - row][j + col].getY() + 45);
                                            isEnemyGryffMoved = true;
                                            gameFlow.appendText("\nEnemy's Gryffs moved to [" + (i - row) + "][" + (j + col) + "]\n");
                                            startTurn();
                                            return;
                                        }
                                    }
                                }
                            }
                            else if(rowPlus == 2) {
                                if(i + row < MAX_ROW) {
                                    if(colPlus == 1) {
                                        if(j - col >= 0 && buttons[i + row][j - col].getName() == null) {
                                            buttons[i + row][j - col].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/enemyGryff.png"))));
                                            buttons[i][j].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field.png"))));
                                            buttons[i][j].setName(null);
                                            buttons[i + row][j - col].setName("enemyGryff");
                                            enemyGryffQuantity.setPosition(buttons[i + row][j - col].getX() + 7, buttons[i + row][j - col].getY() + 45);
                                            isEnemyGryffMoved = true;
                                            gameFlow.appendText("\nEnemy's Gryffs moved to [" + (i + row) + "][" + (j - col) + "]\n");
                                            startTurn();
                                            return;
                                        }
                                    }
                                    else if(colPlus == 2) {
                                        if(j + col < MAX_COL && buttons[i + row][j + col].getName() == null) {
                                            buttons[i + row][j + col].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/enemyGryff.png"))));
                                            buttons[i][j].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field.png"))));
                                            buttons[i][j].setName(null);
                                            buttons[i + row][j + col].setName("enemyGryff");
                                            enemyGryffQuantity.setPosition(buttons[i + row][j + col].getX() + 7, buttons[i + row][j + col].getY() + 45);
                                            isEnemyGryffMoved = true;
                                            gameFlow.appendText("\nEnemy's Gryffs moved to [" + (i + row) + "][" + (j + col) + "]\n");
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

                        int heroUnitCount = 0;

                        //ability
                        float dmg = enemyWarrior.getDamage() * enemyWarrior.getQuantity() * 2;

                        for(int l = j-1; l >= j-3; l--) {
                            System.out.println(i + "," + l);
                            if(l >= 0 && l < MAX_COL) {
                                if(buttons[i][l].getName() == "heroPeasant") {
                                    heroUnitCount++;
                                    int count = 0;
                                    dmg -= dmg * heroPeasant.getDefense() / 20;
                                    for(int k = 0; k < dmg; k = k + heroPeasant.getHp()) {
                                        count++;
                                        System.out.println(count);
                                    }
                                    heroPeasant.setQuantity(heroPeasant.getQuantity() - count);
                                    heroPeasantQuantity.setText(heroPeasant.getQuantity());
                                    gameFlow.appendText("\nEnemy's Warriors killed " + count + " of Hero's Peasants\n");
                                    //TODO: CREATE HP*QUANTITY (MAX HP)
                                    isUnitDead(i, l);
                                }
                                else if(buttons[i][l].getName() == "heroArcher") {
                                    heroUnitCount++;
                                    int count = 0;
                                    dmg -= dmg * heroArcher.getDefense() / 20;
                                    for(int k = 0; k < dmg; k = k + heroArcher.getHp()) {
                                        count++;
                                        System.out.println(count);
                                    }
                                    heroArcher.setQuantity(heroArcher.getQuantity() - count);
                                    heroArcherQuantity.setText(heroArcher.getQuantity());
                                    gameFlow.appendText("\nEnemy's Warriors killed " + count + " of Hero's Archers\n");
                                    //TODO: CREATE HP*QUANTITY (MAX HP)
                                    isUnitDead(i, l);
                                }
                                else if(buttons[i][l].getName() == "heroWarrior") {
                                    heroUnitCount++;
                                    int count = 0;
                                    dmg -= dmg * heroWarrior.getDefense() / 20;
                                    for(int k = 0; k < dmg; k = k + heroWarrior.getHp()) {
                                        count++;
                                        System.out.println(count);
                                    }
                                    heroWarrior.setQuantity(heroWarrior.getQuantity() - count);
                                    heroWarriorQuantity.setText(heroWarrior.getQuantity());
                                    gameFlow.appendText("\nEnemy's Warriors killed " + count + " of Hero's Warriors\n");
                                    //TODO: CREATE HP*QUANTITY (MAX HP)
                                    isUnitDead(i, l);
                                }
                                else if(buttons[i][l].getName() == "heroWizard") {
                                    heroUnitCount++;
                                    int count = 0;
                                    dmg -= dmg * heroWizard.getDefense() / 20;
                                    for(int k = 0; k < dmg; k = k + heroWizard.getHp()) {
                                        count++;
                                        System.out.println(count);
                                    }
                                    heroWizard.setQuantity(heroWizard.getQuantity() - count);
                                    heroWizardQuantity.setText(heroWizard.getQuantity());
                                    gameFlow.appendText("\nEnemy's Warriors killed " + count + " of Hero's Wizards\n");
                                    //TODO: CREATE HP*QUANTITY (MAX HP)
                                    isUnitDead(i, l);
                                }
                                else if(buttons[i][l].getName() == "heroGryff") {
                                    heroUnitCount++;
                                    int count = 0;
                                    dmg -= dmg * heroGryff.getDefense() / 20;
                                    for(int k = 0; k < dmg; k = k + heroGryff.getHp()) {
                                        count++;
                                        System.out.println(count);
                                    }
                                    heroGryff.setQuantity(heroGryff.getQuantity() - count);
                                    heroGryffQuantity.setText(heroGryff.getQuantity());
                                    gameFlow.appendText("\nEnemy's Warriors killed " + count + " of Hero's Gryff\n");
                                    //TODO: CREATE HP*QUANTITY (MAX HP)
                                    isUnitDead(i, l);
                                }
                            }
                        }
                        if(heroUnitCount > 0) {
                            isEnemyWarriorMoved = true;
                            startTurn();
                            return;
                        }

                        //attack
                        if(heroUnitCount == 0) {
                            dmg = enemyWarrior.getDamage() * enemyWarrior.getQuantity();

                            if(enemyAttack(dmg, "Warrior", i, j, 1)) {
                                isEnemyGryffMoved = true;
                                startTurn();
                                return;
                            }
                        }

                        //move
                        if(heroUnitCount == 0) {
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
                                                    gameFlow.appendText("\nEnemy's Warriors moved to [" + m + "][" + n + "]\n");
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
                                                    gameFlow.appendText("\nEnemy's Warriors moved to [" + m + "][" + n + "]\n");
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
                                        if(j - col >= 0 && buttons[i - row][j - col].getName() == null) {
                                            buttons[i - row][j - col].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/enemyWarrior.png"))));
                                            buttons[i][j].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field.png"))));
                                            buttons[i][j].setName(null);
                                            buttons[i - row][j - col].setName("enemyWarrior");
                                            enemyWarriorQuantity.setPosition(buttons[i - row][j - col].getX() + 32, buttons[i - row][j - col].getY());
                                            isEnemyWarriorMoved = true;
                                            gameFlow.appendText("\nEnemy's Warriors moved to [" + (i - row) + "][" + (j - col) + "]\n");
                                            startTurn();
                                            return;
                                        }
                                    }
                                    else if(colPlus == 2) {
                                        if(j + col < MAX_COL && buttons[i - row][j + col].getName() == null) {
                                            buttons[i - row][j + col].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/enemyWarrior.png"))));
                                            buttons[i][j].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field.png"))));
                                            buttons[i][j].setName(null);
                                            buttons[i - row][j + col].setName("enemyWarrior");
                                            enemyWarriorQuantity.setPosition(buttons[i - row][j + col].getX() + 32, buttons[i - row][j + col].getY());
                                            isEnemyWarriorMoved = true;
                                            gameFlow.appendText("\nEnemy's Warriors moved to [" + (i - row) + "][" + (j + col) + "]\n");
                                            startTurn();
                                            return;
                                        }
                                    }
                                }
                            }
                            else if(rowPlus == 2) {
                                if(i + row < MAX_ROW) {
                                    if(colPlus == 1) {
                                        if(j - col >= 0 && buttons[i + row][j - col].getName() == null) {
                                            buttons[i + row][j - col].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/enemyWarrior.png"))));
                                            buttons[i][j].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field.png"))));
                                            buttons[i][j].setName(null);
                                            buttons[i + row][j - col].setName("enemyWarrior");
                                            enemyWarriorQuantity.setPosition(buttons[i + row][j - col].getX() + 32, buttons[i + row][j - col].getY());
                                            isEnemyWarriorMoved = true;
                                            gameFlow.appendText("\nEnemy's Warriors moved to [" + (i + row) + "][" + (j - col) + "]\n");
                                            startTurn();
                                            return;
                                        }
                                    }
                                    else if(colPlus == 2) {
                                        if(j + col < MAX_COL && buttons[i + row][j + col].getName() == null) {
                                            buttons[i + row][j + col].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/enemyWarrior.png"))));
                                            buttons[i][j].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field.png"))));
                                            buttons[i][j].setName(null);
                                            buttons[i + row][j + col].setName("enemyWarrior");
                                            enemyWarriorQuantity.setPosition(buttons[i + row][j + col].getX() + 32, buttons[i + row][j + col].getY());
                                            isEnemyWarriorMoved = true;
                                            gameFlow.appendText("\nEnemy's Warriors moved to [" + (i + row) + "][" + (j + col) + "]\n");
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

                        //move
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
                                            gameFlow.appendText("\nEnemy's Wizards moved to [" + k + "][" + l + "]\n");
                                            startTurn();
                                            return;
                                        }
                                    }
                                }
                            }
                        }

                        //ability
                        float dmgBuff = (float) (enemyWizard.getDamage() * enemyWizard.getQuantity()) / 100;
                        float defBuff = (float) (enemyWizard.getDamage() * enemyWizard.getQuantity()) / 100;

                        if(enemyGryff.getQuantity() >= 30) {
                            enemyGryff.setDamage((int) (enemyGryff.getDamage() + dmgBuff));
                            enemyGryff.setDefense((int) (enemyGryff.getDefense() + defBuff));
                            gameFlow.appendText("\nEnemy's Wizards buffed Enemy's Gryffs\n");

                            isEnemyWizardMoved = true;
                            startTurn();
                            return;
                        }
                        else if(enemyWarrior.getQuantity() >= 50) {
                            enemyWarrior.setDamage((int) (enemyWarrior.getDamage() + dmgBuff));
                            enemyWarrior.setDefense((int) (enemyWarrior.getDefense() + defBuff));
                            gameFlow.appendText("\nEnemy's Wizards buffed Enemy's Warriors\n");

                            isEnemyWizardMoved = true;
                            startTurn();
                            return;
                        }
                        else if(enemyArcher.getQuantity() >= 60) {
                            enemyArcher.setDamage((int) (enemyArcher.getDamage() + dmgBuff));
                            enemyArcher.setDefense((int) (enemyArcher.getDefense() + defBuff));
                            gameFlow.appendText("\nEnemy's Wizards buffed Enemy's Archers\n");

                            isEnemyWizardMoved = true;
                            startTurn();
                            return;
                        }
                        else {
                            //attack
                            float dmg = enemyWizard.getDamage() * enemyWizard.getQuantity();

                            if(enemyAttack(dmg, "Wizard", i, j, 1)) {
                                isEnemyGryffMoved = true;
                                startTurn();
                                return;
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

                        //ability
                        if(isEnemySurround && enemyArcher.getQuantity() <= 60 && !wasEnemyInvisible) {
                            buttons[i][j].setName("invisibleEnemyArcher");
                            buttons[i][j].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/invisibleEnemyArcher.png"))));
                            wasEnemyInvisible = true;
                            invisibleEnemyRow = i;
                            invisibleEnemyCol = j;
                            gameFlow.appendText("\nEnemy's Archers turned invisible\n");

                            isEnemyArcherMoved = true;
                            startTurn();
                            return;
                        }

                        //move
                        else if(isEnemySurround) {
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
                                            gameFlow.appendText("\nEnemy's Archer moved to [" + k + "][" + l + "]\n");
                                            startTurn();
                                            return;
                                        }
                                    }
                                }
                            }
                        }

                        //attack
                        float dmg = enemyArcher.getDamage() * enemyArcher.getQuantity();

                        if(enemyAttack(dmg, "Gryff", i, j, 1)) {
                            isEnemyGryffMoved = true;
                            startTurn();
                            return;
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

                        //attack
                        float dmg = enemyPeasant.getDamage() * enemyPeasant.getQuantity();

                        if(enemyAttack(dmg, "Gryff", i, j, 1)) {
                            isEnemyGryffMoved = true;
                            startTurn();
                            return;
                        }

                        //move
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
                                                gameFlow.appendText("\nEnemy's Peasant moved to [" + m + "][" + n + "]\n");
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
                                                gameFlow.appendText("\nEnemy's Peasant moved to [" + m + "][" + n + "]\n");
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
                                        if(j - col >= 0 && buttons[i - row][j - col].getName() == null) {
                                            buttons[i - row][j - col].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/enemyPeasant.png"))));
                                            buttons[i][j].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field.png"))));
                                            buttons[i][j].setName(null);
                                            buttons[i - row][j - col].setName("enemyPeasant");
                                            enemyPeasantQuantity.setPosition(buttons[i - row][j - col].getX() + 32, buttons[i - row][j - col].getY());
                                            isEnemyPeasantMoved = true;
                                            gameFlow.appendText("\nEnemy's Peasant moved to [" + (i - row) + "][" + (j - col) + "]\n");
                                            startTurn();
                                            return;
                                        }
                                    }
                                    else if(colPlus == 2) {
                                        if(j + col < MAX_COL && buttons[i - row][j + col].getName() == null) {
                                            buttons[i - row][j + col].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/enemyPeasant.png"))));
                                            buttons[i][j].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field.png"))));
                                            buttons[i][j].setName(null);
                                            buttons[i - row][j + col].setName("enemyPeasant");
                                            enemyPeasantQuantity.setPosition(buttons[i - row][j + col].getX() + 32, buttons[i - row][j + col].getY());
                                            isEnemyPeasantMoved = true;
                                            gameFlow.appendText("\nEnemy's Peasant moved to [" + (i - row) + "][" + (j + col) + "]\n");
                                            startTurn();
                                            return;
                                        }
                                    }
                                }
                            }
                            else if(rowPlus == 2) {
                                if(i + row < MAX_ROW) {
                                    if(colPlus == 1) {
                                        if(j - col >= 0 && buttons[i + row][j - col].getName() == null) {
                                            buttons[i + row][j - col].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/enemyPeasant.png"))));
                                            buttons[i][j].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field.png"))));
                                            buttons[i][j].setName(null);
                                            buttons[i + row][j - col].setName("enemyPeasant");
                                            enemyPeasantQuantity.setPosition(buttons[i + row][j - col].getX() + 32, buttons[i + row][j - col].getY());
                                            isEnemyPeasantMoved = true;
                                            gameFlow.appendText("\nEnemy's Peasant moved to [" + (i + row) + "][" + (j - col) + "]\n");
                                            startTurn();
                                            return;
                                        }
                                    }
                                    else if(colPlus == 2) {
                                        if(j + col < MAX_COL && buttons[i + row][j + col].getName() == null) {
                                            buttons[i + row][j + col].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/enemyPeasant.png"))));
                                            buttons[i][j].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field.png"))));
                                            buttons[i][j].setName(null);
                                            buttons[i + row][j + col].setName("enemyPeasant");
                                            enemyPeasantQuantity.setPosition(buttons[i + row][j + col].getX() + 32, buttons[i + row][j + col].getY());
                                            isEnemyPeasantMoved = true;
                                            gameFlow.appendText("\nEnemy's Peasant moved to [" + (i + row) + "][" + (j + col) + "]\n");
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
    }

    public void startTurn() {
        if(isEnemyPeasantMoved && isEnemyArcherMoved && isEnemyWarriorMoved && isEnemyWizardMoved && isEnemyGryffMoved) {
            round++;
            gameFlow.appendText("\n\n\nRound " + round + " started\n\n");
            if(wasInvisible && invisibleRow != -1 && invisibleCol != -1) {
                buttons[invisibleRow][invisibleCol].setName("heroArcher");
                buttons[invisibleRow][invisibleCol].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/heroArcher.png"))));
                invisibleRow = -1;
                invisibleCol = -1;
                gameFlow.appendText("\nHero's Archers turned visible\n");
            }

            if(wasEnemyInvisible && invisibleEnemyRow != -2 && invisibleEnemyCol != -2) {
                buttons[invisibleEnemyRow][invisibleEnemyCol].setName("enemyArcher");
                buttons[invisibleEnemyRow][invisibleEnemyCol].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/units/enemyArcher.png"))));
                invisibleEnemyRow = -2;
                invisibleEnemyCol = -2;
                gameFlow.appendText("\nEnemy's Archers turned visible\n");
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

        if(heroPeasant.getQuantity() <= 0) {
            isHeroPeasantMoved = true;
        }
        if(heroArcher.getQuantity() <= 0) {
            isHeroArcherMoved = true;
        }
        if(heroWarrior.getQuantity() <= 0) {
            isHeroWarriorMoved = true;
        }
        if(heroWizard.getQuantity() <= 0) {
            isHeroWizardMoved = true;
        }
        if(heroGryff.getQuantity() <= 0) {
            isHeroGryffMoved = true;
        }
        if(enemyPeasant.getQuantity() <= 0) {
            isEnemyPeasantMoved = true;
        }
        if(enemyArcher.getQuantity() <= 0) {
            isEnemyArcherMoved = true;
        }
        if(enemyWarrior.getQuantity() <= 0) {
            isEnemyWarriorMoved = true;
        }
        if(enemyWizard.getQuantity() <= 0) {
            isEnemyWizardMoved = true;
        }
        if(enemyGryff.getQuantity() <= 0) {
            isEnemyGryffMoved = true;
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
            //startTurn();
        }

        attackButton.addListener(new ClickListener() {
           public void clicked(InputEvent event, float x, float y) {
               float dmg = 0;
               String unit = "";

               if(name == "heroPeasant") {
                   dmg = heroPeasant.getDamage() * heroPeasant.getQuantity();
                   unit = "Peasants";
                   isHeroPeasantMoved = true;
               }
               else if(name == "heroArcher") {
                   dmg = heroArcher.getDamage() * heroArcher.getQuantity();
                   unit = "Archers";
                   isHeroArcherMoved = true;
               }
               else if(name == "heroWarrior") {
                   dmg = heroWarrior.getDamage() * heroWarrior.getQuantity();
                   unit = "Warriors";
                   isHeroWarriorMoved = true;
               }
               else if(name == "heroWizard") {
                   dmg = heroWizard.getDamage() * heroWizard.getQuantity();
                   unit = "Wizards";
                   isHeroWizardMoved = true;
               }
               else if(name == "heroGryff") {
                   dmg = heroGryff.getDamage() * heroGryff.getQuantity();
                   unit = "Gryffs";
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
                           String finalUnit = unit;
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
                                       gameFlow.appendText("\nHero's " + finalUnit + " killed " + count + " of Enemy's Peasant\n");
                                       //TODO: CREATE HP*QUANTITY (MAX HP)
                                       isUnitDead(finalI, finalJ);
                                   }
                                   else if(buttons[finalI][finalJ].getName() == "enemyArcher") {
                                       dmg = finalDmg - finalDmg * enemyArcher.getDefense() / 20;
                                       for(int i = 0; i < dmg; i = i + enemyArcher.getHp()) {
                                           count++;
                                           System.out.println(count);
                                       }
                                       enemyArcher.setQuantity(enemyArcher.getQuantity() - count);
                                       enemyArcherQuantity.setText(enemyArcher.getQuantity());
                                       gameFlow.appendText("\nHero's " + finalUnit + " killed " + count + " of Enemy's Archers\n");
                                       //TODO: CREATE HP*QUANTITY (MAX HP)
                                       isUnitDead(finalI, finalJ);
                                   }
                                   else if(buttons[finalI][finalJ].getName() == "enemyWarrior") {
                                       dmg = finalDmg - finalDmg * enemyWarrior.getDefense() / 20;
                                       for(int i = 0; i < dmg; i = i + enemyWarrior.getHp()) {
                                           count++;
                                           System.out.println(count);
                                       }
                                       enemyWarrior.setQuantity(enemyWarrior.getQuantity() - count);
                                       enemyWarriorQuantity.setText(enemyWarrior.getQuantity());
                                       gameFlow.appendText("\nHero's " + finalUnit + " killed " + count + " of Enemy's Warriors\n");
                                       //TODO: CREATE HP*QUANTITY (MAX HP)
                                       isUnitDead(finalI, finalJ);
                                   }
                                   else if(buttons[finalI][finalJ].getName() == "enemyWizard") {
                                       dmg = finalDmg - finalDmg * enemyWizard.getDefense() / 20;
                                       for(int i = 0; i < dmg; i = i + enemyWizard.getHp()) {
                                           count++;
                                           System.out.println(count);
                                       }
                                       enemyWizard.setQuantity(enemyWizard.getQuantity() - count);
                                       enemyWizardQuantity.setText(enemyWizard.getQuantity());
                                       gameFlow.appendText("\nHero's " + finalUnit + " killed " + count + " of Enemy's Wizards\n");
                                       //TODO: CREATE HP*QUANTITY (MAX HP)
                                       isUnitDead(finalI, finalJ);
                                   }
                                   else if(buttons[finalI][finalJ].getName() == "enemyGryff") {
                                       dmg = finalDmg - finalDmg * enemyGryff.getDefense() / 20;
                                       for(int i = 0; i < dmg; i = i + enemyGryff.getHp()) {
                                           count++;
                                           System.out.println(count);
                                       }
                                       enemyGryff.setQuantity(enemyGryff.getQuantity() - count);
                                       enemyGryffQuantity.setText(enemyGryff.getQuantity());
                                       gameFlow.appendText("\nHero's " + finalUnit + " killed " + count + " of Enemy's Gryffs\n");
                                       //TODO: CREATE HP*QUANTITY (MAX HP)
                                       isUnitDead(finalI, finalJ);
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
                    gameFlow.appendText("\nHero's Archers turned invisible\n");

                    endTurn();
                }
                else if(name == "heroWarrior") {
                    isHeroWarriorMoved = true;

                    dmg = heroWarrior.getDamage() * heroWarrior.getQuantity() * 2;

                    for(int j = col+1; j <= col+3; j++) {
                        System.out.println(row + "," + j);
                        if(j >= 0 && j < MAX_ROW) {
                            if(buttons[row][j].getName() == "enemyPeasant") {
                                int count = 0;
                                dmg -= dmg * enemyPeasant.getDefense() / 20;
                                for(int k = 0; k < dmg; k = k + enemyPeasant.getHp()) {
                                    count++;
                                    System.out.println(count);
                                }
                                enemyPeasant.setQuantity(enemyPeasant.getQuantity() - count);
                                enemyPeasantQuantity.setText(enemyPeasant.getQuantity());
                                gameFlow.appendText("\nHero's Warriors killed " + count + " of Enemy's Peasant\n");
                                //TODO: CREATE HP*QUANTITY (MAX HP)
                                isUnitDead(row, j);
                            }
                            else if(buttons[row][j].getName() == "enemyArcher") {
                                int count = 0;
                                dmg -= dmg * enemyArcher.getDefense() / 20;
                                for(int k = 0; k < dmg; k = k + enemyArcher.getHp()) {
                                    count++;
                                    System.out.println(count);
                                }
                                enemyArcher.setQuantity(enemyArcher.getQuantity() - count);
                                enemyArcherQuantity.setText(enemyArcher.getQuantity());
                                gameFlow.appendText("\nHero's Warriors killed " + count + " of Enemy's Archers\n");
                                //TODO: CREATE HP*QUANTITY (MAX HP)
                                isUnitDead(row, j);
                            }
                            else if(buttons[row][j].getName() == "enemyWarrior") {
                                int count = 0;
                                dmg -= dmg * enemyWarrior.getDefense() / 20;
                                for(int k = 0; k < dmg; k = k + enemyWarrior.getHp()) {
                                    count++;
                                    System.out.println(count);
                                }
                                enemyWarrior.setQuantity(enemyWarrior.getQuantity() - count);
                                enemyWarriorQuantity.setText(enemyWarrior.getQuantity());
                                gameFlow.appendText("\nHero's Warriors killed " + count + " of Enemy's Warriors\n");
                                //TODO: CREATE HP*QUANTITY (MAX HP)
                                isUnitDead(row, j);
                            }
                            else if(buttons[row][j].getName() == "enemyWizard") {
                                int count = 0;
                                dmg -= dmg * enemyWizard.getDefense() / 20;
                                for(int k = 0; k < dmg; k = k + enemyWizard.getHp()) {
                                    count++;
                                    System.out.println(count);
                                }
                                enemyWizard.setQuantity(enemyWizard.getQuantity() - count);
                                enemyWizardQuantity.setText(enemyWizard.getQuantity());
                                gameFlow.appendText("\nHero's Warriors killed " + count + " of Enemy's Wizards\n");
                                //TODO: CREATE HP*QUANTITY (MAX HP)
                                isUnitDead(row, j);
                            }
                            else if(buttons[row][j].getName() == "enemyGryff") {
                                int count = 0;
                                dmg -= dmg * enemyGryff.getDefense() / 20;
                                for(int k = 0; k < dmg; k = k + enemyGryff.getHp()) {
                                    count++;
                                    System.out.println(count);
                                }
                                enemyGryff.setQuantity(enemyGryff.getQuantity() - count);
                                enemyGryffQuantity.setText(enemyGryff.getQuantity());
                                gameFlow.appendText("\nHero's Warriors killed " + count + " of Enemy's Gryffs\n");
                                //TODO: CREATE HP*QUANTITY (MAX HP)
                                isUnitDead(row, j);
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
                                buttons[finalI][finalJ].addListener(new ClickListener() {
                                    public void clicked(InputEvent event, float x, float y) {
                                        if(buttons[finalI][finalJ].getName() == "heroPeasant") {
                                            heroPeasant.setDamage((int) (heroPeasant.getDamage() + dmgBuff));
                                            heroPeasant.setDefense((int) (heroPeasant.getDefense() + defBuff));
                                            gameFlow.appendText("\nHero's Wizards buffed Hero's Peasant\n");
                                        }
                                        else if(buttons[finalI][finalJ].getName() == "heroArcher") {
                                            heroArcher.setDamage((int) (heroArcher.getDamage() + dmgBuff));
                                            heroArcher.setDefense((int) (heroArcher.getDefense() + defBuff));
                                            gameFlow.appendText("\nHero's Wizards buffed Hero's Archers\n");
                                        }
                                        else if(buttons[finalI][finalJ].getName() == "heroWarrior") {
                                            heroWarrior.setDamage((int) (heroWarrior.getDamage() + dmgBuff));
                                            heroWarrior.setDefense((int) (heroWarrior.getDefense() + defBuff));
                                            gameFlow.appendText("\nHero's Wizards buffed Hero's Warriors\n");
                                        }
                                        else if(buttons[finalI][finalJ].getName() == "heroGryff") {
                                            heroGryff.setDamage((int) (heroGryff.getDamage() + dmgBuff));
                                            heroGryff.setDefense((int) (heroGryff.getDefense() + defBuff));
                                            gameFlow.appendText("\nHero's Wizards buffed Hero's Gryffs\n");
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
                                    gameFlow.appendText("\nHero's Gryffs killed " + count + " of Enemy's Peasant\n");
                                    //TODO: CREATE HP*QUANTITY (MAX HP)
                                    isUnitDead(i, j);
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
                                    gameFlow.appendText("\nHero's Gryffs killed " + count + " of Enemy's Archers\n");
                                    //TODO: CREATE HP*QUANTITY (MAX HP)
                                    isUnitDead(i, j);
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
                                    gameFlow.appendText("\nHero's Gryffs killed " + count + " of Enemy's Warriors\n");
                                    //TODO: CREATE HP*QUANTITY (MAX HP)
                                    isUnitDead(i, j);
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
                                    gameFlow.appendText("\nHero's Gryffs killed " + count + " of Enemy's Wizards\n");
                                    //TODO: CREATE HP*QUANTITY (MAX HP)
                                    isUnitDead(i, j);
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
                                    gameFlow.appendText("\nHero's Gryffs killed " + count + " of Enemy's Gryffs\n");
                                    //TODO: CREATE HP*QUANTITY (MAX HP)
                                    isUnitDead(i, j);
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
        String unit = "";
        if(name == "heroPeasant") {
            moveRange = heroPeasant.getMoveRange();
            unit = "Peasants";
        }
        else if(name == "heroArcher") {
            moveRange = heroArcher.getMoveRange();
            unit = "Archers";
        }
        else if(name == "heroWarrior") {
            moveRange = heroWarrior.getMoveRange();
            unit = "Warriors";
        }
        else if(name == "heroGryff") {
            moveRange = heroGryff.getMoveRange();
            unit = "Gryffs";
        }
        else if(name == "heroWizard") {
            moveRange = heroWizard.getMoveRange();
            unit = "Wizards";
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
                                gameFlow.appendText("\nHero's Peasants moved to [" + finalI + "][" + finalJ + "]\n");
                            }
                            else if(name == "heroArcher") {
                                heroArcherQuantity.setPosition(buttons[finalI][finalJ].getX() + 32, buttons[finalI][finalJ].getY());
                                isHeroArcherMoved = true;
                                gameFlow.appendText("\nHero's Archers moved to [" + finalI + "][" + finalJ + "]\n");
                            }
                            else if(name == "heroWarrior") {
                                heroWarriorQuantity.setPosition(buttons[finalI][finalJ].getX() + 32, buttons[finalI][finalJ].getY());
                                isHeroWarriorMoved = true;
                                gameFlow.appendText("\nHero's Warriors moved to [" + finalI + "][" + finalJ +  "]\n");
                            }
                            else if(name == "heroGryff") {
                                heroGryffQuantity.setPosition(buttons[finalI][finalJ].getX() + 7, buttons[finalI][finalJ].getY() + 45);
                                isHeroGryffMoved = true;
                                gameFlow.appendText("\nHero's Gryffs moved to [" + finalI + "][" + finalJ + "]\n");
                            }
                            else if(name == "heroWizard") {
                                heroWizardQuantity.setPosition(buttons[finalI][finalJ].getX() + 10, buttons[finalI][finalJ].getY());
                                isHeroWizardMoved = true;
                                gameFlow.appendText("\nHero's Wizards moved to [" + finalI + "][" + finalJ + "]\n");
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
                                    gameFlow.appendText("\n\nRound 1 started\n\n");
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
                buttons[i][j].setPosition(300 + i * 64, 160 + j * 64);
                buttons[i][j].setSize(64, 64);
                buttons[i][j].getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field.png"))));
                buttons[i][j].getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/fight/base_field_down.png"))));

                stage.addActor(buttons[i][j]);
            }
        }

        BitmapFont font = new BitmapFont();
        font.getData().setScale(1.2f);

        Texture texture = new Texture(Gdx.files.internal("textures/white_pixel.png")); // Use a plain white texture
        Drawable background = new TextureRegionDrawable(texture);

        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.font = font;
        textFieldStyle.fontColor = Color.BLACK;
        textFieldStyle.background = background;

        gameFlow = new TextArea("Game Start", textFieldStyle);
        gameFlow.setFillParent(false);
        gameFlow.setPrefRows(10);

        scrollPane = new ScrollPane(gameFlow);
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollBarPositions(false, true);
        scrollPane.setForceScroll(false, true);
        scrollPane.setBounds(10, 160, 280, 640);
        gameFlow.layout();
        scrollPane.setScrollY(gameFlow.getHeight());

        stage.addActor(scrollPane);
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
        stage.act(Gdx.graphics.getDeltaTime());
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
