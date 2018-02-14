package hukutoss.chess.piece;

import hukutoss.chess.core.Textures;
import hukutoss.chess.util.Pos;
import hukutoss.chess.util.Side;

public class Queen extends Piece {

    public Queen(Pos pos, Side side) {
        super(pos, side);
        this.sprite = side == Side.WHITE ? Textures.white_queen : Textures.black_queen;
    }

//    @Override
//    public void legalMoves()
//    {
//        moves.clear();
//        Pos mPos;
//
//        for (int i = 1; i < 8; i++)
//        {
//            int xx = pos.getX() + i;
//            int xd = pos.getX() - i;
//            int yy = pos.getY() + i;
//            int yd = pos.getY() - i;
//
//            mPos = new Pos(pos.getX(), yy);
//            moves.add(mPos);
//            mPos = new Pos(pos.getX(), yd);
//            moves.add(mPos);
//            mPos = new Pos(xx, pos.getY());
//            moves.add(mPos);
//            mPos = new Pos(xd, pos.getY());
//            moves.add(mPos);
//            mPos = new Pos(xx, yy);
//            moves.add(mPos);
//            mPos = new Pos(xx, yd);
//            moves.add(mPos);
//            mPos = new Pos(xd, yd);
//            moves.add(mPos);
//            mPos = new Pos(xd, yy);
//            moves.add(mPos);
//        }
//    }
}
