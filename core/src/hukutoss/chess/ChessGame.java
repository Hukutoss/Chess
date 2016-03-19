package hukutoss.chess;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import hukutoss.chess.core.GameLogic;
import hukutoss.chess.core.Textures;

public class ChessGame implements ApplicationListener {

	private SpriteBatch sb;
	private static OrthographicCamera camera;

    private GameLogic logic;

	@Override
	public void create () {
        Textures.load();

		sb = new SpriteBatch();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getHeight(), Gdx.graphics.getWidth());

        logic = new GameLogic();
	}

    @Override
    public void resize(int i, int i1) {

    }

    @Override
	public void render () {
        Gdx.gl.glClearColor(0xff / 255.0F, 0x80 / 255.0F, 0x80 / 255.0F, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        logic.render(sb);
        sb.end();
	}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {
        sb.dispose();
    }

    public static OrthographicCamera getCamera() {
        return camera;
    }
}
