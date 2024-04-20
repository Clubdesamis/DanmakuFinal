import java.awt.*;
import java.awt.image.BufferedImage;

public class EnemyCharacter extends VisualComponent{

	private double positionX;
	private double positionY;
	private double targetPositionX;
	private double targetPositionY;
	private double speedX;
	private double speedY;
	private double speedScalar;

	private Insets insets;

	private int spriteIndex;
	private BufferedImage[] sprite;

	private final double defaultPositionX = 350;
	private final double defaultPositionY = 200;
	private final int SPRITE_WIDTH = 44;
	private final int SPRITE_HEIGHT = 53;


	public final int SPRITE_FORWARD = 0;
	public final int SPRITE_LEFT = 1;
	public final int SPRITE_RIGHT = 2;

	public final double collisionRadius = 30;

	public EnemyCharacter(double speed, String spriteName){
		speedScalar = speed;
		setPosition(defaultPositionX, defaultPositionY);
		setTargetPosition(defaultPositionX, defaultPositionY);
		insets = Game.window.getInsets();
		sprite = Game.ressourceManager.getAnimatedSprite(spriteName);
		spriteIndex = SPRITE_FORWARD;
	}

	public double getPositionX(){
		return positionX;
	}

	public double getPositionY(){
		return positionY;
	}

	public double getTargetPositionX() {
		return targetPositionX;
	}

	public double getTargetPositionY() {
		return targetPositionY;
	}

	public double getSpeedScalar(){
		return speedScalar;
	}

	public void setPosition(double positionX, double positionY){
		this.positionX = positionX;
		this.positionY = positionY;
	}

	public void resetPosition(){
		setPosition(defaultPositionX, defaultPositionY);
		speedX = 0;
		speedY = 0;
		spriteIndex = SPRITE_FORWARD;
		targetPositionX = positionX;
		targetPositionY = positionY;
	}

	public double getCollisionRadius(){
		return collisionRadius;
	}

	public void setTargetPosition(double targetPositionX, double targetPositionY){
		if((targetPositionX > Constants.GAME_WIDTH) || (targetPositionX < 0)){
			return;
		}
		if((targetPositionY > Constants.GAME_HEIGHT) || (targetPositionY < 0)){
			return;
		}
		double deltaX = Math.abs(positionX - targetPositionX);
		double deltaY = Math.abs(positionY - targetPositionY);
		double angle = Math.atan(deltaX / deltaY);
		speedY = speedScalar * Math.cos(angle);
		speedX = speedScalar * Math.sin(angle);
		this.targetPositionX = targetPositionX;
		this.targetPositionY = targetPositionY;
	}

	public void setSpeed(double speed){
		this.speedScalar = speed;
	}

	public void setSprite(BufferedImage[] sprite){
		this.sprite = sprite;
	}

	public void move(){
		if(positionX != targetPositionX){
			if(Math.abs(positionX - targetPositionX) <= speedX){
				positionX = targetPositionX;
			}
			else{
				if(targetPositionX > positionX){
					positionX += speedX;
				}
				else{
					positionX -= speedX;
				}
			}
		}

		if(positionY != targetPositionY){
			if(Math.abs(positionY - targetPositionY) <= speedY){
				positionY = targetPositionY;
			}
			else{
				if(targetPositionY > positionY){
					positionY += speedY;
				}
				else{
					positionY -= speedY;
				}
			}
		}
	}

	public void updateSprite(){
		//System.out.println(Integer.toString(spriteIndex));
		if(targetPositionX > positionX){
			spriteIndex = SPRITE_RIGHT;
		}
		else if(targetPositionX < positionX){
			spriteIndex = SPRITE_LEFT;
		}
		else{
			spriteIndex = SPRITE_FORWARD;
		}
	}

	@Override
	public void notification(Observable.EventID id, AWTEvent e) {

	}

	public void update(){
		move();
		updateSprite();
	}

	@Override
	public void draw(Graphics graphics) {
		//TODO ajouter les offsets avec la taille du sprite
		graphics.drawImage(sprite[spriteIndex], (int)positionX + insets.left - SPRITE_WIDTH / 2, (int)positionY + insets.top - SPRITE_HEIGHT / 2, null);
	}
}