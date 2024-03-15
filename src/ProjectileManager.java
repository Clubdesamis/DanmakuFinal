import java.awt.*;
import java.awt.image.BufferedImage;

public class ProjectileManager {

    public Projectile[] projectiles;

    private int projectileIndex;

    public ProjectileManager(){
        projectiles = new Projectile[Constants.PROJECTILE_COUNT];
        for(int i = 0; i < Constants.PROJECTILE_COUNT; i++){
            projectiles[i] = new Projectile();
        }
        projectileIndex = 0;
    }

    public void assignProjectile(double positionX, double positionY, double speedX, double speedY, BufferedImage image){
        int tempIt = projectileIndex;
        while(true){
            if(!projectiles[tempIt].enabled){
                projectiles[tempIt].enable(positionX, positionY, speedX, speedY, image);
                projectileIndex = (tempIt + 1) % (Constants.PROJECTILE_COUNT - 1);
                return;
            }else{
                tempIt++;
                tempIt %= (Constants.PROJECTILE_COUNT - 1);
            }
        }
    }

    public void assignProjectile(double positionX, double positionY, double speedX, double speedY, BufferedImage image, int size, int id){
        int tempIt = projectileIndex;
        while(true){
            if(!projectiles[tempIt].enabled){
                projectiles[tempIt].enable(positionX, positionY, speedX, speedY, image, size, id);
                projectileIndex = (tempIt + 1) % (Constants.PROJECTILE_COUNT - 1);
                return;
            }else{
                tempIt++;
                tempIt %= (Constants.PROJECTILE_COUNT - 1);
            }
        }
    }

    public void moveProjectiles(){
        for(int i = 0; i < Constants.PROJECTILE_COUNT; i++){
            if(projectiles[i].enabled){
                projectiles[i].move();
            }
        }
        removeOutOfBound();
    }

    public void drawProjectiles(Graphics g){
        for(int i = 0; i < Constants.PROJECTILE_COUNT; i++){
            if(projectiles[i].enabled){
                projectiles[i].draw(g);
            }
        }
    }

    public boolean checkCollision(double x, double y){
        for(int i = 0; i < Constants.PROJECTILE_COUNT; i++){
            if(projectiles[i].enabled){
                double distance = Math.sqrt((Math.pow(((double)projectiles[i].positionX - x), (double)2.0)) + (Math.pow(((double)projectiles[i].positionY - y), (double)2.0)));
                if(distance < projectiles[i].collisionRadius){
                    System.out.println("Collision detected!");
                    return true;
                }
            }
        }
        return false;
    }

    public void removeOutOfBound(){
        for(int i = 0; i < Constants.PROJECTILE_COUNT; i++){
            if(projectiles[i].enabled) {
                if (((projectiles[i].positionX < 0) || (projectiles[i].positionX > Constants.WINDOW_WIDTH)) || ((projectiles[i].positionY < 0) || (projectiles[i].positionY > Constants.WINDOW_HEIGHT))) {
                    projectiles[i].disable();
                }
            }
        }
    }

}
