package hukutoss.chess.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import hukutoss.chess.Game;

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
                        x % 2 == 0 && y % 2 == 0 || x % 2 != 0 && y % 2 != 0 ? Cell.CellType.BLACK : Cell.CellType.WHITE);
            }
        }
    }

    public void render(SpriteBatch sb) {
        update();

        sb.setColor(Color.WHITE);
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                grid[x][y].render(sb);
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
            Game.getCamera().unproject(mouse);

            this.click(mouse.x, mouse.y);
        }
    }

    private void click(float mouseX, float mouseY) {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if(grid[x][y].contains(mouseX, mouseY)) {
                    System.out.println("CELL: " + x + ", " + y);
                }
            }
        }
    }
}
