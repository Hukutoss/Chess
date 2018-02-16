package hukutoss.chess.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import hukutoss.chess.util.Lib;

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

    public static void load() {
        texture = new Texture(Gdx.files.internal("textures/board.png"));
        int tile = Lib.TILE_SIZE;
        int piece = Lib.PIECE_SIZE;

        white_cell =    new Sprite(texture, tile * 0, tile * 0, tile, tile);
        black_cell  =   new Sprite(texture, tile * 1, tile * 0, tile, tile);

        texture =       new Texture(Gdx.files.internal("textures/pieces.png"));

        white_king =    new Sprite(texture, piece * 0, piece * 0, piece, piece);
        white_queen =   new Sprite(texture, piece * 1, piece * 0, piece, piece);
        white_bishop =  new Sprite(texture, piece * 2, piece * 0, piece, piece);
        white_knight =  new Sprite(texture, piece * 3, piece * 0, piece, piece);
        white_rook =    new Sprite(texture, piece * 4, piece * 0, piece, piece);
        white_pawn =    new Sprite(texture, piece * 5, piece * 0, piece, piece);

        black_king =    new Sprite(texture, piece * 0, piece * 1, piece, piece);
        black_queen =   new Sprite(texture, piece * 1, piece * 1, piece, piece);
        black_bishop =  new Sprite(texture, piece * 2, piece * 1, piece, piece);
        black_knight =  new Sprite(texture, piece * 3, piece * 1, piece, piece);
        black_rook =    new Sprite(texture, piece * 4, piece * 1, piece, piece);
        black_pawn =    new Sprite(texture, piece * 5, piece * 1, piece, piece);
    }

    public static void dispose() {
        texture.dispose();
    }
}
