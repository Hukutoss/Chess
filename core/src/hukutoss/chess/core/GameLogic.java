package hukutoss.chess.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import hukutoss.chess.ChessGame;
import hukutoss.chess.piece.Piece;
import hukutoss.chess.util.Lib;
import hukutoss.chess.util.Logger;

public class GameLogic {

    private Logger logger = Logger.getLogger(GameLogic.class);

    private ChessBoard board;
    private Vector3 mouse;

    public GameLogic() {
        mouse = new Vector3();
        board = new ChessBoard();
    }

    public void render(SpriteBatch sb, ShapeRenderer sr) {
        debug();
        inputHandler(); //TODO: Change to update
        board.render(sb, sr);
    }

    public void debug() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.C)) {
            board = new ChessBoard();
            logger.info("New Game");
        }
    }

    Piece t_piece;
    Tile t_tile;

    private void inputHandler() {
        if (Gdx.input.justTouched()) {
            mouse.x = Gdx.input.getX();
            mouse.y = Gdx.input.getY();
            ChessGame.getCamera().unproject(mouse);

//            this.click(mouse.x, mouse.y);
            for (int x = 0; x < Lib.BOARD_SIZE; x++) {
                for (int y = 0; y < Lib.BOARD_SIZE; y++) {
                    if (board.getGrid()[x][y].mouseContains(mouse.x, mouse.y)) {
                        Tile tile = board.getGrid()[x][y];

                        if (!tile.isEmpty()) {
                            if (t_piece == null) {
                                t_piece = tile.getPiece();
                                tile.setSelected(true);
                                t_tile = tile;
                                break;
                            }
                        } else {
                            if (t_piece != null) {
                                tile.addPiece(t_piece);
                                t_tile.removePiece();
                                t_tile.setSelected(false);
                                t_piece = null;
                                t_tile = null;
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}
//        if (Gdx.input.isTouched()) {
//            mouse.x = Gdx.input.getX();
//            mouse.y = Gdx.input.getY();
//            ChessGame.getCamera().unproject(mouse);
//
//            float dist = 12;
//
//            if (mouse.x > startX + dist || mouse.x < startX - dist ||
//                    mouse.y > startY + dist || mouse.y < startY - dist) {
//                int pad = 4;
//                if (mouse.x < 0)
//                    mouse.x = pad;
//                if (mouse.y < 0)
//                    mouse.y = pad;
//                if (mouse.x > Gdx.graphics.getWidth())
//                    mouse.x = Gdx.graphics.getWidth() - pad;
//                if (mouse.y > Gdx.graphics.getHeight())
//                    mouse.y = Gdx.graphics.getHeight() - pad;
//
//                dragAndDrop(mouse.x, mouse.y, false);
//            }
//        } else {
//            dragAndDrop(mouse.x, mouse.y, true);
//        }


