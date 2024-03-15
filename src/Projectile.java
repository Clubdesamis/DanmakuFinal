import java.awt.*;
import java.awt.image.BufferedImage;

public class Projectile {

    final private int x = 0;
    final private int y = 1;
    final private int DEFAULT_SIZE = 21;

    final private int DEFAULT_COLLISION_RADIUS = 5;
    public double speedX = 0.0;
    public double speedY = 0.0;
    public double positionX = 0.0;
    public double positionY = 0.0;

    public double collisionRadius = 0.0;
    private BufferedImage image;
    private int internalID = -1;
    private int size;

    public boolean enabled = false;

    public Projectile(double positionX, double positionY, double speedX, double speedY, BufferedImage image){
        this.positionX = positionX;
        this.positionY = positionY;
        this.speedX = speedX;
        this.speedY = speedY;
        this.image = image;
        this.size = DEFAULT_SIZE;
    }

    public Projectile(double positionX, double positionY, double speedX, double speedY, int size, BufferedImage image){
        this.positionX = positionX;
        this.positionY = positionY;
        this.speedX = speedX;
        this.speedY = speedY;
        this.image = image;
        this.size = size;
    }

    public Projectile(){
        this.disable();
    }

    public void enable(double positionX, double positionY, double speedX, double speedY, BufferedImage image){
        this.enabled = true;
        this.positionX = positionX;
        this.positionY = positionY;
        this.speedX = speedX;
        this.speedY = speedY;
        this.image = image;
        this.size = DEFAULT_SIZE;
        this.collisionRadius = DEFAULT_COLLISION_RADIUS;
    }

    public void enable(double positionX, double positionY, double speedX, double speedY, BufferedImage image, int size, int id){
        this.enabled = true;
        this.positionX = positionX;
        this.positionY = positionY;
        this.speedX = speedX;
        this.speedY = speedY;
        this.image = image;
        this.internalID = id;
        this.size = size;
        this.collisionRadius = size / 4;
    }

    public void disable(){
        this.positionX = 0.0;
        this.positionY = 0.0;
        this.speedX = 0.0;
        this.speedY = 0.0;
        this.enabled = false;
        this.internalID = -1;
    }

    public void setSpeed(double speedX, double speedY){
        this.speedX = speedX;
        this.speedY = speedY;
    }

    public void setPosition(double positionX, double positionY){
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public void move(){
        positionX += speedX;
        positionY += speedY;

    }

    public void draw(Graphics graphics){
        graphics.drawImage(image, (int)(positionX + 7) - size / 2, (int)(positionY + 30) - size / 2, null);
    }

}
