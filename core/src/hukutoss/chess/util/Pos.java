package hukutoss.chess.util;

public class Pos {

    private float x;
    private float y;

    public Pos(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Pos(Pos pos) {
        this.x = pos.getX();
        this.y = pos.getY();
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
