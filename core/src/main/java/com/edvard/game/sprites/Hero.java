package com.edvard.game.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.edvard.game.MainGame;


public class Hero extends Sprite {
    public World world;
    public Body b2body;

    public Hero(World world) {
        this.world = world;
        defineHero();
    }

    public void defineHero() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(80 / MainGame.PPM, 32 / MainGame.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        b2body.setGravityScale(0);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / MainGame.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }


}
