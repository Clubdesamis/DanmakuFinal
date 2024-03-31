import java.awt.*;
import java.io.IOException;

public class MainMenuScene implements Scene{

	MapScroller mapScroller;
	@Override
	public void init() throws IOException {
		mapScroller = new MapScroller(100, 150, 15, 450, 40, "sign_in", new Color(14, 132, 168, 128), new Color(14, 132, 168, 255));


		Game.observable.add(mapScroller, Observable.EventID.MOUSE_CLICKED, Observable.EventID.MOUSE_WHEEL_MOVED, Observable.EventID.MOUSE_MOVED, Observable.EventID.KEY_TYPED);

	}

	@Override
	public void close() {

	}

	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(Game.ressourceManager.getTexture("MainMenuBackground.png"), 0, 0, null);
		mapScroller.draw(graphics);
	}

	@Override
	public void simulate() {
	}

	@Override
	public void setName(String name) {

	}
}
