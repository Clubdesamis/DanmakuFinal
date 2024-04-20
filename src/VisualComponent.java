import java.awt.*;

public abstract class VisualComponent implements Observer {

	protected int sizeX;
	protected int sizeY;
	protected int positionX;
	protected int positionY;
	protected boolean isVisible = true;

	public abstract void draw(Graphics graphics);

	public void setVisible(boolean isVisible){
		this.isVisible = isVisible;
	}

	public void setPosition(int positionX, int positionY){
		this.positionX =  positionX;
		this.positionY = positionY;
	}

	public void setSize(int sizeX, int sizeY){
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}

}