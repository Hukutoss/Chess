package hukutoss.chess;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game implements ApplicationListener {

	private SpriteBatch batch;
	private OrthographicCamera camera;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getHeight(), Gdx.graphics.getWidth());
	}

    @Override
    public void resize(int i, int i1) {

    }

    @Override
	public void render () {
        Gdx.gl.glClearColor(0x80 / 255.0F, 0x80 / 255.0F, 0x80 / 255.0F, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
