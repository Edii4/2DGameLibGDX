package com.edvard.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.edvard.game.MainGame;
import com.edvard.game.scenes.Hud;
import com.edvard.game.sprites.Hero;

public class PlayScreen implements Screen {

    private Hero hero;

    private World world;
    private Box2DDebugRenderer debugRenderer;

    private MainGame game;
    private OrthographicCamera camera;
    private Viewport gameport;

    private TmxMapLoader mapLoader;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer renderer;
    private Hud hud;

    public PlayScreen(MainGame game) {
        this.game = game;
        camera = new OrthographicCamera();

        hud = new Hud(game.batch);

        gameport = new FitViewport(MainGame.V_WIDTH / MainGame.PPM, MainGame.V_HEIGHT / MainGame.PPM, camera);
        gameport.apply();

        mapLoader = new TmxMapLoader();
        tiledMap = mapLoader.load("maps/first-map.tmx");
        renderer = new OrthogonalTiledMapRenderer(tiledMap, 1/MainGame.PPM);

        camera.position.set(gameport.getWorldWidth() / 2, gameport.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0, -10), true);
        debugRenderer = new Box2DDebugRenderer();

        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;

        //terrain
        for(MapObject object : tiledMap.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rect.getX() + rect.getWidth() / 2) /MainGame.PPM, (rect.getY() + rect.getHeight() / 2) / MainGame.PPM);

            body = world.createBody(bodyDef);

            shape.setAsBox(rect.getWidth() / 2 / MainGame.PPM, rect.getHeight() / 2 / MainGame.PPM);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);

        }

        //house
        for(MapObject object : tiledMap.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rect.getX() + rect.getWidth() / 2) / MainGame.PPM, (rect.getY() + rect.getHeight() / 2) / MainGame.PPM);

            body = world.createBody(bodyDef);

            shape.setAsBox(rect.getWidth() / 2 / MainGame.PPM, rect.getHeight() / 2 / MainGame.PPM);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);

        }

        //chests
        for(MapObject object : tiledMap.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rect.getX() + rect.getWidth() / 2) / MainGame.PPM, (rect.getY() + rect.getHeight() / 2) / MainGame.PPM);

            body = world.createBody(bodyDef);

            shape.setAsBox(rect.getWidth() / 2 / MainGame.PPM, rect.getHeight() / 2 / MainGame.PPM);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);


        }

        //hero
        hero = new Hero(world);

    }

    @Override
    public void show() {

    }

    public void handleInput(float delta) {

        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            hero.b2body.applyLinearImpulse(new Vector2(0, 0.5f), hero.b2body.getWorldCenter(), true);
            System.out.println(delta);
            if(hero.b2body.getLinearVelocity().y > 0.5f) {
                hero.b2body.setLinearVelocity(new Vector2(0, 0));
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D) && hero.b2body.getLinearVelocity().x <= 0.5f) {
            hero.b2body.applyLinearImpulse(new Vector2(0.5f, 0), hero.b2body.getWorldCenter(), true);
            if(hero.b2body.getLinearVelocity().x > 0.5f) {
                hero.b2body.setLinearVelocity(new Vector2(0, 0));
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            hero.b2body.applyLinearImpulse(new Vector2(0, -0.5f), hero.b2body.getWorldCenter(), true);
            if(hero.b2body.getLinearVelocity().y < -0.5f) {
                hero.b2body.setLinearVelocity(new Vector2(0, 0));
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            hero.b2body.applyLinearImpulse(new Vector2(-0.5f, 0), hero.b2body.getWorldCenter(), true);
            if(hero.b2body.getLinearVelocity().x < -0.5f) {
                hero.b2body.setLinearVelocity(new Vector2(0, 0));
            }
        }



    }

    public void update(float delta) {
        handleInput(delta);

        world.step(1/60f, 6, 2);
        camera.position.x = hero.b2body.getPosition().x;
        camera.position.y = hero.b2body.getPosition().y;

        camera.update();

        renderer.setView(camera);
    }

    @Override
    public void render(float v) {
        update(v);
        hero.b2body.setLinearVelocity(0, 0);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        debugRenderer.render(world, camera.combined);

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

    }

    @Override
    public void resize(int i, int i1) {
        gameport.update(i, i1);

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
