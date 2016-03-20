package hukutoss.chess.piece;

import hukutoss.chess.core.Textures;
import hukutoss.chess.util.Side;

public class Bishop extends Piece {

    public Bishop(float x, float y, Side side) {
        this.sprite = side == Side.WHITE ? Textures.white_bishop : Textures.black_bishop;

        this.setPiecePos(x, y);
    }
}
