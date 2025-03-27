package com.edvard.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.edvard.game.MainGame;
import com.edvard.game.characters.FightHero;

public class ShopScreen implements Screen {

    public static PlayScreen playScreen;

    Label fireballPrice;
    Label struckPrice;
    Label shieldPrice;

    ImageButton helpButton;
    ImageButton playButton;

    ImageButton buyFireball;
    ImageButton buyStruck;
    ImageButton buyShield;

    Label fireballTextLabel;
    Label struckTextLabel;
    Label shieldTextLabel;

    ImageButton fireball;
    ImageButton struck;
    ImageButton shield;

    Label peasantPriceLabel;
    Label archerPriceLabel;
    Label warriorPriceLabel;
    Label wizardPriceLabel;
    Label gryffPriceLabel;

    Label vitPriceLabel;
    Label intPriceLabel;
    Label strPriceLabel;
    Label mpowPriceLabel;
    Label luckPriceLabel;

    ImageButton gold;

    Label peasantValueLabel;
    Label archerValueLabel;
    Label warriorValueLabel;
    Label wizardValueLabel;
    Label gryffValueLabel;

    ImageButton plusPeasantButton;
    ImageButton minusPeasantButton;
    ImageButton plusArcherButton;
    ImageButton minusArcherButton;
    ImageButton plusWarriorButton;
    ImageButton minusWarriorButton;
    ImageButton plusWizardButton;
    ImageButton minusWizardButton;
    ImageButton plusGryffButton;
    ImageButton minusGryffButton;

    ImageButton peasant;
    ImageButton archer;
    ImageButton warrior;
    ImageButton wizard;
    ImageButton gryff;

    ImageButton plusVitButton;
    ImageButton minusVitButton;
    ImageButton plusIntButton;
    ImageButton minusIntButton;
    ImageButton plusStrButton;
    ImageButton minusStrButton;
    ImageButton plusMpowButton;
    ImageButton minusMpowButton;
    ImageButton plusLuckButton;
    ImageButton minusLuckButton;

    Label vitValueLabel;
    Label intValueLabel;
    Label strValueLabel;
    Label mpowValueLabel;
    Label luckValueLabel;

    Label vitLabel;
    Label intLabel;
    Label strLabel;
    Label mpowLabel;
    Label luckLabel;

    Label goldLabel;

    Stage stage;
    OrthographicCamera camera;

    private MainGame game;

    public static FightHero hero;

    public ShopScreen(MainGame game) {
        stage = new Stage(new ScreenViewport());
        this.game = game;

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false);

        game.batch = new SpriteBatch();

        hero = new FightHero(10, 10, 10, 10, 10);

        goldLabel = new Label("1000", new Label.LabelStyle(new BitmapFont(), Color.GOLD));
        goldLabel.setText(hero.getGoldValue());
        goldLabel.setFontScale(2);
        goldLabel.setPosition(50, 900);
        stage.addActor(goldLabel);

        gold = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/gold.png")))));
        gold.setSize(50, 50);
        gold.setPosition(120, 885);
        stage.addActor(gold);

        createStatLabels();
        createUnitLabels();
        createSpells();
        startGame();
        helpMenu();

        buyStats();
        buyUnits();
        buySpells();

