package com.devnmarki.game.engine.graphics;

import com.devnmarki.game.engine.Engine;
import com.devnmarki.game.engine.ecs.Component;

public class SpriteRenderer extends Component {

    public Sprite sprite;

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (entity == null || sprite == null) return;

        Engine.SPRITE_BATCH.draw(
                sprite.getTexture(),
                entity.getTransform().position.x, entity.getTransform().position.y,
                0, 0,
                sprite.getTexture().getRegionWidth(), sprite.getTexture().getRegionHeight(),
                Engine.scale, Engine.scale,
                entity.getTransform().rotation - 90f,
                false
        );
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

}
