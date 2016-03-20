package hukutoss.chess.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Textures {

    public static Textures instance;

    public static Textures getInstance() {
        if (instance == null) {
            instance = new Textures();
        }
        return instance;
    }

    private Textures() {}

    private static Texture board_sheet;

    public static Sprite white_cell;
    public static Sprite black_cell;

    private static Texture pieces_sheet;

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
        board_sheet = new Texture(Gdx.files.internal("textures/board.png"));

        white_cell = new Sprite(board_sheet, cellSize * 0, cellSize * 0, cellSize, cellSize);
        black_cell  = new Sprite(board_sheet, cellSize * 1, cellSize * 0, cellSize, cellSize);

        pieces_sheet = new Texture(Gdx.files.internal("textures/pieces.png"));

        white_king = new Sprite(pieces_sheet, pieceSize * 0, pieceSize * 0, pieceSize, pieceSize);
        white_queen = new Sprite(pieces_sheet, pieceSize * 1, pieceSize * 0, pieceSize, pieceSize);
        white_bishop = new Sprite(pieces_sheet, pieceSize * 2, pieceSize * 0, pieceSize, pieceSize);
        white_knight = new Sprite(pieces_sheet, pieceSize * 3, pieceSize * 0, pieceSize, pieceSize);
        white_rook = new Sprite(pieces_sheet, pieceSize * 4, pieceSize * 0, pieceSize, pieceSize);
        white_pawn = new Sprite(pieces_sheet, pieceSize * 5, pieceSize * 0, pieceSize, pieceSize);

        black_king = new Sprite(pieces_sheet, pieceSize * 0, pieceSize * 1, pieceSize, pieceSize);
        black_queen = new Sprite(pieces_sheet, pieceSize * 1, pieceSize * 1, pieceSize, pieceSize);
        black_bishop = new Sprite(pieces_sheet, pieceSize * 2, pieceSize * 1, pieceSize, pieceSize);
        black_knight = new Sprite(pieces_sheet, pieceSize * 3, pieceSize * 1, pieceSize, pieceSize);
        black_rook = new Sprite(pieces_sheet, pieceSize * 4, pieceSize * 1, pieceSize, pieceSize);
        black_pawn = new Sprite(pieces_sheet, pieceSize * 5, pieceSize * 1, pieceSize, pieceSize);
    }
}
