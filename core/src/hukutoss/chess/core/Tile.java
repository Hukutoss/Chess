package hukutoss.chess.core;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import hukutoss.chess.piece.Piece;
import hukutoss.chess.util.Position;
import hukutoss.chess.util.TileType;

public class Tile {

    private TileType type;

    private Piece piece_data;

    private Position pos;

    private boolean selected;

    public Tile(Position pos, TileType type)
    {
        this.pos = pos;
        this.type = type;
    }

    public void render(SpriteBatch sb)
    {
        sb.setColor(Color.WHITE);
        if(selected)
        {
            sb.setColor(Color.LIME);
        }
        sb.draw(type.getSprite(), pos.getFloatX(), pos.getFloatY(), type.getSprite().getWidth(), type.getSprite().getHeight());
    }

    public void renderMoves(SpriteBatch sb, ShapeRenderer sr) {
        sb.end();

        sr.setColor(new Color(0.0f, 1.0f, 0.0f, 0.7f));
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.circle(pos.getFloatX() + (type.getSprite().getWidth() / 2), pos.getFloatY() + (type.getSprite().getHeight() / 2), 12);
        sr.end();

        sb.begin();
    }

    public boolean contains(float mouseX, float mouseY)
    {
        return mouseX > this.pos.getFloatX() && mouseX < this.pos.getFloatX() + 1 + type.getSprite().getWidth() &&
                mouseY > this.pos.getFloatY() && mouseY < this.pos.getFloatY() + 1 + type.getSprite().getHeight();
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

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Position getPos() {
        return pos;
    }
}
