package hukutoss.chess.piece;

import hukutoss.chess.core.Textures;
import hukutoss.chess.util.Position;
import hukutoss.chess.util.Side;

public class Queen extends Piece {

    public Queen(Position pos, Side side)
    {
        this.side = side;
        this.sprite = side == Side.WHITE ? Textures.white_queen : Textures.black_queen;
        this.pos = pos;
    }

    @Override
    public void legalMoves()
    {
        moves.clear();
        Position mPos;

        for (int i = 1; i < 8; i++)
        {
            int xx = pos.getX() + i;
            int xd = pos.getX() - i;
            int yy = pos.getY() + i;
            int yd = pos.getY() - i;

            mPos = new Position(pos.getX(), yy);
            moves.add(mPos);
            mPos = new Position(pos.getX(), yd);
            moves.add(mPos);
            mPos = new Position(xx, pos.getY());
            moves.add(mPos);
            mPos = new Position(xd, pos.getY());
            moves.add(mPos);
            mPos = new Position(xx, yy);
            moves.add(mPos);
            mPos = new Position(xx, yd);
            moves.add(mPos);
            mPos = new Position(xd, yd);
            moves.add(mPos);
            mPos = new Position(xd, yy);
            moves.add(mPos);
        }
    }
}
