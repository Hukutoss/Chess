package hukutoss.chess.piece;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import hukutoss.chess.util.Side;

public abstract class Piece {

    protected Sprite sprite;

    protected Side side;

    protected float x;
    protected float y;

    public void setPiecePos(float x, float y)
    {
        this.x = x + sprite.getWidth() / 6;
        this.y = y + sprite.getHeight() / 6;
    }

    public void setPos(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public void render(SpriteBatch sb)
    {
        sb.setColor(Color.WHITE);
        if(sprite != null) {
            sb.draw(sprite, x, y);
        }
    }

    public Side getSide()
    {
        return side;
    }
}
