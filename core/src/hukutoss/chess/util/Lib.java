package hukutoss.chess.util;

import hukutoss.chess.piece.*;

import java.util.HashMap;
import java.util.Map;

public class Lib {

    //TODO: @Hack Right now we have constant size for rendering and logic.
    // This isn't gonna work in different resolutions. Need to change it to flexible value
    public static final int TILE_SIZE = 64;
    public static final int PIECE_SIZE = 48;

    public static final int BOARD_SIZE = 8;

    public static final Map<Character, Class<?>> pieceMap = new HashMap<>();
    static {
        pieceMap.put('P', Pawn.class);
        pieceMap.put('N', Knight.class);
        pieceMap.put('B', Bishop.class);
        pieceMap.put('R', Rook.class);
        pieceMap.put('Q', Queen.class);
        pieceMap.put('K', King.class);
    }

    //FEN
    public static final String DEFAULT_FEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
    public static final String FEN_A28 = "r1bqkb1r/pppp1ppp/2n2n2/4p3/2P5/2N2N2/PP1PPPPP/R1BQKB1R w KQkq - 0 1";

}
