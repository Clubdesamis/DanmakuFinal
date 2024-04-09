import java.awt.*;
import java.io.IOException;

public class MainMenuScene implements Scene{

	MapScroller mapScroller;
	Label mapName;
	Label mapCreatorLabel;
	Label mapCreator;
	Label mapDifficultyLabel;
	Label mapDifficulty;
	Button playButton;

	Color backgroundColor;
	boolean displayInfo = false;

	@Override
	public void init() throws IOException {
		mapScroller = new MapScroller(100, 150, 15, 450, 40, "sign_in", new Color(14, 132, 168, 128), new Color(14, 132, 168, 255));
		mapName = new Label("", "sign_in", Color.WHITE);
		mapName.setPosition(750, 150);
		mapCreator = new Label("", "sign_in", Color.WHITE);
		mapCreator.setPosition(750, 200);
		mapDifficulty = new Label("", "sign_in", Color.WHITE);
		mapDifficulty.setPosition(750, 250);

		//playButton = new Button(750, 250,  300, 50, Game.dialogManager.getDialog("play_button_label"),)


		backgroundColor = new Color(0, 0, 0, 140);
		Game.observable.add(mapScroller, Observable.EventID.MOUSE_CLICKED, Observable.EventID.MOUSE_WHEEL_MOVED, Observable.EventID.MOUSE_MOVED, Observable.EventID.KEY_TYPED);

	}

	@Override
	public void close() {

	}

	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(Game.ressourceManager.getTexture("MainMenuBackground.png"), 0, 0, null);

		if(displayInfo){
			graphics.setColor(backgroundColor);
			graphics.fillRect(720, 90, 500, 300);
			mapName.draw(graphics);
			mapCreator.draw(graphics);
			mapDifficulty.draw(graphics);
		}
		mapScroller.draw(graphics);
	}

	@Override
	public void simulate() {
		mapName.setText(mapScroller.getSelectedHeader().getAttribute("Name"));
		mapCreator.setText(mapScroller.getSelectedHeader().getAttribute("Creator"));
		mapDifficulty.setText(mapScroller.getSelectedHeader().getAttribute("Difficulty"));
		if(mapName.getText().equals("")){
			displayInfo = false;
		}
		else{
			displayInfo = true;
		}
	}

	@Override
	public void setName(String name) {

	}
}
