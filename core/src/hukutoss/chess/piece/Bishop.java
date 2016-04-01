package hukutoss.chess.piece;

import hukutoss.chess.core.Textures;
import hukutoss.chess.util.Position;
import hukutoss.chess.util.Side;

public class Bishop extends Piece {

    public Bishop(Position pos, Side side)
    {
        this.side = side;
        this.sprite = side == Side.WHITE ? Textures.white_bishop : Textures.black_bishop;
        this.pos = pos;
    }

    @Override
    public void legalMoves() {

    }
}