        Gdx.input.setInputProcessor(stage);
    }

    public void saveShop() {
        PlayScreen.shopScreen = this;
        HelpScreen.shopScreen = this;
    }

    public void buySpells() {
        buyFireball.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if(hero.getGoldValue() >= 500) {
                    buyFireball.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/buyButton03.png"))));
                    buyFireball.setTouchable(Touchable.disabled);
                    hero.setFireball(true);
                    hero.setGoldValue(hero.getGoldValue()-500);
                    goldLabel.setText(hero.getGoldValue());
                }
            }
        });
        buyStruck.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if(hero.getGoldValue() >= 500) {
                    buyStruck.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/buyButton03.png"))));
                    buyStruck.setTouchable(Touchable.disabled);
                    hero.setStruck(true);
                    hero.setGoldValue(hero.getGoldValue()-500);
                    goldLabel.setText(hero.getGoldValue());
                }
            }
        });
        buyShield.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if(hero.getGoldValue() >= 500) {
                    buyShield.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/buyButton03.png"))));
                    buyShield.setTouchable(Touchable.disabled);
                    hero.setShield(true);
                    hero.setGoldValue(hero.getGoldValue()-500);
                    goldLabel.setText(hero.getGoldValue());
                }
            }
        });
    }

    public void buyUnits() {
        plusPeasantButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if(hero.getPeasantPrice() <= hero.getGoldValue()) {
                    hero.setPeasantAmount(hero.getPeasantAmount()+1);
                    peasantValueLabel.setText(hero.getPeasantAmount());
                    hero.setGoldValue(hero.getGoldValue()-hero.getPeasantPrice());
                    goldLabel.setText(hero.getGoldValue());
                    hero.setPeasantPrice(hero.getPeasantPrice()+1);
                    peasantPriceLabel.setText(hero.getPeasantPrice());
                }
            }
        });
        plusArcherButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if(hero.getArcherPrice() <= hero.getGoldValue()) {
                    hero.setArcherAmount(hero.getArcherAmount()+1);
                    archerValueLabel.setText(hero.getArcherAmount());
                    hero.setGoldValue(hero.getGoldValue()-hero.getArcherPrice());
                    goldLabel.setText(hero.getGoldValue());
                    hero.setArcherPrice(hero.getArcherPrice()+1);
                    archerPriceLabel.setText(hero.getArcherPrice());
                }
            }
        });
        plusWarriorButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if(hero.getWarriorPrice() <= hero.getGoldValue()) {
                    hero.setWarriorAmount(hero.getWarriorAmount()+1);
                    warriorValueLabel.setText(hero.getWarriorAmount());
                    hero.setGoldValue(hero.getGoldValue()-hero.getWarriorPrice());
                    goldLabel.setText(hero.getGoldValue());
                    hero.setWarriorPrice(hero.getWarriorPrice()+1);
                    warriorPriceLabel.setText(hero.getWarriorPrice());
                }
            }
        });
        plusWizardButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if(hero.getWizardPrice() <= hero.getGoldValue()) {
                    hero.setWizardAmount(hero.getWizardAmount()+1);
                    wizardValueLabel.setText(hero.getWizardAmount());
                    hero.setGoldValue(hero.getGoldValue()-hero.getWizardPrice());
                    goldLabel.setText(hero.getGoldValue());
                    hero.setWizardPrice(hero.getWizardPrice()+1);
                    wizardPriceLabel.setText(hero.getWizardPrice());
                }
            }
        });
        plusGryffButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if(hero.getGryffPrice() <= hero.getGoldValue()) {
                    hero.setGryffAmount(hero.getGryffAmount()+1);
                    gryffValueLabel.setText(hero.getGryffAmount());
                    hero.setGoldValue(hero.getGoldValue()-hero.getGryffPrice());
                    goldLabel.setText(hero.getGoldValue());
                    hero.setGryffPrice(hero.getGryffPrice()+1);
                    gryffPriceLabel.setText(hero.getGryffPrice());
                }
            }
        });

        minusPeasantButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if(hero.getPeasantAmount() > 10) {
                    hero.setPeasantAmount(hero.getPeasantAmount()-1);
                    peasantValueLabel.setText(hero.getPeasantAmount());
                    hero.setPeasantPrice(hero.getPeasantPrice()-1);
                    hero.setGoldValue(hero.getGoldValue()+hero.getPeasantPrice());
                    goldLabel.setText(hero.getGoldValue());
                    peasantPriceLabel.setText(hero.getPeasantPrice());
                }
            }
        });
        minusArcherButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if(hero.getArcherAmount() > 10) {
                    hero.setArcherAmount(hero.getArcherAmount()-1);
                    archerValueLabel.setText(hero.getArcherAmount());
                    hero.setArcherPrice(hero.getArcherPrice()-1);
                    hero.setGoldValue(hero.getGoldValue()+hero.getArcherPrice());
                    goldLabel.setText(hero.getGoldValue());
                    archerPriceLabel.setText(hero.getArcherPrice());
                }
            }
        });
        minusWarriorButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if(hero.getWarriorAmount() > 10) {
                    hero.setWarriorAmount(hero.getWarriorAmount()-1);
                    warriorValueLabel.setText(hero.getWarriorAmount());
                    hero.setWarriorPrice(hero.getWarriorPrice()-1);
                    hero.setGoldValue(hero.getGoldValue()+hero.getWarriorPrice());
                    goldLabel.setText(hero.getGoldValue());
                    warriorPriceLabel.setText(hero.getWarriorPrice());
                }
            }
        });
        minusWizardButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if(hero.getWizardAmount() > 10) {
                    hero.setWizardAmount(hero.getWizardAmount()-1);
                    wizardValueLabel.setText(hero.getWizardAmount());
                    hero.setWizardPrice(hero.getWizardPrice()-1);
                    hero.setGoldValue(hero.getGoldValue()+hero.getWizardPrice());
                    goldLabel.setText(hero.getGoldValue());
                    wizardPriceLabel.setText(hero.getWizardPrice());
                }
            }
        });
        minusGryffButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if(hero.getGryffAmount() > 10) {
                    hero.setGryffAmount(hero.getGryffAmount()-1);
                    gryffValueLabel.setText(hero.getGryffAmount());
                    hero.setGryffPrice(hero.getGryffPrice()-1);
                    hero.setGoldValue(hero.getGoldValue()+hero.getGryffPrice());
                    goldLabel.setText(hero.getGoldValue());
                    gryffPriceLabel.setText(hero.getGryffPrice());
                }
            }
        });
    }

    public void buyStats() {
        plusVitButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if(hero.getVitPrice() <= hero.getGoldValue()) {
                    hero.setVit(hero.getVit()+1);
                    vitValueLabel.setText(hero.getVit());
                    hero.setGoldValue(hero.getGoldValue()-hero.getVitPrice());
                    goldLabel.setText(hero.getGoldValue());
                    hero.setVitPrice(hero.getVitPrice()+1);
                    vitPriceLabel.setText(hero.getVitPrice());
                }
            }
        });
        plusIntButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if(hero.getIntPrice() <= hero.getGoldValue()) {
                    hero.setMana(hero.getMana()+1);
                    intValueLabel.setText(hero.getMana());
                    hero.setGoldValue(hero.getGoldValue()-hero.getIntPrice());
                    goldLabel.setText(hero.getGoldValue());
                    hero.setIntPrice(hero.getIntPrice()+1);
                    intPriceLabel.setText(hero.getIntPrice());
                }
            }
        });
        plusStrButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if(hero.getStrPrice() <= hero.getGoldValue()) {
                    hero.setStrength(hero.getStrength()+1);
                    strValueLabel.setText(hero.getStrength());
                    hero.setGoldValue(hero.getGoldValue()-hero.getStrPrice());
                    goldLabel.setText(hero.getGoldValue());
                    hero.setStrPrice(hero.getStrPrice()+1);
                    strPriceLabel.setText(hero.getStrPrice());
                }
            }
        });
        plusMpowButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if(hero.getMpowPrice() <= hero.getGoldValue()) {
                    hero.setMagicPower(hero.getMagicPower()+1);
                    mpowValueLabel.setText(hero.getMagicPower());
                    hero.setGoldValue(hero.getGoldValue()-hero.getMpowPrice());
                    goldLabel.setText(hero.getGoldValue());
                    hero.setMpowPrice(hero.getMpowPrice()+1);
                    mpowPriceLabel.setText(hero.getMpowPrice());
                }
            }
        });
        plusLuckButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if(hero.getLuckPrice() <= hero.getGoldValue()) {
                    hero.setLuck(hero.getLuck()+1);
                    luckValueLabel.setText(hero.getLuck());
                    hero.setGoldValue(hero.getGoldValue()-hero.getLuckPrice());
                    goldLabel.setText(hero.getGoldValue());
                    hero.setLuckPrice(hero.getLuckPrice()+1);
                    luckPriceLabel.setText(hero.getLuckPrice());
                }
            }
        });

        minusVitButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if(hero.getVit() > 10) {
                    hero.setVit(hero.getVit()-1);
                    vitValueLabel.setText(hero.getVit());
                    hero.setVitPrice(hero.getVitPrice()-1);
                    hero.setGoldValue(hero.getGoldValue()+hero.getVitPrice());
                    goldLabel.setText(hero.getGoldValue());
                    vitPriceLabel.setText(hero.getVitPrice());
                }
            }
        });
        minusIntButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if(hero.getMana() > 10) {
                    hero.setMana(hero.getMana()-1);
                    intValueLabel.setText(hero.getMana());
                    hero.setIntPrice(hero.getIntPrice()-1);
                    hero.setGoldValue(hero.getGoldValue()+hero.getIntPrice());
                    goldLabel.setText(hero.getGoldValue());
                    intPriceLabel.setText(hero.getIntPrice());
                }
            }
        });
        minusStrButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if(hero.getStrength() > 10) {
                    hero.setStrength(hero.getStrength()-1);
                    strValueLabel.setText(hero.getStrength());
                    hero.setStrPrice(hero.getStrPrice()-1);
                    hero.setGoldValue(hero.getGoldValue()+hero.getStrPrice());
                    goldLabel.setText(hero.getGoldValue());
                    strPriceLabel.setText(hero.getStrPrice());
                }
            }
        });
        minusMpowButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if(hero.getMagicPower() > 10) {
                    hero.setMagicPower(hero.getMagicPower()-1);
                    mpowValueLabel.setText(hero.getMagicPower());
                    hero.setMpowPrice(hero.getMpowPrice()-1);
                    hero.setGoldValue(hero.getGoldValue()+hero.getMpowPrice());
                    goldLabel.setText(hero.getGoldValue());
                    mpowPriceLabel.setText(hero.getMpowPrice());
                }
            }
        });
        minusLuckButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if(hero.getLuck() > 10) {
                    hero.setLuck(hero.getLuck()-1);
                    luckValueLabel.setText(hero.getLuck());
                    hero.setLuckPrice(hero.getLuckPrice()-1);
                    hero.setGoldValue(hero.getGoldValue()+hero.getLuckPrice());
                    goldLabel.setText(hero.getGoldValue());
                    luckPriceLabel.setText(hero.getLuckPrice());
                }
            }
        });
    }

    public void helpMenu() {
        Drawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/helpButton.png"))));
        helpButton = new ImageButton(drawable);
        helpButton.setSize((float) 790 /5, (float) 342 /5);
        helpButton.setPosition(30, 25);

        helpButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/helpButton.png"))));
        helpButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/helpButton03.png"))));

        helpButton.setTouchable(Touchable.enabled);

        helpButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                //Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/button-clicked-sound.mp3"));
                //sound.play();
                saveShop();
                game.setScreen(new HelpScreen(game, "shopScreen"));
            }
        });
        stage.addActor(helpButton);
    }

    public void startGame() {
        Drawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/play01.png"))));
        playButton = new ImageButton(drawable);
        playButton.setSize((float) 790 /5, (float) 342 /5);
        playButton.setPosition(770, 25);

        playButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/play01.png"))));
        playButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/play03.png"))));

        playButton.setTouchable(Touchable.enabled);

        playButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/button-clicked-sound.mp3"));
                sound.play();
                saveShop();
                if(playScreen != null) {
                    game.setScreen(playScreen);
                }
                else {
                    game.setScreen(new PlayScreen(game));
                }
            }
        });
        stage.addActor(playButton);
    }

    public void createSpells() {

        fireball = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/fireballimage.png")))));
        struck = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/struckimage.png")))));
        shield = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/shieldimage.png")))));

        fireball.setSize(256, 64);
        fireball.setPosition(78, 400);
        stage.addActor(fireball);

        struck.setSize(256, 64);
        struck.setPosition(352, 400);
        stage.addActor(struck);

        shield.setSize(256, 64);
        shield.setPosition(626, 400);
        stage.addActor(shield);


        BitmapFont font = new BitmapFont();
        Texture texture = new Texture(Gdx.files.internal("buttons/shop/tableBackground.png"));
        Drawable background = new TextureRegionDrawable(texture);
        Label.LabelStyle style = new Label.LabelStyle();
        style.background = background;
        style.fontColor = Color.WHITE;
        style.font = font;

        fireballTextLabel = new Label("Mana cost: 15\n\nThis spell damages \neveryone in a 3x3 area\n\n\nPrice:      ", style);
        struckTextLabel = new Label("Mana cost: 10\n\nYou can struck an enemy \nunit with this spell\n\n\nPrice:      ", style);
        shieldTextLabel = new Label("Mana cost: 7\n\nYou can shield an ally unit, \nincreasing its defense\n\n\nPrice:      ", style);

        fireballTextLabel.setSize(256, 140);
        fireballTextLabel.setPosition(78, 250);
        fireballTextLabel.setAlignment(Align.center);
        stage.addActor(fireballTextLabel);

        struckTextLabel.setSize(256, 140);
        struckTextLabel.setPosition(352, 250);
        struckTextLabel.setAlignment(Align.center);
        stage.addActor(struckTextLabel);

        shieldTextLabel.setSize(256, 140);
        shieldTextLabel.setPosition(626, 250);
        shieldTextLabel.setAlignment(Align.center);
        stage.addActor(shieldTextLabel);

        fireballPrice = new Label("500", new Label.LabelStyle(new BitmapFont(), Color.GOLD));
        fireballPrice.setPosition(220, 257);
        stage.addActor(fireballPrice);
        createGold(245, 254);

        struckPrice = new Label("500", new Label.LabelStyle(new BitmapFont(), Color.GOLD));
        struckPrice.setPosition(495, 257);
        stage.addActor(struckPrice);
        createGold(520, 254);

        shieldPrice = new Label("500", new Label.LabelStyle(new BitmapFont(), Color.GOLD));
        shieldPrice.setPosition(770, 257);
        stage.addActor(shieldPrice);
        createGold(795, 254);


        buyFireball = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/buyButton.png")))));
        buyFireball.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/buyButton03.png"))));
        buyFireball.setSize((float) 790 /5, (float) 342 /5);
        buyFireball.setPosition(128, 180);
        stage.addActor(buyFireball);

        buyStruck = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/buyButton.png")))));
        buyStruck.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/buyButton03.png"))));
        buyStruck.setSize((float) 790 /5, (float) 342 /5);
        buyStruck.setPosition(402, 180);
        stage.addActor(buyStruck);

        buyShield = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/buyButton.png")))));
        buyShield.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/buyButton03.png"))));
        buyShield.setSize((float) 790 /5, (float) 342 /5);
        buyShield.setPosition(676, 180);
        stage.addActor(buyShield);
    }

    public void createGold(int width, int height) {
        gold = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/gold.png")))));
        gold.setSize(25, 25);
        gold.setPosition(width, height);
        stage.addActor(gold);
    }

    public void createUnitLabels() {
        peasant = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/peasant.png")))));
        peasant.setSize(45, 45);
        peasant.setPosition(715, 790);
        archer = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/archer.png")))));
        archer.setSize(45, 45);
        archer.setPosition(715, 740);
        warrior = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/warrior.png")))));
        warrior.setSize(45, 45);
        warrior.setPosition(715, 690);
        wizard = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/wizard.png")))));
        wizard.setSize(45, 45);
        wizard.setPosition(715, 640);
        gryff = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/gryff.png")))));
        gryff.setSize(45, 45);
        gryff.setPosition(715, 590);

        stage.addActor(peasant);
        stage.addActor(archer);
        stage.addActor(warrior);
        stage.addActor(wizard);
        stage.addActor(gryff);

        plusPeasantButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/plusButton.png")))));
        plusPeasantButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/plusButton.png"))));
        plusPeasantButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/plusButton03.png"))));
        plusArcherButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/plusButton.png")))));
        plusArcherButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/plusButton.png"))));
        plusArcherButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/plusButton03.png"))));
        plusWarriorButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/plusButton.png")))));
        plusWarriorButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/plusButton.png"))));
        plusWarriorButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/plusButton03.png"))));
        plusWizardButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/plusButton.png")))));
        plusWizardButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/plusButton.png"))));
        plusWizardButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/plusButton03.png"))));
        plusGryffButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/plusButton.png")))));
        plusGryffButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/plusButton.png"))));
        plusGryffButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/plusButton03.png"))));
        minusPeasantButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/minusButton.png")))));
        minusPeasantButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/minusButton.png"))));
        minusPeasantButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/minusButton03.png"))));
        minusArcherButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/minusButton.png")))));
        minusArcherButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/minusButton.png"))));
        minusArcherButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/minusButton03.png"))));
        minusWarriorButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/minusButton.png")))));
        minusWarriorButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/minusButton.png"))));
        minusWarriorButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/minusButton03.png"))));
        minusWizardButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/minusButton.png")))));
        minusWizardButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/minusButton.png"))));
        minusWizardButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/minusButton03.png"))));
        minusGryffButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/minusButton.png")))));
        minusGryffButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/minusButton.png"))));
        minusGryffButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/minusButton03.png"))));

        minusPeasantButton.setSize(30, 30);
        minusPeasantButton.setPosition(650, 793);
        minusArcherButton.setSize(30, 30);
        minusArcherButton.setPosition(650, 743);
        minusWarriorButton.setSize(30, 30);
        minusWarriorButton.setPosition(650, 693);
        minusWizardButton.setSize(30, 30);
        minusWizardButton.setPosition(650, 643);
        minusGryffButton.setSize(30, 30);
        minusGryffButton.setPosition(650, 593);

        plusPeasantButton.setSize(30, 30);
        plusPeasantButton.setPosition(800, 793);
        plusArcherButton.setSize(30, 30);
        plusArcherButton.setPosition(800, 743);
        plusWarriorButton.setSize(30, 30);
        plusWarriorButton.setPosition(800, 693);
        plusWizardButton.setSize(30, 30);
        plusWizardButton.setPosition(800, 643);
        plusGryffButton.setSize(30, 30);
        plusGryffButton.setPosition(800, 593);

        stage.addActor(minusPeasantButton);
        stage.addActor(minusArcherButton);
        stage.addActor(minusWarriorButton);
        stage.addActor(minusWizardButton);
        stage.addActor(minusGryffButton);
        stage.addActor(plusPeasantButton);
        stage.addActor(plusArcherButton);
        stage.addActor(plusWarriorButton);
        stage.addActor(plusWizardButton);
        stage.addActor(plusGryffButton);

        peasantValueLabel = new Label("10", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        peasantValueLabel.setText(hero.getPeasantAmount());
        archerValueLabel = new Label("10", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        archerValueLabel.setText(hero.getArcherAmount());
        warriorValueLabel = new Label("10", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        warriorValueLabel.setText(hero.getWarriorAmount());
        wizardValueLabel = new Label("10", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        wizardValueLabel.setText(hero.getWizardAmount());
        gryffValueLabel = new Label("10", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        gryffValueLabel.setText(hero.getGryffAmount());

        peasantValueLabel.setPosition(865, 800);
        archerValueLabel.setPosition(865, 750);
        warriorValueLabel.setPosition(865, 700);
        wizardValueLabel.setPosition(865, 650);
        gryffValueLabel.setPosition(865, 600);

        stage.addActor(peasantValueLabel);
        stage.addActor(archerValueLabel);
        stage.addActor(warriorValueLabel);
        stage.addActor(wizardValueLabel);
        stage.addActor(gryffValueLabel);

        peasantPriceLabel = new Label("5", new Label.LabelStyle(new BitmapFont(), Color.GOLD));
        peasantPriceLabel.setText(hero.getPeasantPrice());
        peasantPriceLabel.setPosition(588, 800);
        stage.addActor(peasantPriceLabel);
        createGold(610, 795);

        archerPriceLabel = new Label("5", new Label.LabelStyle(new BitmapFont(), Color.GOLD));
        archerPriceLabel.setText(hero.getArcherPrice());
        archerPriceLabel.setPosition(588, 750);
        stage.addActor(archerPriceLabel);
        createGold(610, 745);

        warriorPriceLabel = new Label("5", new Label.LabelStyle(new BitmapFont(), Color.GOLD));
        warriorPriceLabel.setText(hero.getWarriorPrice());
        warriorPriceLabel.setPosition(588, 700);
        stage.addActor(warriorPriceLabel);
        createGold(610, 695);

        wizardPriceLabel = new Label("5", new Label.LabelStyle(new BitmapFont(), Color.GOLD));
        wizardPriceLabel.setText(hero.getWizardPrice());
        wizardPriceLabel.setPosition(588, 650);
        stage.addActor(wizardPriceLabel);
        createGold(610, 645);

        gryffPriceLabel = new Label("5", new Label.LabelStyle(new BitmapFont(), Color.GOLD));
        gryffPriceLabel.setText(hero.getGryffPrice());
        gryffPriceLabel.setPosition(588, 600);
        stage.addActor(gryffPriceLabel);
        createGold(610, 595);
    }

    public void createStatLabels() {
        vitLabel = new Label("Vitality", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        intLabel = new Label("Intelligence", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        strLabel = new Label("Strength", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        mpowLabel = new Label("Magic power", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        luckLabel = new Label("Luck", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        vitLabel.setPosition(150, 800);
        intLabel.setPosition(150, 750);
        strLabel.setPosition(150, 700);
        mpowLabel.setPosition(150, 650);
        luckLabel.setPosition(150, 600);

        stage.addActor(vitLabel);
        stage.addActor(intLabel);
        stage.addActor(strLabel);
        stage.addActor(mpowLabel);
        stage.addActor(luckLabel);


        vitValueLabel = new Label("10", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        intValueLabel = new Label("10", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        strValueLabel = new Label("10", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        mpowValueLabel = new Label("10", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        luckValueLabel = new Label("10", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        vitValueLabel.setPosition(50, 800);
        intValueLabel.setPosition(50, 750);
        strValueLabel.setPosition(50, 700);
        mpowValueLabel.setPosition(50, 650);
        luckValueLabel.setPosition(50, 600);

        stage.addActor(vitValueLabel);
        stage.addActor(intValueLabel);
        stage.addActor(strValueLabel);
        stage.addActor(mpowValueLabel);
        stage.addActor(luckValueLabel);


        plusVitButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/plusButton.png")))));
        plusVitButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/plusButton.png"))));
        plusVitButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/plusButton03.png"))));
        plusIntButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/plusButton.png")))));
        plusIntButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/plusButton.png"))));
        plusIntButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/plusButton03.png"))));
        plusStrButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/plusButton.png")))));
        plusStrButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/plusButton.png"))));
        plusStrButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/plusButton03.png"))));
        plusMpowButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/plusButton.png")))));
        plusMpowButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/plusButton.png"))));
        plusMpowButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/plusButton03.png"))));
        plusLuckButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/plusButton.png")))));
        plusLuckButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/plusButton.png"))));
        plusLuckButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/plusButton03.png"))));
        minusVitButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/minusButton.png")))));
        minusVitButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/minusButton.png"))));
        minusVitButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/minusButton03.png"))));
        minusIntButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/minusButton.png")))));
        minusIntButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/minusButton.png"))));
        minusIntButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/minusButton03.png"))));
        minusStrButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/minusButton.png")))));
        minusStrButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/minusButton.png"))));
        minusStrButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/minusButton03.png"))));
        minusMpowButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/minusButton.png")))));
        minusMpowButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/minusButton.png"))));
        minusMpowButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/minusButton03.png"))));
        minusLuckButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/minusButton.png")))));
        minusLuckButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/minusButton.png"))));
        minusLuckButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/shop/minusButton03.png"))));

        minusVitButton.setSize(30, 30);
        minusVitButton.setPosition(100, 793);
        minusIntButton.setSize(30, 30);
        minusIntButton.setPosition(100, 743);
        minusStrButton.setSize(30, 30);
        minusStrButton.setPosition(100, 693);
        minusMpowButton.setSize(30, 30);
        minusMpowButton.setPosition(100, 643);
        minusLuckButton.setSize(30, 30);
        minusLuckButton.setPosition(100, 593);

        plusVitButton.setSize(30, 30);
        plusVitButton.setPosition(250, 793);
        plusIntButton.setSize(30, 30);
        plusIntButton.setPosition(250, 743);
        plusStrButton.setSize(30, 30);
        plusStrButton.setPosition(250, 693);
        plusMpowButton.setSize(30, 30);
        plusMpowButton.setPosition(250, 643);
        plusLuckButton.setSize(30, 30);
        plusLuckButton.setPosition(250, 593);

        stage.addActor(minusVitButton);
        stage.addActor(minusIntButton);
        stage.addActor(minusStrButton);
        stage.addActor(minusMpowButton);
        stage.addActor(minusLuckButton);

        stage.addActor(plusVitButton);
        stage.addActor(plusIntButton);
        stage.addActor(plusStrButton);
        stage.addActor(plusMpowButton);
        stage.addActor(plusLuckButton);

        vitPriceLabel = new Label("5", new Label.LabelStyle(new BitmapFont(), Color.GOLD));
        vitPriceLabel.setText(hero.getVitPrice());
        vitPriceLabel.setPosition(300, 800);
        stage.addActor(vitPriceLabel);
        createGold(322, 795);

        intPriceLabel = new Label("5", new Label.LabelStyle(new BitmapFont(), Color.GOLD));
        intPriceLabel.setText(hero.getIntPrice());
        intPriceLabel.setPosition(300, 750);
        stage.addActor(intPriceLabel);
        createGold(322, 745);

        strPriceLabel = new Label("5", new Label.LabelStyle(new BitmapFont(), Color.GOLD));
        strPriceLabel.setText(hero.getStrPrice());
        strPriceLabel.setPosition(300, 700);
        stage.addActor(strPriceLabel);
        createGold(322, 695);

        mpowPriceLabel = new Label("5", new Label.LabelStyle(new BitmapFont(), Color.GOLD));
        mpowPriceLabel.setText(hero.getMpowPrice());
        mpowPriceLabel.setPosition(300, 650);
        stage.addActor(mpowPriceLabel);
        createGold(322, 645);

        luckPriceLabel = new Label("5", new Label.LabelStyle(new BitmapFont(), Color.GOLD));
        luckPriceLabel.setText(hero.getLuckPrice());
        luckPriceLabel.setPosition(300, 600);
        stage.addActor(luckPriceLabel);
        createGold(322, 595);
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
        stage.getViewport().update(i, i1, true);
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
