import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class TextField extends VisualComponent{

	private String text;
	private String passwordText;
	private Font font;

	private Color selectedColor;
	private Color unSelectedColor;

	private Color textColor;

	private int clickCount = 0;
	private int maxLength;

	private Insets insets;

	private boolean isSelected;
	private boolean isPassword;

	public TextField(){
		text = "";
		passwordText = "";
		font = Game.ressourceManager.getFont("default_font");
		setBackgroundColor(Color.WHITE);

		insets = Game.window.getInsets();
		isSelected = false;
		isPassword = false;
		maxLength = 10;
	}

	public void setText(String text){
		this.text = text;
	}

	public void setMaxLength(int maxLength){
		this.maxLength = maxLength;
	}

	public void setTextColor(Color color){
		textColor = color;
	}

	public void setFont(String fontId){
		if(Game.ressourceManager.getFont(fontId) != null){
			font = Game.ressourceManager.getFont(fontId);
		}
	}

	public void setBackgroundColor(Color color){
		this.selectedColor = color;
		if(selectedColor.getAlpha() > 0){
			this.unSelectedColor = new Color(selectedColor.getRed(), selectedColor.getGreen(), selectedColor.getBlue(), selectedColor.getAlpha() / 2);
		}
		else{
			this.unSelectedColor = color;
		}

	}

	public void setIsPassword(boolean isPassword){
		this.isPassword = isPassword;
	}


	@Override
	public void draw(Graphics graphics) {
		graphics.setFont(font);

		if(!isSelected){
			graphics.setColor(unSelectedColor);
		}
		else{
			graphics.setColor(selectedColor);
		}
		graphics.drawRect(positionX + insets.left, positionY - insets.top, sizeX, sizeY);

		graphics.setColor(textColor);
		if(isPassword){
			graphics.drawString(passwordText, positionX + insets.left + (int)(0.1 * font.getSize()), positionY - insets.top + (int)(1.0 * font.getSize()));
		}
		else{
			graphics.drawString(text, positionX + insets.left + (int)(0.1 * font.getSize()), positionY - insets.top + (int)(1.0 * font.getSize()));
		}
	}

	@Override
	public void notification(Observable.EventID id, AWTEvent e) {
		switch(id){
			case MOUSE_CLICKED -> {
				int x = ((MouseEvent)e).getX();
				int y = ((MouseEvent)e).getY();
				if((x >= positionX + insets.left && x <= positionX + insets.left + sizeX) && (y >= positionY - insets.top && y <= positionY - insets.top + sizeY)){
					clickCount++;
					//System.out.println(Integer.toString(clickCount));
					isSelected = true;
				}
				else{
					isSelected = false;
				}
				break;
			}
			case KEY_TYPED -> {
				if(isSelected){
					//System.out.println(Integer.toString((int)((KeyEvent)e).getKeyChar()));
					int keyCode = (int)((KeyEvent)e).getKeyChar();
					switch(keyCode){
						case 0x08 -> {
							if(text.length() > 0){
								text = text.substring(0, text.length() - 1);
								passwordText = passwordText.substring(0, passwordText.length() - 1);
							}
						}
						case 0x1B -> {
							isSelected = false;
						}
						default -> {
							if(text.length() < maxLength){
								text += (char)keyCode;
								passwordText += "*";
							}
						}
					}
				}
			}
		}
	}
}