import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MapResultScene implements Scene {


	private Label mapStatusLabel;
	private Label scoreLabel;
	private Label score;
	private Button backToMenuButton;
	private BufferedImage emoji1;

	private int scoreToDisplay;
	private int scoreIncrement;
	private int scoreIncrementCount = 100;

	private Color backgroundColor;
	private Insets insets;

	@Override
	public void init() throws IOException {
		backgroundColor = new Color(0, 0, 0, 140);
		insets = Game.window.getInsets();
		scoreToDisplay = 0;
		mapStatusLabel = new Label("", "sign_in", Color.WHITE);
		mapStatusLabel.setPosition(400, 300);
		scoreLabel = new Label(Game.dialogManager.getDialog("score_label"), "sign_in", Color.WHITE);
		scoreLabel.setPosition(400, 340);
		score = new Label(Integer.toString(scoreToDisplay), "sign_in", Color.WHITE);
		score.setPosition(475, 340);

		backToMenuButton = new Button(400, 380, 300, 50, Game.dialogManager.getDialog("continue_button"), new Color(14, 132, 168, 128), new Color(14, 132, 168, 255), Color.WHITE, "sign_in");
		backToMenuButton.setTextPositionOffset(100, 30);

		if(Variables.stageCleared){
			emoji1 = Game.ressourceManager.getTexture("ThumbsUpEmoji.png");
			mapStatusLabel.setText(Game.dialogManager.getDialog("map_cleared_label"));
		}
		else{
			emoji1 = Game.ressourceManager.getTexture("LaughingEmoji.png");
			mapStatusLabel.setText(Game.dialogManager.getDialog("map_failed_label"));
		}

		Game.observable.add(backToMenuButton, Observable.EventID.MOUSE_CLICKED, Observable.EventID.MOUSE_MOVED);

		scoreIncrement = Variables.score / scoreIncrementCount;
		if(scoreIncrement < 1){
			scoreIncrement = 1;
		}
	}

	@Override
	public void close() {

	}

	@Override
	public void render(Graphics graphics) {
		graphics.setColor(backgroundColor);
		graphics.fillRect(0 + insets.left, 0 + insets.top, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		mapStatusLabel.draw(graphics);
		scoreLabel.draw(graphics);
		score.draw(graphics);
		backToMenuButton.draw(graphics);
		graphics.drawImage(emoji1, 100 + insets.left, 300 + insets.top, null);
	}

	@Override
	public void simulate() {
		if(scoreToDisplay != Variables.score){
			if(Variables.score - scoreToDisplay < scoreIncrement){
				scoreToDisplay = Variables.score;
			}
			else{
				scoreToDisplay += scoreIncrement;
			}
		}
		score.setText(Integer.toString(scoreToDisplay));

		if(backToMenuButton.isClicked()){
			backToMenuButton.setClicked(false);
			Game.sceneManager.setScenesToRender(1);
			Game.sceneManager.pop();
			Game.sceneManager.pop();
		}
	}

	@Override
	public void setName(String name) {

	}
}