package hukutoss.chess.core;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import hukutoss.chess.piece.Piece;
import hukutoss.chess.util.Pos;

public class Tile {

    public static final int TILE_SIZE = 64;

    private Piece piece;
    private Pos pos;
    private Sprite sprite;

    private boolean selected;

    public Tile(int x, int y, Sprite sprite) {
        this.pos = new Pos(x, y);
        this.sprite = sprite;
    }

    public void render(SpriteBatch sb) {
        sb.setColor(Color.WHITE);
        if (selected) {
            sb.setColor(Color.LIME);
        }
        sb.draw(this.sprite, pos.getX() * TILE_SIZE, pos.getY() * TILE_SIZE, this.sprite.getWidth(), this.sprite.getHeight());
    }

//    public void renderMoves(SpriteBatch sb, ShapeRenderer sr) {
//        sb.end();
//
//        sr.setColor(new Color(0.0f, 1.0f, 0.0f, 0.7f));
//        sr.begin(ShapeRenderer.ShapeType.Filled);
//        sr.circle(pos.getX() + (this.sprite.getWidth() / 2), pos.getY() + (this.sprite.getHeight() / 2), 12);
//        sr.end();
//
//        sb.begin();
//    }

    public boolean mouseContains(float mouseX, float mouseY) {
        float x = this.pos.getX() * TILE_SIZE;
        float y = this.pos.getY() * TILE_SIZE;
        return mouseX > x && mouseX < x + 1 + this.sprite.getWidth() &&
                mouseY > y && mouseY < y + 1 + this.sprite.getHeight(); // why +1 tho?
    }

    public boolean isEmpty() {
        return this.piece == null;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Pos getPos() {
        return pos;
    }
}
