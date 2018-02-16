package hukutoss.chess.core;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import hukutoss.chess.piece.*;
import hukutoss.chess.util.Logger;
import hukutoss.chess.util.Lib;
import hukutoss.chess.util.Side;

public class ChessBoard {

    private Logger logger = Logger.getLogger(ChessBoard.class);

    private Tile[][] grid;

    public ChessBoard() {
        grid = new Tile[Lib.BOARD_SIZE][Lib.BOARD_SIZE];

        //x - letter, y - number
        for (int x = 0; x < Lib.BOARD_SIZE; x++)
            for (int y = 0; y < Lib.BOARD_SIZE; y++) {
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

    private void initGame() {

        String fen = Lib.START_FEN.trim();
        String[] fenParts = fen.split(" ");
        if (fenParts.length != 6) {
            logger.info("FEN is invalid %s must have 6 sections", fen);
            return;
        }
        //Board setup
        String[] rank = fenParts[0].split("/");
        if (rank.length != 8) {
            logger.info("FEN has an invalid board %s", fenParts[0]);
            return;
        }

        for (int y = 0; y < rank.length; y++) {
            int x = 0;
            for (int r = 0; r < rank[y].length(); r++) {
                char t = rank[y].charAt(r);
                if (!Character.isLetter(t)) {
                    x += Character.getNumericValue(t);
                    continue;
                }

                try {
                    Side side = Character.isLowerCase(t) ? Side.WHITE : Side.BLACK;
                    t = Character.toUpperCase(t);
                    Piece p = (Piece) Lib.pieceMap.get(t).getConstructor(Side.class).newInstance(side);
                    getGrid()[x][y].addPiece(p);
                } catch (Exception e) {
                    logger.error("Something goes wrong here... ");
                }
                x++;
            }
        }
        //TODO: Add color, castling, en passant and clocks
    }

    public void render(SpriteBatch sb, ShapeRenderer sr) {
        sb.setColor(Color.WHITE);
        for (int x = 0; x < Lib.BOARD_SIZE; x++) {
            for (int y = 0; y < Lib.BOARD_SIZE; y++) {
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
