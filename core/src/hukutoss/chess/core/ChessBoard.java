package hukutoss.chess.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Constructor;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import hukutoss.chess.ChessGame;
import hukutoss.chess.piece.Piece;
import hukutoss.chess.util.EnumPiece;
import hukutoss.chess.util.Side;
import hukutoss.chess.util.TileType;

public class ChessBoard {

    private static final int SIZE = 8;
    private static final int CELL_SIZE = 64;

    private Tile[][] grid;

    private Vector3 mouse;

    public ChessBoard() {
        grid = new Tile[SIZE][SIZE];

        mouse = new Vector3();

        //x - letter, y - number
        for (int x = 0; x < SIZE; x++)
            for (int y = 0; y < SIZE; y++)
            {
                grid[x][y] = new Tile(
                        x * CELL_SIZE,
                        y * CELL_SIZE,
                        x % 2 == 0 && y % 2 == 0 || x % 2 != 0 && y % 2 != 0 ? TileType.WHITE : TileType.BLACK);
            }

        initGame();
    }

    // lower case - white
    // upper case - black
    // "rnbqkbnr/pppppppp/8/8/8/8//PPPPPPPP/RNBQKBNR
    public static final String START_POS = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";

    private void initGame()
    {
        String[] tmp = START_POS.split("/");

        for (int i = 0; i < tmp.length; i++)
            for (int j = 0; j < tmp[i].length(); j++)
            {
                char t = tmp[i].substring(j, j + 1).charAt(0);
                Side color = Character.isLowerCase(t) ? Side.WHITE : t == '8' ? null : Side.BLACK;
                EnumPiece piece;
                switch (tmp[i].substring(j, j + 1).charAt(0))
                {
                    case 'r':
                    case 'R':
                        piece = EnumPiece.Rook;
                        break;
                    case 'n':
                    case 'N':
                        piece = EnumPiece.Knight;
                        break;
                    case 'b':
                    case 'B':
                        piece = EnumPiece.Bishop;
                        break;
                    case 'q':
                    case 'Q':
                        piece = EnumPiece.Queen;
                        break;
                    case 'k':
                    case 'K':
                        piece = EnumPiece.King;
                        break;
                    case 'p':
                    case 'P':
                        piece = EnumPiece.Pawn;
                        break;
                    default:
                        piece = null;
                }
                createPiece(color, piece, j, i);
            }
    }

    //TODO: Rewrite this shit ( ^◡^)っ✂╰⋃╯
    private void createPiece(Side color, EnumPiece type, int x, int y) {
        if (type != null && color != null) {
            try {
                Tile tile = grid[x][y];
                Constructor[] cons = ClassReflection
                        .getConstructors(ClassReflection.forName("hukutoss.chess.piece." + type.name()));
                tile.setPiece_data((Piece) cons[0].newInstance(tile.getX(), tile.getY(), color));
            } catch (ReflectionException e) {
                e.printStackTrace();
            }
        }
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
        if(Gdx.input.isTouched())
        {
            mouse.x = Gdx.input.getX();
            mouse.y = Gdx.input.getY();

            ChessGame.getCamera().unproject(mouse);

            if (    mouse.x > startX + dist || mouse.x < startX - dist ||
                    mouse.y > startY + dist || mouse.y < startY - dist)
            {
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
        }
        else
        {
            dragAndDrop(mouse.x, mouse.y, true);
        }
    }

    private void dragAndDrop(float mouseX, float mouseY, boolean drop)
    {
        if (temp_piece == null) {
            return;
        }

        temp_piece.setPos(mouseX - 24, mouseY - 24);

        if(drop)
        {
            for (int x = 0; x < SIZE; x++)
                for (int y = 0; y < SIZE; y++)
                {
                    if (grid[x][y].contains(mouseX, mouseY))
                    {
                        Tile tile = grid[x][y];
                        if (tile.isEmpty())
                        {
                            tile.setPiece_data(temp_piece);
                            resetTemp(tile);
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

                        resetTemp(tile);
                    }
                }
        }
    }

    private void resetTemp(Tile tile)
    {
        temp_piece.setPiecePos(tile.getX(), tile.getY());
        temp_piece = null;

        temp_cell.setSelected(false);
        temp_cell.setPiece_data(null);
        temp_cell = null;
    }
}
