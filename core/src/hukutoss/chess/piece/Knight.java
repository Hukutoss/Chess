package hukutoss.chess.piece;

import hukutoss.chess.core.Textures;
import hukutoss.chess.util.Pos;
import hukutoss.chess.util.Side;

public class Knight extends Piece {

    public Knight(Side side) {
        super(side);
        this.sprite = side == Side.WHITE ? Textures.white_knight : Textures.black_knight;
    }

//    @Override
//    public void legalMoves()
//    {
//        moves.clear();
//        Pos mPos;
//
//        mPos = new Pos(pos.getX() + 1, pos.getY() + 2);
//        moves.add(mPos);
//        mPos = new Pos(pos.getX() - 1, pos.getY() + 2);
//        moves.add(mPos);
//        mPos = new Pos(pos.getX() + 1, pos.getY() - 2);
//        moves.add(mPos);
//        mPos = new Pos(pos.getX() - 1, pos.getY() - 2);
//        moves.add(mPos);
//        mPos = new Pos(pos.getX() + 2, pos.getY() + 1);
//        moves.add(mPos);
//        mPos = new Pos(pos.getX() + 2, pos.getY() - 1);
//        moves.add(mPos);
//        mPos = new Pos(pos.getX() - 2, pos.getY() + 1);
//        moves.add(mPos);
//        mPos = new Pos(pos.getX() - 2, pos.getY() - 1);
//        moves.add(mPos);
//    }
}
