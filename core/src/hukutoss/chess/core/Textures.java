package hukutoss.chess.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Textures {

    private static Texture texture;

    public static Sprite white_cell;
    public static Sprite black_cell;

    public static Sprite white_king;
    public static Sprite white_queen;
    public static Sprite white_pawn;
    public static Sprite white_knight;
    public static Sprite white_bishop;
    public static Sprite white_rook;

    public static Sprite black_king;
    public static Sprite black_queen;
    public static Sprite black_pawn;
    public static Sprite black_knight;
    public static Sprite black_bishop;
    public static Sprite black_rook;

    private static final int pieceSize = 48;
    private static final int cellSize = 64;

    public static void load() {
        texture = new Texture(Gdx.files.internal("textures/board.png"));

        white_cell = new Sprite(texture, cellSize * 0, cellSize * 0, cellSize, cellSize);
        black_cell  = new Sprite(texture, cellSize * 1, cellSize * 0, cellSize, cellSize);

        texture = new Texture(Gdx.files.internal("textures/pieces.png"));

        white_king = new Sprite(texture, pieceSize * 0, pieceSize * 0, pieceSize, pieceSize);
        white_queen = new Sprite(texture, pieceSize * 1, pieceSize * 0, pieceSize, pieceSize);
        white_bishop = new Sprite(texture, pieceSize * 2, pieceSize * 0, pieceSize, pieceSize);
        white_knight = new Sprite(texture, pieceSize * 3, pieceSize * 0, pieceSize, pieceSize);
        white_rook = new Sprite(texture, pieceSize * 4, pieceSize * 0, pieceSize, pieceSize);
        white_pawn = new Sprite(texture, pieceSize * 5, pieceSize * 0, pieceSize, pieceSize);

        black_king = new Sprite(texture, pieceSize * 0, pieceSize * 1, pieceSize, pieceSize);
        black_queen = new Sprite(texture, pieceSize * 1, pieceSize * 1, pieceSize, pieceSize);
        black_bishop = new Sprite(texture, pieceSize * 2, pieceSize * 1, pieceSize, pieceSize);
        black_knight = new Sprite(texture, pieceSize * 3, pieceSize * 1, pieceSize, pieceSize);
        black_rook = new Sprite(texture, pieceSize * 4, pieceSize * 1, pieceSize, pieceSize);
        black_pawn = new Sprite(texture, pieceSize * 5, pieceSize * 1, pieceSize, pieceSize);
    }

    public static void dispose() {
        texture.dispose();
    }
}
