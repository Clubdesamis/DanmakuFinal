import java.awt.*;
import java.awt.image.BufferedImage;

public class ProjectileManager {

    public Projectile[] projectiles;

    private int projectileIndex;
    private int bufferSize;
    private boolean deleteOnCollision;

    public ProjectileManager(int bufferSize, boolean deleteOnCollision){
        projectiles = new Projectile[bufferSize];
        this.bufferSize = bufferSize;
        this.deleteOnCollision = deleteOnCollision;
        for(int i = 0; i < bufferSize; i++){
            projectiles[i] = new Projectile();
        }
        projectileIndex = 0;
    }

    public void clear(){
        for(int i = 0; i < projectiles.length; i++){
            projectiles[i].disable();
        }
    }

    public void assignProjectile(double positionX, double positionY, double speedX, double speedY, BufferedImage image, int size, int id){
        int tempIt = projectileIndex;
        while(true){
            if(!projectiles[tempIt].enabled){
                projectiles[tempIt].enable(positionX, positionY, speedX, speedY, image, size, id);
                projectileIndex = (tempIt + 1) % (bufferSize - 1);
                return;
            }else{
                tempIt++;
                tempIt %= (bufferSize - 1);
            }
        }
    }

    public void assignProjectile(double positionX, double positionY, double directionX, double directionY, double speedScalar, BufferedImage image, int size, int id){
        int tempIt = projectileIndex;
        while(true){
            if(!projectiles[tempIt].enabled){
                projectiles[tempIt].enable(positionX, positionY, directionX, directionY, speedScalar, image, size, id);
                projectileIndex = (tempIt + 1) % (bufferSize - 1);
                return;
            }else{
                tempIt++;
                tempIt %= (bufferSize - 1);
            }
        }
    }

    public void moveProjectiles(){
        for(int i = 0; i < bufferSize; i++){
            if(projectiles[i].enabled){
                projectiles[i].move();
            }
        }
        removeOutOfBound();
    }

    public void drawProjectiles(Graphics g){
        for(int i = 0; i < bufferSize; i++){
            if(projectiles[i].enabled){
                projectiles[i].draw(g);
            }
        }
    }

    public boolean checkCollision(double x, double y){
        int collisionCount = 0;
        for(int i = 0; i < bufferSize; i++){
            if(projectiles[i].enabled){
                double distance = Math.sqrt((Math.pow(((double)projectiles[i].positionX - x), (double)2.0)) + (Math.pow(((double)projectiles[i].positionY - y), (double)2.0)));
                if(distance < projectiles[i].collisionRadius){
                    //System.out.println("Collision detected!");
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkCollisionWithRadius(double x, double y, double radius){
        for(int i = 0; i < bufferSize; i++){
            if(projectiles[i].enabled){
                double distance = Math.sqrt((Math.pow(((double)projectiles[i].positionX - x), (double)2.0)) + (Math.pow(((double)projectiles[i].positionY - y), (double)2.0)));
                //System.out.println(Double.toString(distance));
                if(distance < radius){
                    //System.out.println("Collision detected!");
                    if(deleteOnCollision){
                        projectiles[i].disable();
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public int getCollisionCountWithRadius(double x, double y, double radius){
        int collisionCount = 0;
        for(int i = 0; i < bufferSize; i++){
            if(projectiles[i].enabled){
                double distance = Math.sqrt((Math.pow(((double)projectiles[i].positionX - x), (double)2.0)) + (Math.pow(((double)projectiles[i].positionY - y), (double)2.0)));
                //System.out.println(Double.toString(distance));
                if(distance < radius){
                    //System.out.println("Collision detected!");
                    if(deleteOnCollision){
                        projectiles[i].disable();
                    }
                    collisionCount++;
                }
            }
        }
        return collisionCount;
    }

    public void removeOutOfBound(){
        for(int i = 0; i < bufferSize; i++){
            if(projectiles[i].enabled) {
                if (((projectiles[i].positionX < 0) || (projectiles[i].positionX > 700)) || ((projectiles[i].positionY < 0) || (projectiles[i].positionY > Constants.WINDOW_HEIGHT))) {
                    projectiles[i].disable();
                }
            }
        }
    }

    public void printAllActiveProjectiles(){
        for(int i = 0; i < bufferSize; i++){
            if(projectiles[i].enabled){
                System.out.println(Double.toString(projectiles[i].positionX) + "   " + Double.toString(projectiles[i].positionY));
            }
        }
        System.out.print("\n\n\n\n\n\n\n\n\n\n");
    }

    public void setTargetSpeedForID(double targetSpeedScalar, double accelerationScalar, int internalID){
        for(int i = 0; i < bufferSize; i++){
            if(projectiles[i].getInternalID() == internalID){
                projectiles[i].setTargetSpeed(targetSpeedScalar, accelerationScalar);
            }
        }
    }

    public void setRevolutionSpeedForID(double revolutionCenterX, double revolutionCenterY, double revolutionSpeed, int internalID){
        for(int i = 0; i < bufferSize; i++){
            if(projectiles[i].getInternalID() == internalID){
                projectiles[i].setRevolution(revolutionCenterX, revolutionCenterY, revolutionSpeed);
            }
        }
    }
}