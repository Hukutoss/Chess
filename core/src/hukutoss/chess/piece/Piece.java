package hukutoss.chess.piece;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import hukutoss.chess.util.Position;
import hukutoss.chess.util.Side;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {

    protected Sprite sprite;

    protected Side side;

    protected float x;
    protected float y;

    protected Position pos;

    protected List<Position> moves = new ArrayList<Position>();

    public void setPiecePos(Position pos)
    {
        this.pos = new Position(pos);
    }

    public void dragging(float x, float y)
    {
        float offset = 32;
        this.pos.setX((x - offset) / pos.TILE_SIZE);
        this.pos.setY((y - offset) / pos.TILE_SIZE);
    }

    public void render(SpriteBatch sb)
    {
        if(sprite != null)
        {
            float xOffset = sprite.getWidth() / 6;
            float yOffset = sprite.getHeight() / 6;

            sb.setColor(Color.WHITE);
            sb.draw(sprite, pos.getFloatX() + xOffset, pos.getFloatY() + yOffset);
        }
    }

    public abstract void legalMoves();

    public List<Position> getMoves() {
        return moves;
    }

    public Side getSide()
    {
        return side;
    }
}
