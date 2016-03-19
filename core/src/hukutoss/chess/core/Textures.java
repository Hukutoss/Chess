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

    private static final int cellSize = 64;

    public static void load() {
        board_sheet = new Texture(Gdx.files.internal("texture.png"));

        white_cell = new Sprite(board_sheet, cellSize * 0, cellSize * 0, cellSize, cellSize);
        black_cell  = new Sprite(board_sheet, cellSize * 1, cellSize * 0, cellSize, cellSize);
    }
}
