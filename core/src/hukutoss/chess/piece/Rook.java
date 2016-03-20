package hukutoss.chess.piece;

import hukutoss.chess.core.Textures;
import hukutoss.chess.util.Side;

public class Rook extends Piece {

    public Rook(float x, float y, Side side) {
        this.sprite = side == Side.WHITE ? Textures.white_rook: Textures.black_rook;

        this.setPiecePos(x, y);
    }
}
