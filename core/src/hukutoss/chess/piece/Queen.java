package hukutoss.chess.piece;

import hukutoss.chess.core.Textures;
import hukutoss.chess.util.Side;

public class Queen extends Piece {

    public Queen(float x, float y, Side side)
    {
        this.side = side;
        this.sprite = side == Side.WHITE ? Textures.white_queen : Textures.black_queen;
        this.setPiecePos(x, y);
    }
}
