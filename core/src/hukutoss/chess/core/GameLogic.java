package hukutoss.chess.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import hukutoss.chess.ChessGame;
import hukutoss.chess.piece.Piece;

public class GameLogic {

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
            System.out.println("NEW GAME");
        }
    }

    private float startX;
    private float startY;

    private Piece temp_piece;
    private Tile temp_tile;

    private void inputHandler() {
        if (Gdx.input.justTouched()) {
            mouse.x = Gdx.input.getX();
            mouse.y = Gdx.input.getY();
            ChessGame.getCamera().unproject(mouse);

            this.click(mouse.x, mouse.y);

            startX = mouse.x;
            startY = mouse.y;
        }
        if (Gdx.input.isTouched()) {
            mouse.x = Gdx.input.getX();
            mouse.y = Gdx.input.getY();
            ChessGame.getCamera().unproject(mouse);

            float dist = 12;

            if (mouse.x > startX + dist || mouse.x < startX - dist ||
                    mouse.y > startY + dist || mouse.y < startY - dist) {
                int pad = 4;
                if (mouse.x < 0)
                    mouse.x = pad;
                if (mouse.y < 0)
                    mouse.y = pad;
                if (mouse.x > Gdx.graphics.getWidth())
                    mouse.x = Gdx.graphics.getWidth() - pad;
                if (mouse.y > Gdx.graphics.getHeight())
                    mouse.y = Gdx.graphics.getHeight() - pad;

                dragAndDrop(mouse.x, mouse.y, false);
            }
        } else {
            dragAndDrop(mouse.x, mouse.y, true);
        }
    }

    private void dragAndDrop(float mouseX, float mouseY, boolean isDropping) {
        if (temp_piece == null) {
            return;
        }

        temp_piece.dndMove(mouseX, mouseY);

        if (isDropping) {
            for (int x = 0; x < ChessBoard.BOARD_SIZE; x++)
                for (int y = 0; y < ChessBoard.BOARD_SIZE; y++) {
                    if (board.getGrid()[x][y].mouseContains(mouseX, mouseY)) {
                        Tile tile = board.getGrid()[x][y];
                        dropPiece(tile, true);
                    }
                }
        }
    }

    private void click(float mouseX, float mouseY) {
        for (int x = 0; x < ChessBoard.BOARD_SIZE; x++)
            for (int y = 0; y < ChessBoard.BOARD_SIZE; y++) {
                if (board.getGrid()[x][y].mouseContains(mouseX, mouseY)) {
                    Tile tile = board.getGrid()[x][y];
                    if (temp_piece == null && !tile.isEmpty()) {
                        temp_piece = tile.getPiece();
//                        legalMoves = temp_piece.getMoves();

                        temp_tile = tile;
                        temp_tile.setSelected(true);
                    } else if (temp_piece != null) {
                        dropPiece(tile, false);
                    }
                }
            }
    }

    private void dropPiece(Tile tile, boolean isDrag) {
        if (tile.isEmpty()) {
//            for (Pos p : legalMoves) {
//                if (tile.getPos().getX() == p.getX() && tile.getPos().getY() == p.getY()) {
//                    tile.addPiece(temp_piece);
//                    resetTemp(tile);
//                    return;
//                } else {
//                    temp_tile.addPiece(temp_piece);
//                    temp_piece.setPiecePos(temp_tile.getPos());
//                }
//            }
            temp_tile.setSelected(false);
            temp_tile = null;
            temp_piece = null;
        } else {
//            boolean sameSide = tile.getPiece().getSide().equals(temp_piece.getSide());

//            if (!sameSide) {
//                for (Pos p : legalMoves) {
//                    if (tile.getPos().getX() == p.getX() && tile.getPos().getY() == p.getY()) {
//                        tile.addPiece(temp_piece);
//                        resetTemp(tile);
//                        return;
//                    } else {
//                        temp_tile.addPiece(temp_piece);
//                        temp_piece.setPiecePos(temp_tile.getPos());
//                    }
//                }
//            } else {
//                if (isDrag) {
//                    temp_piece.setPiecePos(temp_tile.getPos());
//                } else {
//                    temp_tile.setSelected(false);
//
//                    temp_piece = tile.getPiece();
//                    legalMoves = temp_piece.getMoves();
//
//                    temp_tile = tile;
//                    temp_tile.setSelected(true);
//                }
//            }
        }
    }

    private void resetTemp(Tile tile) {
        if (temp_piece != null && temp_tile != null) {
            temp_piece.setPos(tile.getPos());
            temp_piece = null;

            temp_tile.setSelected(false);
            temp_tile.addPiece(null);
            temp_tile = null;
        }
    }
}
