import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.Hashtable;

public class MapScroller extends VisualComponent{

	private ArrayList<ScriptHeader> headers;
	private ArrayList<ScriptHeader> filteredHeaders;
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
	private int searchCharacterCount;

	private final int textOffsetX = 15;
	private final int textOffsetY = 28;

	private final int textFieldOffsetX = 0;
	private final int textFieldOffsetY = -40;

	public MapScroller(int positionX, int positionY, int levelsToShow, int buttonsWidth, int buttonsHeight, String fontId, Color buttonsColor, Color buttonsHoverColor){
		headers = new ArrayList<ScriptHeader>();
		filteredHeaders =  new ArrayList<ScriptHeader>();
		levelButtons = new ArrayList<Button>();
		searchCharacterCount = 0;
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
		sortHeaders();
		loadLevels();
		refreshButtons();
	}

	public void loadHeaders(){
		headers = Game.scriptReader.readAllHeaders();
	}

	public void loadLevels(){
		for(int i = 0; i < filteredHeaders.size(); i++){
			levelButtons.add(new Button(-100, -100, buttonsWidth, buttonsHeight, filteredHeaders.get(i).getAttribute("Name"), buttonsColor, buttonsHoverColor, Color.WHITE, fontId));
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
				//System.out.println(Integer.toString(scrollIndex));
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

	public void refreshButtons(){
		filteredHeaders.clear();

		for(int i = 0; i < headers.size(); i++){
			if(startsWith(headers.get(i).getAttribute("Name"), searchField.getText())){
				filteredHeaders.add(headers.get(i));
			}
		}

		levelButtons.clear();
		loadLevels();
	}

	public void sortHeaders(){

		for(int i = 0; i < headers.size(); i++){
			for(int j = i; j < headers.size(); j++){
				if(headers.get(i).getAttribute("Name").compareTo(headers.get(j).getAttribute("Name")) > 0){
					Hashtable<String, String> tempHeader = headers.get(i).getContents();
					headers.get(i).setContents(headers.get(j).getContents());
					headers.get(j).setContents(tempHeader);
				}

			}
		}
	}

	public ScriptHeader getSelectedHeader(){
		for(int i = 0; i < levelButtons.size(); i++){
			if(levelButtons.get(i).isClicked()){
				return filteredHeaders.get(i);
			}
		}
		ScriptHeader value = new ScriptHeader();
		value.setAttribute("Name", "");
		value.setAttribute("Difficulty", "");
		value.setAttribute("Creator", "");
		return value;
	}

	public boolean startsWith(String source, String searched){
		int size = 0;
		boolean result = true;
		if(searched.compareTo("") == 0){
			return result;
		}
		if(source.length() > searched.length()){
			size = searched.length();
		}
		else{
			size = source.length();
		}

		for(int i = 0; i < size; i++){
			if(source.charAt(i) != searched.charAt(i)){
				result = false;
			}
		}
		return result;
	}

	@Override
	public void draw(Graphics graphics) {

		if(searchCharacterCount != searchField.getText().length()){
			searchCharacterCount = searchField.getText().length();
			//System.out.println("Search field has been modified");
			refreshButtons();
		}

		for(int i = 0; i < levelButtons.size(); i++){
			levelButtons.get(i).setPosition(-100, -100);
		}

		for(int i = 0; i < levelsToShow; i++){

			if(i + scrollIndex >= levelButtons.size()){
				break;
			}
			levelButtons.get(i + scrollIndex).setPosition(positionX, positionY + i * buttonSpacing);
			levelButtons.get(i + scrollIndex).draw(graphics);
		}
		searchField.draw(graphics);
		//System.out.println(getSelectedHeader().getAttribute("Name"));
	}
}
