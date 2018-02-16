package hukutoss.chess.core;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import hukutoss.chess.piece.Piece;
import hukutoss.chess.util.Lib;
import hukutoss.chess.util.Logger;
import hukutoss.chess.util.Pos;

public class Tile {

    private Logger logger = Logger.getLogger(Tile.class);

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
        sb.draw(this.sprite, pos.getX() * Lib.TILE_SIZE, pos.getY() * Lib.TILE_SIZE, this.sprite.getWidth(), this.sprite.getHeight());
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
        float x = this.pos.getX() * Lib.TILE_SIZE;
        float y = this.pos.getY() * Lib.TILE_SIZE;
        return mouseX > x && mouseX < x + 1 + this.sprite.getWidth() &&
                mouseY > y && mouseY < y + 1 + this.sprite.getHeight(); // why +1 tho?
    }

    public void addPiece(Piece piece) {
            this.piece = piece;
            this.piece.setPos(this.pos);
    }

    public void removePiece() {
        this.piece = null;
    }

    public Piece getPiece() {
        return this.piece;
    }

    public boolean isEmpty() {
        return this.piece == null;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Pos getPos() {
        return pos;
    }
}
