package hukutoss.chess.piece;

import hukutoss.chess.core.Textures;
import hukutoss.chess.util.Side;

public class Bishop extends Piece {

    public Bishop(Side side) {
        super(side);
        this.sprite = side == Side.WHITE ? Textures.white_bishop : Textures.black_bishop;
    }

//    @Override
//    public void legalMoves() {
//        moves.clear();
//        Pos mPos;
//
//        for (int i = 1; i < 8; i++)
//        {
//            mPos = new Pos(pos.getX() + i, pos.getY() + i);
//            moves.add(mPos);
//            mPos = new Pos(pos.getX() - i, pos.getY() - i);
//            moves.add(mPos);
//            mPos = new Pos(pos.getX() + i, pos.getY() - i);
//            moves.add(mPos);
//            mPos = new Pos(pos.getX() - i, pos.getY() + i);
//            moves.add(mPos);
//        }
//    }
}
