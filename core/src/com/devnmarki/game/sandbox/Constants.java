package com.devnmarki.game.sandbox;

import com.devnmarki.game.engine.physics.LayerCollision;

public class Constants {

    public static class Collision {

        public static final short CATEGORY_PLAYER = 0x0001;
        public static final short CATEGORY_OBJECT  = 0x0002;

        public static final short MASK_PLAYER = CATEGORY_PLAYER | CATEGORY_OBJECT; // Player collides with crates
        public static final short MASK_OBJECT = 0x0004; // Crates collide with something else, not players

    }

    public static class CollisionLayers {
        public static final int PLAYER = 1;
        public static final int OBJECT = 2;
    }

}
