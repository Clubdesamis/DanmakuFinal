import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

public class MapScroller extends VisualComponent{

	private ArrayList<ScriptHeader> headers;
	private ArrayList<Button> levelButtons;
	private Color buttonsColor;
	private Color buttonsHoverColor;
	private String fontId;
	private int levelsToShow;

	private TextField searchField;

	private int scrollIndex;
	private int buttonsHeight;
	private int buttonsWidth;
	private int buttonSpacing;

	private final int textOffsetX = 15;
	private final int textOffsetY = 28;

	private final int textFieldOffsetX = 0;
	private final int textFieldOffsetY = -40;

	public MapScroller(int positionX, int positionY, int levelsToShow, int buttonsWidth, int buttonsHeight, String fontId, Color buttonsColor, Color buttonsHoverColor){
		headers = new ArrayList<ScriptHeader>();
		levelButtons = new ArrayList<Button>();
		searchField = new TextField();
		searchField.setPosition(positionX + textFieldOffsetX, positionY + textFieldOffsetY);
		searchField.setSize(buttonsWidth, 25);
		searchField.setFont("credentials");
		searchField.setBackgroundColor(Color.WHITE);
		searchField.setTextColor(Color.WHITE);
		searchField.setMaxLength(20);

		scrollIndex = 0;
		this.buttonsColor = buttonsColor;
		this.buttonsHoverColor = buttonsHoverColor;
		this.fontId = fontId;
		this.positionX = positionX;
		this.positionY = positionY;
		this.levelsToShow = levelsToShow;
		this.buttonsWidth = buttonsWidth;
		this.buttonsHeight = buttonsHeight;
		this.buttonSpacing = 50;
		loadHeaders();
		loadLevels();
	}

	public void loadHeaders(){
		headers = Game.scriptReader.readAllHeaders();
	}

	public void loadLevels(){
		for(int i = 0; i < headers.size(); i++){
			levelButtons.add(new Button(-100, -100, buttonsWidth, buttonsHeight, headers.get(i).getAttribute("Name"), buttonsColor, buttonsHoverColor, Color.WHITE, fontId));
			levelButtons.get(i).setTextPositionOffset(textOffsetX, textOffsetY);
		}
	}
	@Override
	public void notification(Observable.EventID id, AWTEvent e) {
		switch(id){
			case MOUSE_CLICKED -> {
				for(int i = 0; i < levelButtons.size(); i++){
					levelButtons.get(i).notification(Observable.EventID.MOUSE_CLICKED, e);
				}
				searchField.notification(Observable.EventID.MOUSE_CLICKED, e);
				break;
			}
			case MOUSE_MOVED -> {
				for(int i = 0; i < levelButtons.size(); i++){
					levelButtons.get(i).notification(Observable.EventID.MOUSE_MOVED, e);
				}
				break;
			}
			case MOUSE_WHEEL_MOVED -> {

				int scrollDirection = ((MouseWheelEvent)e).getWheelRotation();
				switch(scrollDirection){
					case -1 -> {
						if(scrollIndex > 0){
							scrollIndex--;
						}
						break;
					}
					case 1 -> {
						if(scrollIndex < levelButtons.size() - 1){
							scrollIndex++;
						}
						break;
					}
				}
				System.out.println(Integer.toString(scrollIndex));
				break;
			}
			case KEY_TYPED -> {
				searchField.notification(Observable.EventID.KEY_TYPED, e);
			}
			default -> {
				break;
			}
		}

	}

	@Override
	public void draw(Graphics graphics) {
		//levelButtons.get(scrollIndex).draw(graphics, 200, 200);
		for(int i = 0; i < levelButtons.size(); i++){
			levelButtons.get(i).setPosition(-100, -100);
		}

		for(int i = 0; i < levelsToShow; i++){

			if(i + scrollIndex >= levelButtons.size()){
				break;
			}
			levelButtons.get(i + scrollIndex).setPosition(positionX, positionY + i * buttonSpacing);
			levelButtons.get(i + scrollIndex).draw(graphics);
			//levelButtons.get(i + scrollIndex).draw(graphics, positionX, positionY + i * buttonSpacing);
		}
		searchField.draw(graphics);
	}
}
