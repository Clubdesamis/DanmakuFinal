import java.awt.*;
import java.awt.event.MouseEvent;

public class Button extends VisualComponent{

	private Color backgroundColor;
	private Color hoverColor;

	private Color textColor;
	private Insets insets;
	private boolean isClicked;
	private boolean isHovered;
	private int textPositionOffsetX;
	private int textPositionOffsetY;
	private Font textFont;



	private String text;

	public Button(int positionX, int positionY, int sizeX, int sizeY, String text, Color backgroundColor, Color hoverColor, Color textColor, String fontId){
		this.textFont = Game.ressourceManager.getFont(fontId);
		this.text = text;
		setBackgroundColor(backgroundColor);
		setHoverColor(hoverColor);
		setTextColor(textColor);
		this.setPosition(positionX, positionY);
		this.setSize(sizeX, sizeY);
		insets = Game.window.getInsets();
		isClicked = false;
		isHovered = false;
		textPositionOffsetX = 0;
		textPositionOffsetY = 0;
	}

	public void setTextPositionOffset(int x, int y){
		this.textPositionOffsetX = x;
		this.textPositionOffsetY = y;
	}

	public Color getTextColor() {
		return textColor;
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setBackgroundColor(Color color){
		this.backgroundColor = color;
	}

	public void setHoverColor(Color color){
		this.hoverColor = color;
	}

	public boolean isClicked(){
		return isClicked;
	}

	public void setClicked(boolean state){
		isClicked = state;
	}


	@Override
	public void draw(Graphics graphics) {

		if(!isHovered){
			graphics.setColor(backgroundColor);
		}
		else{
			graphics.setColor(hoverColor);
		}
		graphics.fillRect(positionX + insets.left, positionY - insets.top, sizeX, sizeY);

		graphics.setColor(textColor);
		graphics.setFont(textFont);
		graphics.drawString(text, positionX + textPositionOffsetX + insets.left, positionY + textPositionOffsetY - insets.top);
	}

	public void draw(Graphics graphics, int argumentPositionX, int argumentPositionY){
		if(!isHovered){
			graphics.setColor(backgroundColor);
		}
		else{
			graphics.setColor(hoverColor);
		}
		graphics.fillRect(argumentPositionX + insets.left, argumentPositionY - insets.top, sizeX, sizeY);

		graphics.setColor(textColor);
		graphics.setFont(textFont);
		graphics.drawString(text, argumentPositionX + textPositionOffsetX + insets.left, argumentPositionY + textPositionOffsetY - insets.top);
	}

	@Override
	public void notification(Observable.EventID id, AWTEvent e) {
		switch(id){
			case MOUSE_CLICKED -> {
				int x = ((MouseEvent)e).getX();
				int y = ((MouseEvent)e).getY();
				if((x >= positionX + insets.left && x <= positionX + insets.left + sizeX) && (y >= positionY - insets.top && y <= positionY - insets.top + sizeY)){
					isClicked = true;
				}
				else{
					isClicked = false;
				}
				break;
			}
			case MOUSE_MOVED -> {
				int x = ((MouseEvent)e).getX();
				int y = ((MouseEvent)e).getY();
				if((x >= positionX + insets.left && x <= positionX + insets.left + sizeX) && (y >= positionY - insets.top && y <= positionY - insets.top + sizeY)){
					isHovered = true;
				}
				else{
					isHovered = false;
				}
			}
		}
	}
}