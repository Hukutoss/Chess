package hukutoss.chess.piece;

import hukutoss.chess.core.Textures;
import hukutoss.chess.util.Side;

public class Pawn extends Piece {

    public Pawn(float x, float y, Side side)
    {
        this.side = side;
        this.sprite = side == Side.WHITE ? Textures.white_pawn : Textures.black_pawn;
        this.setPiecePos(x, y);
    }
}
