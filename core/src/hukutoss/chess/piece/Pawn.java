package hukutoss.chess.piece;

import hukutoss.chess.core.Textures;
import hukutoss.chess.util.Position;
import hukutoss.chess.util.Side;

public class Pawn extends Piece {

    public Pawn(Position pos, Side side)
    {
        this.side = side;
        this.sprite = side == Side.WHITE ? Textures.white_pawn : Textures.black_pawn;
        this.pos = pos;
    }

    @Override
    public void legalMoves()
    {
        moves.clear();
        Position mPos = new Position(pos.getX(), pos.getY() + 1);
        moves.add(mPos);

        if(isFirstMove())
        {
            mPos = new Position(pos.getX(), pos.getY() + 2);
            moves.add(mPos);
        }
    }

    public boolean isFirstMove() {
        return this.pos.getY() == 1;
    }
}
