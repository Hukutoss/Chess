package hukutoss.chess.piece;

import hukutoss.chess.core.Textures;
import hukutoss.chess.util.Side;

public class Pawn extends Piece {

    public Pawn(Side side) {
        super(side);
        this.sprite = side == Side.WHITE ? Textures.white_pawn : Textures.black_pawn;
    }

//    @Override
//    public void legalMoves()
//    {
//        moves.clear();
//        Pos mPos;
//        if (side == Side.WHITE)
//        {
//            mPos = new Pos(pos.getX(), pos.getY() + 1);
//            moves.add(mPos);
//
//            if (this.pos.getY() == 1)
//            {
//                mPos = new Pos(pos.getX(), pos.getY() + 2);
//                moves.add(mPos);
//            }
//        } else if (side == Side.BLACK)
//        {
//            mPos = new Pos(pos.getX(), pos.getY() - 1);
//            moves.add(mPos);
//
//            if (this.pos.getY() == 6)
//            {
//                mPos = new Pos(pos.getX(), pos.getY() - 2);
//                moves.add(mPos);
//            }
//        }
//    }
}
