package hukutoss.chess.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameLogic {

    private ChessBoard board;

    public GameLogic() {
        board = new ChessBoard();
    }

    public void render(SpriteBatch sb) {
        debug();
        board.render(sb);
    }

    public void debug() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.C)) {
            board = new ChessBoard();
            System.out.println("NEW GAME");
        }
    }
}
