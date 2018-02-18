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

    private String userFen; //Input from user

    private void initGame() {
        String fen = userFen == null ? Lib.DEFAULT_FEN : userFen;

        fen = fen.trim();
        if (!validateFen(fen)) {
            return;
        }

        String[] fenParts = fen.split(" ");

        //Board setup
        String[] rank = fenParts[0].split("/");
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

    private boolean validateFen(String fen) {

        //6 space-separated fields
        String[] fenParts = fen.split(" ");
        if (fenParts.length != 6) {
            logger.info("FEN must contain six space-delimited fields: %s", fen);
            return false;
        }

        //2nd field is "w" or "b"
        if (!fenParts[1].matches("^([wb])$")) {
            logger.info("FEN: 2nd field is invalid: %s", fenParts[1]);
            return false;
        }

        //3rd field is a valid castle string
        if (!fenParts[2].matches("^(KQ?k?q?|Qk?q?|kq?|q|-)$")) {
            logger.info("FEN: 3rd field is invalid: %s", fenParts[2]);
            return false;
        }

        //4th field is a valied e.p. string
        if(!fenParts[3].matches("^(-|[abcdefgh][36])$")) {
            logger.info("FEN: 4th field is invalid: %s", fenParts[3]);
            return false;
        }

        //half move counter is an integer >= 0
        if(Integer.parseInt(fenParts[4]) < 0) {
            logger.info("FEN: 5th field must be a non-negative integer.: %s", fenParts[4]);
            return false;
        }

        //move number field is a integer > 0
        if(Integer.parseInt(fenParts[5]) <= 0) {
            logger.info("FEN: 6th field must be a positive integer: %s", fenParts[5]);
            return false;
        }

        //1st field contains 8 rows?
        String[] rows = fenParts[0].split("/");
        if (rows.length != 8) {
            logger.info("'FEN: 1st field \"%s\" doesn't contain 8 '/'-delimited rows.", fenParts[0]);
            return false;
        }

        //TODO: is 1-nd row valid?
        //TODO: is e.p is valid

        //everything is ok
        return true;
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
