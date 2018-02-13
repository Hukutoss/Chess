package hukutoss.chess.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import hukutoss.chess.ChessGame;
import hukutoss.chess.piece.*;
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

    // TODO: @Cleanup Move FEN declaration and etc. into different file

    // FEN for the starting position
    //      "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"
    //
    // A FEN record contains six fields. They separate with a space
    // 1. Piece placement from white's perspective. Each row is described, from 8 to 1. Within each row the
    // contents of each tile are described from "a" to "h".
    // Each piece is identified by a single letter taken from the standard English names:
    //      "P" - Pawn
    //      "N" - Knight
    //      "B" - Bishop
    //      "R" - Rook
    //      "Q" = Queen
    //      "K" = King
    // Uppercase letters for White side. Lowercase letters for Black side.
    // Empty tiles are noted using digits from 1 to 8 (the number of empty tiles).
    // "/" - separates ranks.
    // 2. Active color.
    //      "w" means White move next.
    //      "b" means Black move next.
    // 3. Castling availability. It has one or more letters.
    //      "k" or "K" for kingside castle.
    //      "q" or "Q" for queenside castle.
    //      Uppercase means White side, lowercase Black side.
    // 4. En passant target square in algebraic notation. If there's no en passant, this is "-"
    // 5. Halfmove clock. This is the number of halfmove since the last capture or pawn advance.
    //    This is used to determine if a draw can be claimed under the "fifty-move rule".
    // 6. Fullmove number. The number of the full move. It start at 1, and is incremented after Black's move.

    private static final String DEFAULT_FEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
    String secondfen = "r1bqkb1r/pppp1ppp/2n2n2/4p3/2P5/2N2N2/PP1PPPPP/R1BQKB1R w KQkq - 0 1";

    private void initGame()
    {
        String fen = secondfen.trim();
        String[] fenParts = fen.split(" ");
        if (fenParts.length != 6) {
            System.out.format("FEN is invalid %s must have 6 sections", fen); //TODO: @Cleanup Make a LOG
            return;
        }
        //Board setup
        String[] rankStrs = fenParts[0].split("/");
        if (rankStrs.length != 8) {
            System.out.format("FEN has an invalid board %s", rankStrs); //TODO: @Cleanup Make a LOG
            return;
        }
        for (int y = 0; y < rankStrs.length; y++) {
            int x = 0;
            for (int r = 0; r < rankStrs[y].length(); r++) {
                char t = rankStrs[y].charAt(r);
//                System.out.format("There is %s at [%s][%s]  \n", Character.toUpperCase(t) , r, y);
                if (!Character.isLetter(t)) {
                    x += Character.getNumericValue(t);
//                    System.out.format("> x is %s. Added %s\n", x, t);
                    continue;
                }

                Side side = Character.isLowerCase(t) ? Side.WHITE : Side.BLACK;
                t = Character.toUpperCase(t);
                System.out.format(">>> %s %s piece added at [%s][%s]\n", side.name(), t, x, y);
                x++;
                //TODO: Implement pieces into the game
            }
            System.out.println("=====");
        }

        //TODO: Add color, castling, en passant and clocks
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
