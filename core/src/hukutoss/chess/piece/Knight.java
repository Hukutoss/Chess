package hukutoss.chess.piece;

import hukutoss.chess.core.Textures;
import hukutoss.chess.util.Side;

public class Knight extends Piece {

    public Knight(float x, float y, Side side) {
        this.sprite = side == Side.WHITE ? Textures.white_knight : Textures.black_knight;

        this.setPiecePos(x, y);
    }
}
