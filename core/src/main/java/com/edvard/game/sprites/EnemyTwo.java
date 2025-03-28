package com.edvard.game.sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.edvard.game.screens.PlayScreen;

public class EnemyTwo extends InteractiveTileObject{

    public EnemyTwo(World world, TiledMap tiledMap, Rectangle bounds) {
        super(world, tiledMap, bounds);
        fixture.setUserData(this);
    }

    @Override
    public void onHit() {
        PlayScreen.startFightLabel.setVisible(true);
        PlayScreen.isSpacePressable = true;
        PlayScreen.minPowerLevel = 30;
        PlayScreen.maxPowerLevel = 40;
    }

    @Override
    public void afterHit() {
        PlayScreen.startFightLabel.setVisible(false);
        PlayScreen.isSpacePressable = false;
    }
}
