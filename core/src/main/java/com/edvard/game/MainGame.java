package com.edvard.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.edvard.game.screens.FightScreen;
import com.edvard.game.screens.MenuScreen;
import com.edvard.game.screens.PlayScreen;

public class MainGame extends Game {
    public static final int V_WIDTH = 200;
    public static final int V_HEIGHT = 200;
    public static final float PPM = 100;

    public SpriteBatch batch;

    private PlayScreen playScreen;
    private FightScreen fightScreen;

    public enum ScreenKey {playScreen, fightScreen}

    public void setScreens(ScreenKey key) {
        switch (key) {
            case playScreen:
                setScreen(playScreen);
                break;
            case fightScreen:
                setScreen(fightScreen);
                break;
        }
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        setScreen(new MenuScreen(this));
    }

    public void render() {
        super.render();
    }
}
