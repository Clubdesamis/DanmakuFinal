import java.awt.image.BufferedImage;

public class InstructionMakeSpiral extends Instruction {

    private int positionX;
    private int positionY;
    private int projectileCount;
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

    public static final int POSITION_X = 0;
    public static final int POSITION_Y = 1;
    public static final int PROJECTILE_COUNT = 2;
    public static final int PROJECTILE_SPEED = 3;
    public static final int FIRING_SPEED = 4;
    public static final int ROTATION_SPEED = 5;
    public static final int INITIAL_OFFSET = 6;
    public static final int CLOCKWISE = 7;
    public static final int ID = 8;
    public static final int IMAGE = 9;

    public InstructionMakeSpiral(int positionX, int positionY, int projectileCount, double projectileSpeed, int firingSpeed, double rotationSpeed, double initialOffset, boolean clockwise, int id, BufferedImage image){
        this.positionX = positionX;
        this.positionY = positionY;
        this.projectileCount = projectileCount;
        this.projectileSize = image.getHeight();
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

            Game.projectileManager.assignProjectile(positionX + FIRING_DISTANCE * (double)Math.cos(Math.toRadians((double)_trajectory)), positionY + FIRING_DISTANCE * (double)Math.sin(Math.toRadians((double)_trajectory)), (double)(projectileSpeed * Math.cos(Math.toRadians((double)_trajectory))), (double)(projectileSpeed * Math.sin(Math.toRadians((double)_trajectory))), image, projectileSize, id);
            _trajectory += _tempOffset;

            frameCount = 0;
            if(clockwise){
                offset += rotationSpeed;
            }
            else{
                offset -= rotationSpeed;
            }
            projectilesShot++;
        }
        if(projectilesShot == projectileCount){
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


    public static Instruction build(String[] arguments){
        switch(arguments.length){
            case 10 ->{
                return new InstructionMakeSpiral(Integer.parseInt(arguments[POSITION_X]), Integer.parseInt(arguments[POSITION_Y]), Integer.parseInt(arguments[PROJECTILE_COUNT]), Double.parseDouble(arguments[PROJECTILE_SPEED]), Integer.parseInt(arguments[FIRING_SPEED]), Double.parseDouble(arguments[ROTATION_SPEED]), Double.parseDouble(arguments[INITIAL_OFFSET]), Boolean.parseBoolean(arguments[CLOCKWISE]), Integer.parseInt(arguments[ID]) ,Game.ressourceManager.getTexture(arguments[IMAGE]));
                //break;
            }
            default ->{
                System.out.println("Invalid argument count passed in builder of MakeSpiral");
                break;
            }
        }
        return null;
    }
}