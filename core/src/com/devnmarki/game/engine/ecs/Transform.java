package com.devnmarki.game.engine.ecs;

import com.devnmarki.game.engine.ecs.component.Component;
import com.devnmarki.game.engine.math.Vector2;

public class Transform extends Component {

    public Vector2 position = new Vector2();
    public Vector2 size = new Vector2();
    public float rotation = 0f;

}
