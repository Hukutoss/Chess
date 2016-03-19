package hukutoss.chess.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import hukutoss.chess.ChessGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Chess";
        config.height = 512;
        config.width = 512;
		new LwjglApplication(new ChessGame(), config);
	}
}
