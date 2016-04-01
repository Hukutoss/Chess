package hukutoss.chess.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Constructor;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import hukutoss.chess.ChessGame;
import hukutoss.chess.piece.Piece;
import hukutoss.chess.util.EnumPiece;
import hukutoss.chess.util.Position;
import hukutoss.chess.util.Side;
import hukutoss.chess.util.TileType;

import java.util.List;

public class ChessBoard {

    private static final int BOARD_SIZE = 8;

    private Tile[][] grid;

    private Vector3 mouse;

    public ChessBoard() {
        grid = new Tile[BOARD_SIZE][BOARD_SIZE];

        mouse = new Vector3();

        //x - letter, y - number
        for (int x = 0; x < BOARD_SIZE; x++)
            for (int y = 0; y < BOARD_SIZE; y++)
            {
                grid[x][y] = new Tile(new Position(x, y),
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

        for (int y = 0; y < tmp.length; y++)
            for (int x = 0; x < tmp[y].length(); x++)
            {
                char t = tmp[y].substring(x, x + 1).charAt(0);
                Side color = Character.isLowerCase(t) ? Side.WHITE : t == '8' ? null : Side.BLACK;
                EnumPiece piece;
                switch (tmp[y].substring(x, x + 1).charAt(0))
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
                createPiece(color, piece, x, y);
            }
    }

    //TODO: Rewrite this shit ( ^◡^)っ✂╰⋃╯
    private void createPiece(Side color, EnumPiece type, int x, int y) {
        if (type != null && color != null) {
            try {
                Constructor[] cons = ClassReflection
                        .getConstructors(ClassReflection.forName("hukutoss.chess.piece." + type.name()));
                grid[x][y].setPiece_data((Piece) cons[0].newInstance(new Position(x, y), color));
            } catch (ReflectionException e) {
                e.printStackTrace();
            }
        }
    }

    private Tile getTile(Position pos) {
        if(pos.getX() > -1 && pos.getX() < BOARD_SIZE && pos.getY() > -1 && pos.getY() < BOARD_SIZE)
            return grid[pos.getX()][pos.getY()];
        return null;
    }

    public void render(SpriteBatch sb, ShapeRenderer sr)
    {
        update();

        sb.setColor(Color.WHITE);
        for (int x = 0; x < BOARD_SIZE; x++)
            for (int y = 0; y < BOARD_SIZE; y++)
            {
                grid[x][y].render(sb);
                if(grid[x][y].getPiece_data() != null)
                {
                    grid[x][y].getPiece_data().render(sb);
                }
            }

        if(temp_piece != null && legalMoves != null)
        {
            for(Position p : legalMoves)
            {
                Tile tile = getTile(p);
                if(tile != null && tile.isEmpty())
                {
                    tile.renderMoves(sb, sr);
                }
            }
            temp_piece.render(sb);
        }
    }

    private void update() {
        inputHandler();
    }

    private float startX;
    private float startY;

    private Piece temp_piece;
    private Tile temp_tile;

    private List<Position> legalMoves;

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

            float dist = 12;

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

    private void dragAndDrop(float mouseX, float mouseY, boolean isDropping)
    {
        if (temp_piece == null) {
            return;
        }

        temp_piece.dragging(mouseX, mouseY);

        if(isDropping)
        {
            for (int x = 0; x < BOARD_SIZE; x++)
                for (int y = 0; y < BOARD_SIZE; y++)
                {
                    if (grid[x][y].contains(mouseX, mouseY))
                    {
                        Tile tile = grid[x][y];
                        dropPiece(tile, true);
                    }
                }
        }
    }

    private void click(float mouseX, float mouseY)
    {
        for (int x = 0; x < BOARD_SIZE; x++)
            for (int y = 0; y < BOARD_SIZE; y++)
            {
                if(grid[x][y].contains(mouseX, mouseY))
                {
                    Tile tile = grid[x][y];
                    if(temp_piece == null && !tile.isEmpty())
                    {
                        temp_piece = tile.getPiece_data();
                        legalMoves = temp_piece.getMoves();

                        temp_tile = tile;
                        temp_tile.setSelected(true);
                    }
                    else if(temp_piece != null)
                    {
                        dropPiece(tile, false);
                    }
                }
        }
    }

    private void dropPiece(Tile tile, boolean isDrag) {
        if (tile.isEmpty())
        {
            for (Position p : legalMoves)
            {
                if (tile.getPos().getX() == p.getX() && tile.getPos().getY() == p.getY())
                {
                    tile.setPiece_data(temp_piece);
                    resetTemp(tile);
                    return;
                }
                else
                {
                    temp_tile.setPiece_data(temp_piece);
                    temp_piece.setPiecePos(temp_tile.getPos());
                }
            }
            temp_tile.setSelected(false);
            temp_tile = null;
            temp_piece = null;
        }
        else
        {
            boolean sameSide = tile.getPiece_data().getSide().equals(temp_piece.getSide());

            if(!sameSide)
            {
                for (Position p : legalMoves)
                {
                    if (tile.getPos().getX() == p.getX() && tile.getPos().getY() == p.getY())
                    {
                        tile.setPiece_data(temp_piece);
                        resetTemp(tile);
                        return;
                    }
                    else
                    {
                        temp_tile.setPiece_data(temp_piece);
                        temp_piece.setPiecePos(temp_tile.getPos());
                    }
                }
            }
            else
            {
                if(isDrag)
                {
                    temp_piece.setPiecePos(temp_tile.getPos());
                }
                else
                {
                    temp_tile.setSelected(false);

                    temp_piece = tile.getPiece_data();
                    legalMoves = temp_piece.getMoves();

                    temp_tile = tile;
                    temp_tile.setSelected(true);
                }
            }
        }
    }

    private void resetTemp(Tile tile)
    {
        if(temp_piece != null && temp_tile != null)
        {
            temp_piece.setPiecePos(tile.getPos());
            temp_piece = null;

            temp_tile.setSelected(false);
            temp_tile.setPiece_data(null);
            temp_tile = null;
        }
    }
}
