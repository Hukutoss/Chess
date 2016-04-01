package hukutoss.chess.util;

public class Position {

    private float x;
    private float y;

    public static final int TILE_SIZE = 64;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(Position pos) {
        this.x = pos.getX();
        this.y = pos.getY();
    }

    public int getX() {
        return (int)x;
    }

    public int getY() {
        return (int)y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getFloatX() {
        return x * TILE_SIZE;
    }

    public float getFloatY() {
        return y * TILE_SIZE;
    }
}
