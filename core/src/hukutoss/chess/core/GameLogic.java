package hukutoss.chess.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameLogic {

    private ChessBoard board;

    public GameLogic() {
        board = new ChessBoard();
    }

    public void render(SpriteBatch sb) {
        board.render(sb);
    }
}
