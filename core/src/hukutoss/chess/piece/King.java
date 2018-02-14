package hukutoss.chess.piece;

import hukutoss.chess.core.Textures;
import hukutoss.chess.util.Pos;
import hukutoss.chess.util.Side;

public class King extends Piece {

    public King(Pos pos, Side side) {
        super(pos, side);
        this.sprite = side == Side.WHITE ? Textures.white_king : Textures.black_king;
    }

//    @Override
//    public void legalMoves()
//    {
//        moves.clear();
//        Pos mPos;
//
//        mPos = new Pos(pos.getX() + 1, pos.getY());
//        moves.add(mPos);
//        mPos = new Pos(pos.getX() - 1, pos.getY());
//        moves.add(mPos);
//        mPos = new Pos(pos.getX(), pos.getY() + 1);
//        moves.add(mPos);
//        mPos = new Pos(pos.getX(), pos.getY() - 1);
//        moves.add(mPos);
//    }
}
