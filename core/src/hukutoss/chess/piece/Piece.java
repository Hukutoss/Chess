package hukutoss.chess.piece;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import hukutoss.chess.core.Tile;
import hukutoss.chess.util.Pos;
import hukutoss.chess.util.Side;

public abstract class Piece {

    protected Sprite sprite;
    protected Side side;
    protected Pos pos;

    public Piece(float x, float y, Side side) {
        this(new Pos(x, y), side);
    }

    public Piece(Tile tile, Side side) {
        this(tile.getPos(), side);
    }

    public Piece(Pos pos, Side side) {
        this.side = side;
        this.pos = pos;
    }

    public void setPos(float x, float y) {
        this.pos.setX(x);
        this.pos.setY(y);
    }

    public void setPos(Pos pos) {
        this.pos = pos;
    }

    public void dndMove(float x, float y) {
        float offset = 32;
        this.setPos((x - offset) / Tile.TILE_SIZE, (y - offset) / Tile.TILE_SIZE);
    }

    public void render(SpriteBatch sb) {
        if (sprite != null) {
            float xOffset = sprite.getWidth() / 6;
            float yOffset = sprite.getHeight() / 6;

            sb.setColor(Color.WHITE);
            sb.draw(sprite, pos.getX() * Tile.TILE_SIZE + xOffset, pos.getY() * Tile.TILE_SIZE + yOffset);
        }
    }
}
