package hukutoss.chess.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import hukutoss.chess.ChessGame;
import hukutoss.chess.piece.*;
import hukutoss.chess.util.Pos;
import hukutoss.chess.util.Side;

public class ChessBoard {

    public static final int BOARD_SIZE = 8;

    private Tile[][] grid;

    public ChessBoard() {
        grid = new Tile[BOARD_SIZE][BOARD_SIZE];

        //x - letter, y - number
        for (int x = 0; x < BOARD_SIZE; x++)
            for (int y = 0; y < BOARD_SIZE; y++) {
                Sprite tileSprite = (x % 2 == 0 && y % 2 == 0 || x % 2 != 0 && y % 2 != 0) ?
                        Textures.white_cell : Textures.black_cell;
                grid[x][y] = new Tile(x, y, tileSprite);
            }

        initGame();
    }

    // TODO: @Cleanup Move FEN declaration and etc. into different file

    // FEN for the starting position
    //      "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"
    //
    // A FEN record mouseContains six fields. They separate with a space
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

    private void initGame() {
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

        grid[4][4].setPiece(new Queen(new Pos(4, 4), Side.WHITE)); //TODO: @Temporary

        //TODO: Add color, castling, en passant and clocks
    }

    public void render(SpriteBatch sb, ShapeRenderer sr) {
        sb.setColor(Color.WHITE);
        for (int x = 0; x < BOARD_SIZE; x++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                grid[x][y].render(sb);
                if (grid[x][y].getPiece() != null) {
                    grid[x][y].getPiece().render(sb);
                }
            }
        }
    }

    public Tile[][] getGrid() {
        return grid;
    }
}
