import java.awt.*;

public class Label extends VisualComponent{

	private String text;
	private Font font;
	private Color color;
	private Insets insets;

	public Label(){
		setText("");
		setColor(Color.WHITE);
		insets = Game.window.getInsets();
	}

	public Label(String text, String fontId, Color color){
		this.text = text;
		setFont(fontId);
		this.color = color;
		insets = Game.window.getInsets();
	}

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	public void setFont(String fontId){
		if(Game.ressourceManager.getFont(fontId) != null){
			font = Game.ressourceManager.getFont(fontId);
		}
		else{
			font = Game.ressourceManager.getFont("default_font");
		}
	}

	public void setColor(Color color){
		this.color = color;
	}

	@Override
	public void notification(Observable.EventID id, AWTEvent e) {

	}

	@Override
	public void draw(Graphics graphics) {
		graphics.setColor(color);
		graphics.setFont(font);
		graphics.drawString(text, positionX + insets.left, positionY - insets.top + (int)(0.8 * font.getSize()));
	}
}