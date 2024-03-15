import java.awt.image.BufferedImage;

public class InstructionMakeSpiral extends Instruction {

    private int positionX;
    private int positionY;
    private int projectileCount;
    private int projectilesToShoot;
    private int projectileSize;
    private double projectileSpeed;
    private int firingSpeed;
    private double rotationSpeed;
    private boolean clockwise;
    private int internalID;
    private double initialOffset;
    private BufferedImage image;

    private final double FIRING_DISTANCE = 50;


    int frameCount = 0;
    int projectilesShot = 0;
    double offset = (double)0.0;



    public InstructionMakeSpiral(int positionX, int positionY, int projectileCount, int projectilesToShoot, int projectileSize, double projectileSpeed, int firingSpeed, double rotationSpeed, double initialOffset, boolean clockwise, int internalID, BufferedImage image){
        this.positionX = positionX;
        this.positionY = positionY;
        this.projectileCount = projectileCount;
        this.projectilesToShoot = projectilesToShoot;
        this.projectileSize = projectileSize;
        this.projectileSpeed = projectileSpeed;
        this.firingSpeed = firingSpeed;
        this.rotationSpeed = rotationSpeed;
        this.clockwise = clockwise;
        this.internalID = internalID;
        this.initialOffset = initialOffset;
        this.image = image;
    }
    public boolean execute(){
        frameCount++;
        if(frameCount == firingSpeed){
            double trajectory = initialOffset + offset;
            double tempOffset = (double)360.0 / (double)projectileCount;
            for(int i = 0; i < projectileCount; i++){
                Game.projectileManager.assignProjectile(positionX + FIRING_DISTANCE * (double)Math.cos(Math.toRadians((double)trajectory)), positionY + FIRING_DISTANCE * (double)Math.sin(Math.toRadians((double)trajectory)), (double)(projectileSpeed * Math.cos(Math.toRadians((double)trajectory))), (double)(projectileSpeed * Math.sin(Math.toRadians((double)trajectory))), image, projectileSize, id);
                trajectory += tempOffset;
            }
            frameCount = 0;
            if(clockwise){
                offset += rotationSpeed;
            }
            else{
                offset -= rotationSpeed;
            }
            projectilesShot++;
        }
        if(projectilesShot == projectilesToShoot){
            return true;
        }
        return false;
    }

}