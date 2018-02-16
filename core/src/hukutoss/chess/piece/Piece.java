package hukutoss.chess.piece;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import hukutoss.chess.util.Lib;
import hukutoss.chess.util.Pos;
import hukutoss.chess.util.Side;

public abstract class Piece {

    protected Sprite sprite;
    protected Side side;
    protected Pos pos;

    public Piece(Side side) {
        this.side = side;
    }

    public void setPos(float x, float y) {
        this.pos.setX(x);
        this.pos.setY(y);
    }

    public void setPos(Pos pos) {
        this.pos = new Pos(pos);
    }

    public void render(SpriteBatch sb) {
        if (sprite != null) {
            float xOffset = sprite.getWidth() / 6;
            float yOffset = sprite.getHeight() / 6;

            sb.setColor(Color.WHITE);
            sb.draw(sprite, pos.getX() * Lib.TILE_SIZE + xOffset, pos.getY() * Lib.TILE_SIZE + yOffset);
        }
    }
}
