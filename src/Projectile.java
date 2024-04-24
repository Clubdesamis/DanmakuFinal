import java.awt.*;
import java.awt.image.BufferedImage;

public class Projectile {

    final private int x = 0;
    final private int y = 1;
    final private int DEFAULT_SIZE = 21;

    final private int DEFAULT_COLLISION_RADIUS = 5;

    public double positionX = 0.0;
    public double positionY = 0.0;
    public double directionX = 0.0;
    public double directionY = 0.0;
    public double speedX = 0.0;
    public double speedY = 0.0;
    public double accelerationScalar;
    public double speedScalar = 0.0;
    public double targetSpeedScalar = 0.0;
    public double revolutionCenterX = 0.0;
    public double revolutionCenterY = 0.0;
    //public double revolutionCenterAngle = 0.0;
    public double revolutionSpeed = 0.0;

    public double collisionRadius = 0.0;
    private BufferedImage image;
    private int internalID = -1;
    private int size;

    public boolean enabled = false;

    //If targetSpeedScalar is positive, projectile will keep going in the same direction
    //If targetSpeedScalar is negative, projectile will start accelerating the opposite way
    //accelerationScalar is the rate scalar of acceleration, its sign is irrelevant
    public void setTargetSpeed(double targetSpeedScalar, double accelerationScalar){
        //this.targetSpeedScalar = targetSpeedScalar;
        this.accelerationScalar = accelerationScalar;
        this.targetSpeedScalar = targetSpeedScalar;

    }

    public Projectile(){
        this.disable();
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
        setTargetSpeed(10.0, 0.4);
    }

    public void enable(double positionX, double positionY, double directionX, double directionY, double speedScalar, BufferedImage image, int size, int id){
        this.enabled = true;
        this.positionX = positionX;
        this.positionY = positionY;
        this.directionX = directionX;
        this.directionY = directionY;
        this.speedScalar = speedScalar;
        this.targetSpeedScalar = speedScalar;
        this.image = image;
        this.size = size;
        this.internalID = id;
        this.collisionRadius = size / 4;
        this.speedX = directionX * speedScalar;
        this.speedY = directionY * speedScalar;
        //setRevolution(Game.enemyCharacter.getPositionX(), Game.enemyCharacter.getPositionY(), 4.0);
    }

    public void disable(){
        this.positionX = 0.0;
        this.positionY = 0.0;
        this.speedX = 0.0;
        this.speedY = 0.0;
        this.accelerationScalar = 0.0;
        this.speedScalar = 0.0;
        this.targetSpeedScalar = 0.0;
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

    public void setRevolution(double revolutionCenterX, double revolutionCenterY, double revolutionSpeed){
        this.revolutionCenterX = revolutionCenterX;
        this.revolutionCenterY = revolutionCenterY;
        this.revolutionSpeed = revolutionSpeed;
    }



    public void computeAcceleration(){
        if(targetSpeedScalar != speedScalar){
            if(Math.signum(targetSpeedScalar) == 1.0){
                if(accelerationScalar > 0){
                    if(speedScalar >= targetSpeedScalar){
                        speedScalar = targetSpeedScalar;
                        accelerationScalar = 0.0;
                        targetSpeedScalar = 0.0;
                    }
                }
                else if(accelerationScalar < 0){
                    if(speedScalar <= targetSpeedScalar){
                        speedScalar = targetSpeedScalar;
                        accelerationScalar = 0.0;
                        targetSpeedScalar = 0.0;
                    }
                }
            }
            else if(Math.signum(targetSpeedScalar) == 0.0){
                if(accelerationScalar > 0){
                    if(speedScalar >= targetSpeedScalar){
                        speedScalar = targetSpeedScalar;
                        accelerationScalar = 0.0;
                        targetSpeedScalar = 0.0;
                    }
                }
                else if(accelerationScalar < 0){
                    if(speedScalar <= targetSpeedScalar){
                        speedScalar = targetSpeedScalar;
                        accelerationScalar = 0.0;
                        targetSpeedScalar = 0.0;
                    }
                }
            }
        }
    }

    public void computeRevolution(){
        if(revolutionSpeed != 0.0){
            double _distanceX = Math.abs(positionX) - Math.abs(revolutionCenterX);
            double _distanceY = Math.abs(positionY) - Math.abs(revolutionCenterY);
            double _distanceScalar = Math.sqrt(Math.pow(_distanceX, 2) + Math.pow(_distanceY, 2));

            double _angle = 0.0;

            if(_distanceX > 0.0 && _distanceY < 0.0){
                //Quadrant 1
                _angle = 0.0;
                _angle += Math.toDegrees(Math.asin(Math.abs(_distanceY) / _distanceScalar));
            }
            else if(_distanceX < 0.0 && _distanceY < 0.0){
                //Quadrant 2
                _angle = 90.0;
                _angle += 90.0 - Math.toDegrees(Math.asin(Math.abs(_distanceY) / _distanceScalar));
            }
            else if(_distanceX < 0.0 && _distanceY > 0.0){
                //Quadrant 3
                _angle = 180.0;
                _angle += Math.toDegrees(Math.asin(Math.abs(_distanceY) / _distanceScalar));
            }
            else if(_distanceX > 0.0 && _distanceY > 0.0){
                //Quadrant 4
                _angle = 270.0;
                _angle += 90.0 - Math.toDegrees(Math.asin(Math.abs(_distanceY) / _distanceScalar));
            }
            else if( _distanceX > 0.0 && _distanceY == 0.0){
                //Axe des X a droite
                _angle = 0.0;
            }
            else if(_distanceX < 0.0 && _distanceY == 0.0){
                //Axe des X a gauche
                _angle = 180.0;
            }
            else if(_distanceX == 0.0 && _distanceY < 0.0){
                //Axe des Y en haut
                _angle = 90.0;
            }
            else if(_distanceX == 0.0 && _distanceY > 0.0){
                //Axe des Y en bas
                _angle = 270.0;
            }


            _angle += revolutionSpeed;

            double _newPositionX = revolutionCenterX + _distanceScalar * Math.cos(Math.toRadians(_angle));
            double _newPositionY = revolutionCenterY - _distanceScalar * Math.sin(Math.toRadians(_angle));

            positionX = _newPositionX;
            positionY = _newPositionY;

            directionX = Math.cos(Math.toRadians(_angle));
            directionY = -1 * Math.sin(Math.toRadians(_angle));


        }
    }

    public void move(){
        positionX += speedScalar * directionX;
        positionY += speedScalar * directionY;
        speedScalar += accelerationScalar;

        computeAcceleration();
        computeRevolution();

    }

    public void draw(Graphics graphics){
        graphics.drawImage(image, (int)(positionX + 7) - size / 2, (int)(positionY + 30) - size / 2, null);
    }

    public int getInternalID(){
        return this.internalID;
    }
}