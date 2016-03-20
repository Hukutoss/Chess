package hukutoss.chess.piece;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import hukutoss.chess.core.Textures;

public class Piece {

    private Sprite sprite;

    private float x;
    private float y;

    //Just for test
    public Piece(float x, float y) {
        this.sprite = Textures.white_pawn;

        this.x = x + sprite.getWidth() / 6;
        this.y = y + sprite.getHeight() / 6;

        sp = new ShapeRenderer();
    }

    public void setPiecePos(float x, float y) {
        this.x = x + sprite.getWidth() / 6;
        this.y = y + sprite.getHeight() / 6;
    }

    //TODO: Delete
    ShapeRenderer sp;

    public void render(SpriteBatch sb) {

        sb.draw(sprite, x, y);
        sb.end();

        sp.begin(ShapeRenderer.ShapeType.Line);
        sp.setColor(1, 0, 0, 1);
        sp.line(x, y, x + sprite.getWidth(), y + sprite.getHeight());
        sp.line(x + sprite.getWidth(), y, x, y +  sprite.getHeight() );
        sp.end();

        sb.begin();
    }
}
