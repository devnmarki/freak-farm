package com.devnmarki.game.engine.ecs;

import com.badlogic.gdx.physics.box2d.Contact;
import com.devnmarki.game.engine.math.Vector2;

public interface IEntity {

    void collisionBegin(Entity other, Vector2 normal, Contact contact);
    void collisionEnd(Entity other);
    void collisionPreSolve(Entity other, Contact contact);

}
