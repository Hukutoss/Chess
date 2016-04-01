package hukutoss.chess.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GameLogic {

    private ChessBoard board;

    public GameLogic() {
        board = new ChessBoard();
    }

    public void render(SpriteBatch sb, ShapeRenderer sr) {
        debug();
        board.render(sb, sr);
    }

    public void debug() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.C)) {
            board = new ChessBoard();
            System.out.println("NEW GAME");
        }
    }
}
