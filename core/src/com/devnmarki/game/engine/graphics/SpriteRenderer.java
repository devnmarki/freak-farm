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
                sprite.getTexture().getTexture(),
                entity.getTransform().position.x, entity.getTransform().position.y,
                entity.getTransform().size.x * Engine.scale, entity.getTransform().size.y * Engine.scale,
                0, 0,
                sprite.getTexture().getRegionWidth(), sprite.getTexture().getRegionHeight(),
                sprite.isFlip(), false
        );
    }

}
