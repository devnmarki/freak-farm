package com.devnmarki.game.engine.math;

public class Vector2 {

    public float x;
    public float y;

    public static final Vector2 ZERO = new Vector2(0f, 0f);
    public static final Vector2 ONE = new Vector2(1f, 1f);
    public static final Vector2 UNIT_X = new Vector2(1f, 0f);
    public static final Vector2 UNIT_Y = new Vector2(0f, 1f);

    public Vector2() {
        this.x = 0f;
        this.y = 0f;
    }

    public Vector2(float value) {
        this.x = value;
        this.y = value;
    }

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(Vector2 v) {
        set(v);
    }

    public Vector2 set(Vector2 v) {
        this.x = v.x;
        this.y = v.y;

        return this;
    }

    public Vector2 cpy() {
        return new Vector2(this);
    }

    public Vector2 add(Vector2 v) {
        this.x += v.x;
        this.y += v.y;
        return this;
    }

    public Vector2 add(float v) {
        this.x += v;
        this.y += v;
        return this;
    }

    public Vector2 sub(Vector2 v) {
        this.x -= v.x;
        this.y -= v.y;
        return this;
    }

    public Vector2 sub(float v) {
        this.x -= v;
        this.y -= v;
        return this;
    }

    public Vector2 mul(Vector2 v) {
        this.x *= v.x;
        this.y *= v.y;
        return this;
    }

    public Vector2 mul(float v) {
        this.x *= v;
        this.y *= v;
        return this;
    }

    public Vector2 div(Vector2 v) {
        this.x /= v.x;
        this.y /= v.y;
        return this;
    }

    public Vector2 div(float v) {
        this.x /= v;
        this.y /= v;
        return this;
    }

}
