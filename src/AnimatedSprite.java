import java.awt.*;
import java.awt.image.BufferedImage;

public class AnimatedSprite extends VisualComponent{

	private BufferedImage[] frames;
	private int framesPerFrame;
	private int frameCount;
	private int frameIndex;
	private int direction;
	private int mode;
	private int width;
	private int height;
	private Insets insets;

	public final static int BACK_AND_FORTH = 1;
	public final static int LOOP = 2;
	public final static int DIRECTION_FORWARD = 3;
	public final static int DIRECTION_BACKWARD = 4;

	public AnimatedSprite(int positionX, int positionY, String spriteName, int framesPerFrame, int mode){
		this.positionX = positionX;
		this.positionY = positionY;
		this.frames = Game.ressourceManager.getAnimatedSprite(spriteName);
		this.framesPerFrame = framesPerFrame;
		this.mode = mode;
		width = 0;
		height = 0;
		insets = Game.window.getInsets();
		frameCount = 0;
		frameIndex = 0;
		direction = DIRECTION_FORWARD;
	}

	public BufferedImage[] getFrames() {
		return frames;
	}

	public void setFrames(BufferedImage[] frames) {
		this.frames = frames;
	}

	public int getFramesPerFrame() {
		return framesPerFrame;
	}

	public void setFramesPerFrame(int framesPerFrame) {
		this.framesPerFrame = framesPerFrame;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public void setSize(int width, int height){
		this.width = width;
		this.height = height;
	}

	@Override
	public void notification(Observable.EventID id, AWTEvent e) {

	}

	@Override
	public void draw(Graphics graphics) {
		graphics.drawImage(frames[frameIndex], positionX + insets.left - width / 2, positionY + insets.top - height / 2, null);
	}

	public void update(){
		frameCount++;
		if(frameCount == framesPerFrame){
			frameCount = 0;

			if(direction == DIRECTION_FORWARD){
				if(frameIndex < frames.length - 1){
					frameIndex++;
				}
				else{
					direction = DIRECTION_BACKWARD;
				}
			}
			else{
				if(frameIndex > 0){
					frameIndex--;
				}
				else{
					direction = DIRECTION_FORWARD;
				}
			}

		}
	}
}