package hukutoss.chess.core;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ChessBoard {

    private static final int SIZE = 8;
    private static final int CELL_SIZE = 64;

    private Cell[][] grid;

    public ChessBoard() {
        grid = new Cell[SIZE][SIZE];

        //x - letter, y - number
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                grid[x][y] = new Cell(
                        x * CELL_SIZE,
                        y * CELL_SIZE,
                        x % 2 == 0 &&
                                y % 2 == 0 ||
                                x % 2 != 0 &&
                                        y % 2 != 0 ? Cell.CellType.BLACK : Cell.CellType.WHITE);
            }
        }
    }

    public void render(SpriteBatch sb) {
        sb.setColor(Color.WHITE);
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                grid[x][y].render(sb);
            }
        }
    }
}
