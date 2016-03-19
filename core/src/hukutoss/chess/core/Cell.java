package hukutoss.chess.core;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Cell {

    private float x;
    private float y;

    private CellType type;

    public Cell(float x, float y, CellType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void render(SpriteBatch sb) {
        sb.draw(type.getSprite(), x, y, type.getSprite().getWidth(), type.getSprite().getHeight());
    }

    public boolean contains(float mouseX, float mouseY) {
        return  mouseX > x && mouseX < x + type.getSprite().getWidth() &&
                mouseY > y && mouseY < y + type.getSprite().getHeight();
    }

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
}
