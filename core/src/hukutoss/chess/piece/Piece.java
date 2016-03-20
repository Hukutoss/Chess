package hukutoss.chess.piece;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Piece {

    protected Sprite sprite;

    protected float x;
    protected float y;

    public void setPiecePos(float x, float y)
    {
        this.x = x + sprite.getWidth() / 6;
        this.y = y + sprite.getHeight() / 6;
    }

    public void setPos(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void render(SpriteBatch sb) {
        if(sprite != null) {
            sb.draw(sprite, x, y);
        }
    }
}
