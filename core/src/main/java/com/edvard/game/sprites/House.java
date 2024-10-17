package com.edvard.game.sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

public class House extends InteractiveTileObject{
    public House(World world, TiledMap tiledMap, Rectangle bounds) {
        super(world, tiledMap, bounds);
        fixture.setUserData(this);
    }

    @Override
    public void onHit() {
        System.out.println("House");
    }

    @Override
    public void afterHit() {

    }
}
