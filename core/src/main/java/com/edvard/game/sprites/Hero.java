package com.edvard.game.sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.edvard.game.MainGame;
import com.edvard.game.screens.PlayScreen;


public class Hero extends Sprite {
    public enum State { STANDING_UP, STANDING_DOWN, STANDING_SIDE, RUNNING_UP, RUNNING_DOWN, RUNNING_SIDE };
    public State currentState;
    public State previousState;

    public World world;
    public Body b2body;
    private TextureRegion heroStand;

    private Animation<TextureRegion> heroStandingUp;
    private Animation<TextureRegion> heroStandingDown;
    private Animation<TextureRegion> heroRunningUp;
    private Animation<TextureRegion> heroRunningDown;
    private Animation<TextureRegion> heroRunningSide;
    private Animation<TextureRegion> heroStandingSide;

    private float stateTimer;

    private boolean runningRight;

    public Hero(World world, PlayScreen screen) {
        super(screen.getAtlas().findRegion("Player"));

        this.world = world;

        currentState = State.STANDING_DOWN;
        previousState = State.STANDING_DOWN;
        stateTimer = 0f;
        runningRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 0; i < 6; i++) {
            frames.add(new TextureRegion(getTexture(), i * 32, 0, 32, 32));
        }
        heroStandingDown = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();

        for(int i = 0; i < 6; i++) {
            frames.add(new TextureRegion(getTexture(), i * 32, 32, 32, 32));
        }
        heroStandingSide = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();

        for(int i = 0; i < 6; i++) {
            frames.add(new TextureRegion(getTexture(), i * 32, 64, 32, 32));
        }
        heroStandingUp = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();

        for(int i = 0; i < 6; i++) {
            frames.add(new TextureRegion(getTexture(), i * 32, 96, 32, 32));
        }
        heroRunningDown = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();

        for(int i = 0; i < 6; i++) {
            frames.add(new TextureRegion(getTexture(), i * 32, 128, 32, 32));
        }
        heroRunningSide = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();

        for(int i = 0; i < 6; i++) {
            frames.add(new TextureRegion(getTexture(), i * 32, 160, 32, 32));
        }
        heroRunningUp = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();

        defineHero();

        heroStand = new TextureRegion(getTexture(), 0, 0, 32, 32);
        setBounds(0, 0, 16 / MainGame.PPM, 16 / MainGame.PPM);
        setRegion(heroStand);
    }

    public void update(float delta) {
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);

        setRegion(getFrame(delta));
    }

    public TextureRegion getFrame(float delta) {
        currentState = getState();

        TextureRegion region;
        switch(currentState) {
            case STANDING_DOWN:
                region = heroStandingDown.getKeyFrame(stateTimer, true);
                break;
            case STANDING_UP:
                region = heroStandingUp.getKeyFrame(stateTimer, true);
                break;
            case STANDING_SIDE:
                region = heroStandingSide.getKeyFrame(stateTimer, true);
                break;
            case RUNNING_UP:
                region = heroRunningUp.getKeyFrame(stateTimer, true);
                break;
            case RUNNING_DOWN:
                region = heroRunningDown.getKeyFrame(stateTimer, true);
                break;
            case RUNNING_SIDE:
                region = heroRunningSide.getKeyFrame(stateTimer, true);
                break;
            default:
                region = null;
                break;
        }
        if((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()) {
            region.flip(true, false);
            runningRight = false;
        }
        else if((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()) {
            region.flip(true, false);
            runningRight = true;
        }

        if(b2body.getLinearVelocity().y == 0 && previousState == State.RUNNING_UP) {
            region = heroStandingUp.getKeyFrame(stateTimer, true);
        }

        stateTimer = currentState == previousState ? stateTimer + delta : 0;
        previousState = currentState;
        return region;
    }

    public State getState() {
        if(b2body.getLinearVelocity().y > 0) {
            return State.RUNNING_UP;
        }
        else if(b2body.getLinearVelocity().y < 0) {
            return State.RUNNING_DOWN;
        }
        else if(b2body.getLinearVelocity().x != 0) {
            return State.RUNNING_SIDE;
        }
        else {
            if(previousState == State.RUNNING_UP) {
                return State.STANDING_UP;
            }
            else if(previousState == State.RUNNING_DOWN) {
                return State.STANDING_DOWN;
            }
            return State.STANDING_DOWN;
        }
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
