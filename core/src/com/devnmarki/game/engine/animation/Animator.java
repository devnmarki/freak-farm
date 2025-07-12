package com.devnmarki.game.engine.animation;

import com.devnmarki.game.engine.ecs.component.Component;
import com.devnmarki.game.engine.graphics.SpriteRenderer;

import java.util.HashMap;
import java.util.Map;

public class Animator extends Component {

    private Map<String, Animation> animations = new HashMap<String, Animation>();
    private Animation currentAnimation;

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (currentAnimation != null) {
            currentAnimation.update();

            int frameIndex = currentAnimation.getCurrentFrame();
            currentAnimation.getSpritesheet().getSprite(frameIndex).setFlip(currentAnimation.isFlip());

            if (entity.hasComponent(SpriteRenderer.class)) {
                entity.getComponent(SpriteRenderer.class).sprite = currentAnimation.getSpritesheet().getSprite(frameIndex);
            }
        }
    }

    public void addAnimation(String animationName, Animation animation) {
        animations.put(animationName, animation);
    }

    public void play(String animationName) {
        Animation newAnimation = animations.get(animationName);
        if (newAnimation != null && currentAnimation != newAnimation) {
            currentAnimation = newAnimation;
            currentAnimation.reset();
        }
    }

    public boolean isCurrentAnimationFinished() {
        return currentAnimation != null && currentAnimation.hasFinished();
    }

    public Animation getCurrentAnimation() {
        return currentAnimation;
    }

}
