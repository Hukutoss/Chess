package hukutoss.chess.piece;

import hukutoss.chess.core.Textures;
import hukutoss.chess.util.Position;
import hukutoss.chess.util.Side;

public class Rook extends Piece {

    public Rook(Position pos, Side side)
    {
        this.side = side;
        this.sprite = side == Side.WHITE ? Textures.white_rook: Textures.black_rook;
        this.pos = pos;
    }

    @Override
    public void legalMoves()
    {
        moves.clear();
        Position mPos;

        for (int i = 1; i < 8; i++)
        {
            mPos = new Position(pos.getX(), pos.getY() + i);
            moves.add(mPos);
            mPos = new Position(pos.getX(), pos.getY() - i);
            moves.add(mPos);
            mPos = new Position(pos.getX() + i, pos.getY());
            moves.add(mPos);
            mPos = new Position(pos.getX() - i, pos.getY());
            moves.add(mPos);
        }
    }
}
