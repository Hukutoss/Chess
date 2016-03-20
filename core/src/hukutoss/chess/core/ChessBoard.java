package hukutoss.chess.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import hukutoss.chess.ChessGame;
import hukutoss.chess.piece.Pawn;
import hukutoss.chess.piece.Piece;
import hukutoss.chess.util.CellType;
import hukutoss.chess.util.Side;

public class ChessBoard {

    private static final int SIZE = 8;
    private static final int CELL_SIZE = 64;

    private Cell[][] grid;

    private Vector3 mouse;

    public ChessBoard() {
        grid = new Cell[SIZE][SIZE];

        mouse = new Vector3();

        //x - letter, y - number
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                grid[x][y] = new Cell(
                        x * CELL_SIZE,
                        y * CELL_SIZE,
                        x % 2 == 0 && y % 2 == 0 || x % 2 != 0 && y % 2 != 0 ? CellType.BLACK : CellType.WHITE);
            }
        }

        grid[0][0].setPiece_data(new Pawn(grid[0][0].getX(), grid[0][0].getY(), Side.BLACK));


    }

    public void render(SpriteBatch sb) {
        update();

        sb.setColor(Color.WHITE);
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                grid[x][y].render(sb);
                if(grid[x][y].getPiece_data() != null)
                    grid[x][y].getPiece_data().render(sb);
            }
        }
    }

    private void update() {
        inputHandler();
    }

    //TODO: Move to the player (when he will be exist)
    private void inputHandler() {
        if(Gdx.input.justTouched()) {
            mouse.x = Gdx.input.getX();
            mouse.y = Gdx.input.getY();

            //TODO: Need to do something with it..
            ChessGame.getCamera().unproject(mouse);

            this.click(mouse.x, mouse.y);
        }
    }

    private Piece temp_piece;
    private Cell temp_cell;

    private void click(float mouseX, float mouseY) {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if(grid[x][y].contains(mouseX, mouseY)) {
                    Cell tile = grid[x][y];
                    if(tile.getPiece_data() != null) {
                        if (temp_piece == null) {
                            temp_piece = grid[x][y].getPiece_data();
                            temp_cell = tile;
                        }
                    }
                    else {
                        if(tile.getPiece_data() == null) {
                            tile.setPiece_data(temp_piece);
                            tile.getPiece_data().setPiecePos(tile.getX(), tile.getY());
                            temp_piece = null;
                            temp_cell = null;
                        }
                    }
                }
            }
        }
    }
}
