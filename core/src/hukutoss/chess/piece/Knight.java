package hukutoss.chess.piece;

import hukutoss.chess.core.Textures;
import hukutoss.chess.util.Position;
import hukutoss.chess.util.Side;

public class Knight extends Piece {

    public Knight(Position pos, Side side)
    {
        this.side = side;
        this.sprite = side == Side.WHITE ? Textures.white_knight : Textures.black_knight;
        this.pos = pos;
    }

    @Override
    public void legalMoves()
    {
        moves.clear();
        Position mPos;

        mPos = new Position(pos.getX() + 1, pos.getY() + 2);
        moves.add(mPos);
        mPos = new Position(pos.getX() - 1, pos.getY() + 2);
        moves.add(mPos);
        mPos = new Position(pos.getX() + 1, pos.getY() - 2);
        moves.add(mPos);
        mPos = new Position(pos.getX() - 1, pos.getY() - 2);
        moves.add(mPos);
        mPos = new Position(pos.getX() + 2, pos.getY() + 1);
        moves.add(mPos);
        mPos = new Position(pos.getX() + 2, pos.getY() - 1);
        moves.add(mPos);
        mPos = new Position(pos.getX() - 2, pos.getY() + 1);
        moves.add(mPos);
        mPos = new Position(pos.getX() - 2, pos.getY() - 1);
        moves.add(mPos);
    }
}
