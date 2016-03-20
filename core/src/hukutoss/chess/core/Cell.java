package hukutoss.chess.core;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import hukutoss.chess.piece.Piece;

public class Cell {

    private float x;
    private float y;

    private CellType type;

    private Piece piece_data;

    public Cell(float x, float y, CellType type) {
        this.x = x;
        this.y = y;
        this.type = type;

        this.piece_data = null;
    }

    public void render(SpriteBatch sb) {
        sb.draw(type.getSprite(), x, y, type.getSprite().getWidth(), type.getSprite().getHeight());
    }

    public boolean contains(float mouseX, float mouseY) {
        return mouseX > x && mouseX < x + type.getSprite().getWidth() &&
                mouseY > y && mouseY < y + type.getSprite().getHeight();
    }

    public Piece getPiece_data() {
        return piece_data;
    }

    public void setPiece_data(Piece piece_data) {
        this.piece_data = piece_data;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public enum CellType {
        BLACK(Textures.black_cell),
        WHITE(Textures.white_cell);

        private Sprite sprite;

        CellType(Sprite sprite) {
            this.sprite = sprite;
        }

        public Sprite getSprite() {
            return sprite;
        }
    }
}
