package com.edvard.game.sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.edvard.game.screens.PlayScreen;


public class Chest extends InteractiveTileObject{
    public Chest(World world, TiledMap tiledMap, Rectangle bounds) {
        super(world, tiledMap, bounds);
        fixture.setUserData(this);
    }

    @Override
    public void onHit() {
        PlayScreen.startFightLabel.setVisible(true);
    }

    @Override
    public void afterHit() {
        PlayScreen.startFightLabel.setVisible(false);
    }
}
