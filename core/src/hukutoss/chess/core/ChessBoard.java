package hukutoss.chess.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import hukutoss.chess.ChessGame;
import hukutoss.chess.piece.Pawn;
import hukutoss.chess.piece.Piece;
import hukutoss.chess.util.TileType;
import hukutoss.chess.util.Side;

public class ChessBoard {

    private static final int SIZE = 8;
    private static final int CELL_SIZE = 64;

    private Tile[][] grid;

    private Vector3 mouse;

    public ChessBoard() {
        grid = new Tile[SIZE][SIZE];

        mouse = new Vector3();

        //x - letter, y - number
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                grid[x][y] = new Tile(
                        x * CELL_SIZE,
                        y * CELL_SIZE,
                        x % 2 == 0 && y % 2 == 0 || x % 2 != 0 && y % 2 != 0 ? TileType.BLACK : TileType.WHITE);
            }
        }

        grid[0][0].setPiece_data(new Pawn(grid[0][0].getX(), grid[0][0].getY(), Side.BLACK));
    }

    public void render(SpriteBatch sb)
    {
        update();

        sb.setColor(Color.WHITE);
        for (int x = 0; x < SIZE; x++)
            for (int y = 0; y < SIZE; y++)
            {
                grid[x][y].render(sb);
                if(grid[x][y].getPiece_data() != null) {
                    grid[x][y].getPiece_data().render(sb);
                }
                if(temp_piece != null) {
                    temp_piece.render(sb);
                }
            }
    }

    private void update() {
        inputHandler();
    }

    private float startX;
    private float startY;
    private float dist = 12;

    private Piece temp_piece;
    private Tile temp_cell;

    //TODO: Move to the player (when he will be exist)
    private void inputHandler()
    {
        if(Gdx.input.justTouched())
        {
            mouse.x = Gdx.input.getX();
            mouse.y = Gdx.input.getY();

            //TODO: Need to do something with it..
            ChessGame.getCamera().unproject(mouse);

            this.click(mouse.x, mouse.y);

            startX = mouse.x;
            startY = mouse.y;
        }
        if(Gdx.input.isTouched()) {
            mouse.x = Gdx.input.getX();
            mouse.y = Gdx.input.getY();

            ChessGame.getCamera().unproject(mouse);

            if (mouse.x > startX + dist || mouse.x < startX - dist || mouse.y > startY + dist || mouse.y < startY - dist) {
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

    private void dragAndDrop(float mouseX, float mouseY, boolean drop) {
        if (temp_piece == null) {
            return;
        }

        temp_piece.setPos(mouseX - 24, mouseY - 24);

        if(drop) {
            for (int x = 0; x < SIZE; x++)
                for (int y = 0; y < SIZE; y++) {
                    if (grid[x][y].contains(mouseX, mouseY)) {
                        Tile tile = grid[x][y];
                        if (tile.isEmpty()) {
                            tile.setPiece_data(temp_piece);

                            temp_piece.setPiecePos(tile.getX(), tile.getY());
                            temp_piece = null;

                            temp_cell.setSelected(false);
                            temp_cell.setPiece_data(null);
                            temp_cell = null;
                        }
                        else
                        {
                            temp_piece.setPiecePos(temp_cell.getX(), temp_cell.getY());
                        }
                    }
                }
        }
    }

    private void click(float mouseX, float mouseY)
    {
        for (int x = 0; x < SIZE; x++)
            for (int y = 0; y < SIZE; y++)
            {
                if(grid[x][y].contains(mouseX, mouseY))
                {
                    Tile tile = grid[x][y];
                    if(temp_piece == null && !tile.isEmpty())
                    {
                        tile.setSelected(true);

                        temp_piece = tile.getPiece_data();
                        temp_cell = tile;
                    }
                    else if(temp_piece != null && tile.isEmpty())
                    {
                        tile.setPiece_data(temp_piece);

                        temp_piece.setPiecePos(tile.getX(), tile.getY());
                        temp_piece = null;

                        temp_cell.setPiece_data(null);
                        temp_cell.setSelected(false);
                        temp_cell = null;
                    }
                }
        }
    }
}
