package com.devnmarki.game.engine.ecs;

import com.devnmarki.game.engine.math.Vector2;

public class Transform extends Component {

    public Vector2 position;
    public Vector2 size;
    public float rotation;

    public Transform() {
        this.position = Vector2.ZERO;
        this.size = Vector2.ONE;
        this.rotation = 0f;
    }

}
