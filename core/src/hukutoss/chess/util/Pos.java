package hukutoss.chess.util;

import hukutoss.chess.core.Tile;

public class Pos {

    private float x;
    private float y;

    public Pos(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
}
