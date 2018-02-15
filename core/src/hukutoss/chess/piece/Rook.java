package hukutoss.chess.piece;

import hukutoss.chess.core.Textures;
import hukutoss.chess.util.Pos;
import hukutoss.chess.util.Side;

public class Rook extends Piece {

    public Rook(Side side) {
        super(side);
        this.sprite = side == Side.WHITE ? Textures.white_rook : Textures.black_rook;
    }

//    @Override
//    public void legalMoves()
//    {
//        moves.clear();
//        Pos mPos;
//
//        for (int i = 1; i < 8; i++)
//        {
//            mPos = new Pos(pos.getX(), pos.getY() + i);
//            moves.add(mPos);
//            mPos = new Pos(pos.getX(), pos.getY() - i);
//            moves.add(mPos);
//            mPos = new Pos(pos.getX() + i, pos.getY());
//            moves.add(mPos);
//            mPos = new Pos(pos.getX() - i, pos.getY());
//            moves.add(mPos);
//        }
//    }
}
