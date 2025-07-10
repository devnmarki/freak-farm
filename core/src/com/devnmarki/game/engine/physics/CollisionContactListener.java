package com.devnmarki.game.engine.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.devnmarki.game.engine.ecs.Entity;
import com.devnmarki.game.engine.ecs.IEntity;

public class CollisionContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        Entity entityA = (Entity) fa.getBody().getUserData();
        Entity entityB = (Entity) fb.getBody().getUserData();

        Vector2 normal = contact.getWorldManifold().getNormal();

        if (entityA != null) {
            entityA.collisionBegin((Entity) fb.getUserData(), new com.devnmarki.game.engine.math.Vector2(normal.x, normal.y), contact);
        }

        if (entityB != null) {
            normal.scl(-1);
            entityB.collisionBegin((Entity) fa.getUserData(), new com.devnmarki.game.engine.math.Vector2(normal.x, normal.y), contact);
        }
    }

    @Override
    public void endContact(Contact contact) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        Entity entityA = (Entity) fa.getBody().getUserData();
        Entity entityB = (Entity) fb.getBody().getUserData();

        if (entityA != null) {
            entityA.collisionEnd((Entity) fb.getUserData());
        }

        if (entityB != null) {
            entityB.collisionEnd((Entity) fa.getUserData());
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        Entity entityA = (Entity) fa.getBody().getUserData();
        Entity entityB = (Entity) fb.getBody().getUserData();

        if (entityA != null) {
            entityA.collisionPreSolve((Entity) fb.getUserData(), contact);
        }

        if (entityB != null) {
            entityB.collisionPreSolve((Entity) fa.getUserData(), contact);
        }
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {

    }

}
