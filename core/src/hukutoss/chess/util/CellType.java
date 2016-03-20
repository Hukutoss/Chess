package hukutoss.chess.util;

import com.badlogic.gdx.graphics.g2d.Sprite;
import hukutoss.chess.core.Textures;

public enum CellType {

    BLACK(Textures.black_cell),
    WHITE(Textures.white_cell);

    private Sprite sprite;

    CellType(Sprite sprite) {
        this.sprite = sprite;
    }

    public Sprite getSprite() {
        return sprite;
    }
}
