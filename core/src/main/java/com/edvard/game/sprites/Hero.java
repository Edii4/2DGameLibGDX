package com.edvard.game.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.edvard.game.MainGame;
import com.edvard.game.screens.PlayScreen;


public class Hero extends Sprite {
    public World world;
    public Body b2body;
    private TextureRegion heroStand;

    public Hero(World world, PlayScreen screen) {
        super(screen.getAtlas().findRegion("Player"));

        this.world = world;
        defineHero();

        heroStand = new TextureRegion(getTexture(), 0, 0, 32, 32);
        setBounds(0, 0, 16 / MainGame.PPM, 16 / MainGame.PPM);
        setRegion(heroStand);
    }

    public void update(float delta) {
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
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
