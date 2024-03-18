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



    public InstructionMakeSpiral(int positionX, int positionY, int projectileCount, int projectilesToShoot, int projectileSize, double projectileSpeed, int firingSpeed, double rotationSpeed, double initialOffset, boolean clockwise, int id, BufferedImage image){
        this.positionX = positionX;
        this.positionY = positionY;
        this.projectileCount = projectileCount;
        this.projectilesToShoot = projectilesToShoot;
        this.projectileSize = projectileSize;
        this.projectileSpeed = projectileSpeed;
        this.firingSpeed = firingSpeed;
        this.rotationSpeed = rotationSpeed;
        this.initialOffset = initialOffset;
        this.clockwise = clockwise;
        this.internalID = id;
        this.image = image;
    }

    public int getInstructionId(){
        return Constants.INSTRUCTION_MAKESPIRAL_INTERNAL_ID;
    }
    public boolean execute(){

        frameCount++;
        if(frameCount == firingSpeed){
            double _trajectory = initialOffset + offset;
            double _tempOffset = (double)360.0 / (double)projectileCount;
            for(int i = 0; i < projectileCount; i++){
                Game.projectileManager.assignProjectile(positionX + FIRING_DISTANCE * (double)Math.cos(Math.toRadians((double)_trajectory)), positionY + FIRING_DISTANCE * (double)Math.sin(Math.toRadians((double)_trajectory)), (double)(projectileSpeed * Math.cos(Math.toRadians((double)_trajectory))), (double)(projectileSpeed * Math.sin(Math.toRadians((double)_trajectory))), image, projectileSize, id);
                _trajectory += _tempOffset;
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
            cleanVariables();
            return true;
        }
        return false;
    }

    public void cleanVariables(){
        frameCount = 0;
        projectilesShot = 0;
        offset = (double)0.0;
        //System.out.println("Variables cleaned!");
    }
}