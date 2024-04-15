import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class PlayableCharacter extends VisualComponent{

	private double positionX;
	private double positionY;
	private double speedX;
	private double speedY;
	private int shootingInterval;
	private int framesSinceLastShot;
	private BufferedImage regularProjectile;
	private AnimatedSprite sprite;
	private boolean[] keyInputs = {false, false, false, false, false};

	private final int KEY_UP = 0;
	private final int KEY_DOWN = 1;
	private final int KEY_LEFT = 2;
	private final int KEY_RIGHT = 3;
	private final int KEY_SHOOT = 4;

	private final double defaultPositionX = 350;
	private final double defaultPositionY = 500;
	private final int defaultShootingInterval = 8;
	private final double defaultProjectileSpeedY = -25.0;
	private final int width = 20;
	private final int height = 47;

	private final int projectileOffsetX = 10;
	private final int projectileOffsetY = 15;

	public PlayableCharacter(double speed, String spriteName, int spriteFramesPerFrame, String regularProjectileName){
		this.speedX = speed;
		this.speedY = speed;
		this.positionX = defaultPositionX;
		this.positionY = defaultPositionY;
		this.sprite = new AnimatedSprite((int)positionX, (int)positionY, spriteName, spriteFramesPerFrame, AnimatedSprite.BACK_AND_FORTH);
		sprite.setSize(width, height);
		this.regularProjectile = Game.ressourceManager.getTexture(regularProjectileName);
		shootingInterval = defaultShootingInterval;
		framesSinceLastShot = shootingInterval;
	}

	public double getPositionX() {
		return positionX;
	}

	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}

	public double getPositionY() {
		return positionY;
	}

	public void setPositionY(double positionY) {
		this.positionY = positionY;
	}

	public double getSpeedX() {
		return speedX;
	}

	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}

	public double getSpeedY() {
		return speedY;
	}

	public void setSpeedY(double speedY) {
		this.speedY = speedY;
	}

	public BufferedImage getRegularProjectile() {
		return regularProjectile;
	}

	public void setRegularProjectile(BufferedImage regularProjectile) {
		this.regularProjectile = regularProjectile;
	}

	public AnimatedSprite getSprite() {
		return sprite;
	}

	public void setSprite(AnimatedSprite sprite) {
		this.sprite = sprite;
	}

	public int getShootingInterval() {
		return shootingInterval;
	}

	public void setShootingInterval(int shootingInterval) {
		this.shootingInterval = shootingInterval;
	}

	public double getCenteredX(){
		return positionX + width / 2;
	}

	public double getCenteredY(){
		return positionY + height / 2;
	}
	public void shootProjectile(){
		framesSinceLastShot++;
		if(framesSinceLastShot >= shootingInterval){
			if(keyInputs[KEY_SHOOT]){
				framesSinceLastShot = 0;
				//System.out.println("Shooting projectiles from " + Double.toString(positionX) + " " + Double.toString(positionY));
				//System.out.println("Sprite is at " + Double.toString(sprite.positionX) + " " + Double.toString(sprite.positionY));
				Game.playerProjectileManager.assignProjectile(positionX - projectileOffsetX, positionY - projectileOffsetY, 0.0, defaultProjectileSpeedY, regularProjectile, 10, -111);
				Game.playerProjectileManager.assignProjectile(positionX + projectileOffsetX, positionY - projectileOffsetY, 0.0, defaultProjectileSpeedY, regularProjectile, 10, -111);
			}
		}
	}

	public void update(){

		if(keyInputs[KEY_UP])
			positionY -= speedY;
		if(keyInputs[KEY_DOWN])
			positionY += speedY;
		if(keyInputs[KEY_LEFT])
			positionX -= speedX;
		if(keyInputs[KEY_RIGHT])
			positionX += speedX;

		framesSinceLastShot++;
		shootProjectile();
		sprite.setPosition((int)positionX, (int)positionY);
		sprite.update();

		Game.enemyProjectileManager.checkCollision(positionX, positionY);
	}

	@Override
	public void notification(Observable.EventID id, AWTEvent e) {
		char keyCode = ((KeyEvent)e).getKeyChar();
		switch(id){
			case KEY_PRESSED -> {
				switch(keyCode){
					case Constants.UP_KEYCODE -> {
						keyInputs[KEY_UP] = true;
						break;
					}
					case Constants.DOWN_KEYCODE -> {
						keyInputs[KEY_DOWN] = true;
						break;
					}
					case Constants.LEFT_KEYCODE -> {
						keyInputs[KEY_LEFT] = true;
						break;
					}
					case Constants.RIGHT_KEYCODE -> {
						keyInputs[KEY_RIGHT] = true;
						break;
					}
					case Constants.SHOOT_KEYCODE -> {
						keyInputs[KEY_SHOOT] = true;
						break;
					}
				}
				break;
			}
			case KEY_RELEASED -> {
				switch(keyCode){
					case Constants.UP_KEYCODE -> {
						keyInputs[KEY_UP] = false;
						break;
					}
					case Constants.DOWN_KEYCODE -> {
						keyInputs[KEY_DOWN] = false;
						break;
					}
					case Constants.LEFT_KEYCODE -> {
						keyInputs[KEY_LEFT] = false;
						break;
					}
					case Constants.RIGHT_KEYCODE -> {
						keyInputs[KEY_RIGHT] = false;
						break;
					}
					case Constants.SHOOT_KEYCODE -> {
						keyInputs[KEY_SHOOT] = false;
						break;
					}
				}
			}
		}
	}

	@Override
	public void draw(Graphics graphics) {
		sprite.draw(graphics);
	}
}