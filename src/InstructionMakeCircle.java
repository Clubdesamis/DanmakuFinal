import java.awt.image.BufferedImage;

public class InstructionMakeCircle extends Instruction {
    private int positionX;
    private int positionY;
    private int projectileCount;
    private double initialOffset;
    private double speed;
    private int size;
    private int id;
    private BufferedImage image;

    private final double FIRING_DISTANCE = 0;

    public InstructionMakeCircle(int positionX, int positionY, int projectileCount, double initialOffset, double speed, BufferedImage image){
        this.positionX = positionX;
        this.positionY = positionY;
        this.projectileCount = projectileCount;
        this.initialOffset = initialOffset;
        this.speed = speed;
        this.image = image;
    }

    public InstructionMakeCircle(int positionX, int positionY, int projectileCount, double initialOffset, double speed, BufferedImage image, int size, int id){
        this.positionX = positionX;
        this.positionY = positionY;
        this.projectileCount = projectileCount;
        this.initialOffset = initialOffset;
        this.speed = speed;
        this.image = image;
        this.size = size;
        this.id = id;
    }

    public int getInstructionId(){
        return Constants.INSTRUCTION_MAKECIRCLE_INTERNAL_ID;
    }

    public boolean execute(){
        double trajectory = initialOffset;
        double offset = (double)360.0 / (double)projectileCount;
        double trajectorySinus;
        for(int i = 0; i < projectileCount; i++){
            //trajectorySinus = (double)Math.cos(Math.toRadians((double)trajectory));
            Game.projectileManager.assignProjectile(positionX + FIRING_DISTANCE * (double)Math.cos(Math.toRadians((double)trajectory)), positionY + FIRING_DISTANCE * (double)Math.sin(Math.toRadians((double)trajectory)), (double)(speed * Math.cos(Math.toRadians((double)trajectory))), (double)(speed * Math.sin(Math.toRadians((double)trajectory))), image, size, id);
            trajectory += offset;
        }
        return true;
    }
}