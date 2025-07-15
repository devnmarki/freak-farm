package com.devnmarki.game.sandbox.components.objects;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Filter;
import com.devnmarki.game.engine.animation.Animation;
import com.devnmarki.game.engine.animation.Animator;
import com.devnmarki.game.engine.ecs.Entity;
import com.devnmarki.game.engine.ecs.component.Component;
import com.devnmarki.game.engine.graphics.Spritesheet;
import com.devnmarki.game.engine.physics.BoxCollider;
import com.devnmarki.game.sandbox.Assets;
import com.devnmarki.game.sandbox.Constants;

public class Crate extends Component {

    private Animator anim;
    private BoxCollider collider;

    @Override
    public void onStart() {
        super.onStart();

        anim = entity.getComponent(Animator.class);
        collider = entity.getComponent(BoxCollider.class);

        anim.addAnimation("idle", new Animation(Assets.Spritesheets.CRATE_SHEET, new int[] { 0 }, 0.1f, true, false));
        anim.addAnimation("hit", new Animation(Assets.Spritesheets.CRATE_SHEET, new int[] { 1 }, 0.1f, false, false));
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        anim.play("idle");
    }

    @Override
    public void onPreCollision(Entity other, Contact contact) {
        super.onPreCollision(other, contact);
    }
}
