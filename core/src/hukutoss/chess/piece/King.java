package hukutoss.chess.piece;

import hukutoss.chess.core.Textures;
import hukutoss.chess.util.Side;

public class King extends Piece {

    public King(float x, float y, Side side)
    {
        this.side = side;
        this.sprite = side == Side.WHITE ? Textures.white_king : Textures.black_king;
        this.setPiecePos(x, y);
    }
}
