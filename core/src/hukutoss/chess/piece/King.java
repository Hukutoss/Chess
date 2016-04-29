package hukutoss.chess.piece;

import hukutoss.chess.core.Textures;
import hukutoss.chess.util.PieceType;
import hukutoss.chess.util.Position;
import hukutoss.chess.util.Side;

public class King extends Piece {

    public King(Position pos, Side side)
    {
        this.side = side;
        this.sprite = side == Side.WHITE ? Textures.white_king : Textures.black_king;
        this.pos = pos;
        this.type = PieceType.Bishop;
    }

    @Override
    public void legalMoves()
    {
        moves.clear();
        Position mPos;

        mPos = new Position(pos.getX() + 1, pos.getY());
        moves.add(mPos);
        mPos = new Position(pos.getX() - 1, pos.getY());
        moves.add(mPos);
        mPos = new Position(pos.getX(), pos.getY() + 1);
        moves.add(mPos);
        mPos = new Position(pos.getX(), pos.getY() - 1);
        moves.add(mPos);
    }
}
