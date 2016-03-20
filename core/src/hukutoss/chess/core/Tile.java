package hukutoss.chess.core;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import hukutoss.chess.piece.Piece;
import hukutoss.chess.util.TileType;

public class Tile {

    private float x;
    private float y;

    private TileType type;

    private Piece piece_data;

    private boolean selected;

    public Tile(float x, float y, TileType type) {
        this.x = x;
        this.y = y;
        this.type = type;

        this.piece_data = null;
    }

    public void render(SpriteBatch sb)
    {
        sb.setColor(Color.WHITE);
        if(selected)
        {
            sb.setColor(Color.LIME);
        }
        sb.draw(type.getSprite(), x, y, type.getSprite().getWidth(), type.getSprite().getHeight());
    }

    public boolean contains(float mouseX, float mouseY) {
        return mouseX > x && mouseX < x + type.getSprite().getWidth() &&
                mouseY > y && mouseY < y + type.getSprite().getHeight();
    }

    public boolean isEmpty() {
        return this.piece_data == null;
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

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
