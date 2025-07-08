package com.devnmarki.game.engine.graphics;

import com.badlogic.gdx.graphics.Color;
import com.devnmarki.game.engine.Engine;
import com.devnmarki.game.engine.ecs.Component;

public class SpriteRenderer extends Component {

    public Sprite sprite;
    public Color color;

    public SpriteRenderer(Sprite sprite, Color color) {
        this.sprite = sprite;
        this.color = color;
    }

    public SpriteRenderer(Sprite sprite) {
        this(sprite, Color.WHITE);
    }

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
                0f, 0f,
                entity.getTransform().size.x, entity.getTransform().size.y,
                Engine.scale, Engine.scale,
                entity.getTransform().rotation
        );
    }

}
