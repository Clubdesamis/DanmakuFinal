import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DanmakuScene implements Scene{

    private BufferedImage verySmallPurpleProjectileImage;
    private BufferedImage verySmallRedProjectileImage;
    private BufferedImage mediumRedProjectileImage;
    private BufferedImage smallPurpleProjectileImage;

    public DanmakuScene() throws IOException {

    }

    public void init() throws IOException {
        verySmallPurpleProjectileImage = Game.ressourceManager.getTexture("VerySmallPurpleProjectile.png");
        smallPurpleProjectileImage = Game.ressourceManager.getTexture("SmallPurpleProjectile.png");
        verySmallRedProjectileImage = Game.ressourceManager.getTexture("VerySmallRedProjectile.png");
        mediumRedProjectileImage = Game.ressourceManager.getTexture("MediumRedProjectile.png");


        Game.scriptReader.loadScript("Script1.club");

        //InstructionParallel parallel1 = new InstructionParallel();
        //InstructionParallel parallel2 = new InstructionParallel();
        //InstructionParallel parallel3 = new InstructionParallel();

        InstructionSequence list1 = new InstructionSequence();

        list1.addInstruction(new InstructionMakeSpiral(200, 200, 1, 10, 51, (float)3.5, 4, (float)10.0, (float)40.0, true, 0, mediumRedProjectileImage));
        list1.addInstruction(new InstructionMakeCircle(200, 200, 32, (float)30.0, (float)4.0, smallPurpleProjectileImage, 31, 0));
        list1.addInstruction(new InstructionMakeSpiral(200, 200, 1, 10, 51, (float)3.5, 4, (float)10.0, (float)140.0, false, 0, mediumRedProjectileImage));
        list1.addInstruction(new InstructionMakeCircle(200, 200, 32, (float)30.0, (float)4.0, smallPurpleProjectileImage, 31, 0));

        list1.addInstruction(new InstructionMakeSpiral(400, 200, 1, 10, 51, (float)3.5, 4, (float)10.0, (float)40.0, true, 0, mediumRedProjectileImage));
        list1.addInstruction(new InstructionMakeCircle(400, 200, 32, (float)30.0, (float)4.0, smallPurpleProjectileImage, 31, 0));
        list1.addInstruction(new InstructionMakeSpiral(400, 200, 1, 10, 51, (float)3.5, 4, (float)10.0, (float)140.0, false, 0, mediumRedProjectileImage));
        list1.addInstruction(new InstructionMakeCircle(400, 200, 32, (float)30.0, (float)4.0, smallPurpleProjectileImage, 31, 0));

        //parallel3.addInstruction(parallel1);
        //parallel3.addInstruction(parallel2);

        //InstructionLoop loop1 = new InstructionLoop(0);
        //loop1.addInstruction(parallel3);
        //loop1.addInstruction(new InstructionWait(250));
        Game.projectileScript.addInstruction(list1);
        Game.projectileScript.addInstruction(list1);



        Game.projectileScript.addInstruction(new InstructionWait(20000));
        Game.projectileScript.addInstruction(new InstructionMakeSpiral(300, 200, 1, 10, 51, (float)3.5, 4, (float)10.0, (float)40.0, true, 0, mediumRedProjectileImage));
        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 32, (float)30.0, (float)4.0, smallPurpleProjectileImage, 31, 0));
        Game.projectileScript.addInstruction(new InstructionMakeSpiral(300, 200, 1, 10, 51, (float)3.5, 4, (float)10.0, (float)140.0, false, 0, mediumRedProjectileImage));
        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 32, (float)60.0, (float)4.0, smallPurpleProjectileImage, 31, 0));

        Game.projectileScript.addInstruction(new InstructionMakeSpiral(300, 200, 1, 10, 51, (float)3.5, 4, (float)10.0, (float)40.0, true, 0, mediumRedProjectileImage));
        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 32, (float)-30.0, (float)4.0, smallPurpleProjectileImage, 31, 0));
        Game.projectileScript.addInstruction(new InstructionMakeSpiral(300, 200, 1, 10, 51, (float)3.5, 4, (float)10.0, (float)140.0, false, 0, mediumRedProjectileImage));
        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 32, (float)-60.0, (float)4.0, smallPurpleProjectileImage, 31, 0));

        Game.projectileScript.addInstruction(new InstructionMakeSpiral(300, 200, 1, 10, 51, (float)3.5, 4, (float)10.0, (float)40.0, true, 0, mediumRedProjectileImage));
        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 32, (float)30.0, (float)4.0, smallPurpleProjectileImage, 31, 0));
        Game.projectileScript.addInstruction(new InstructionMakeSpiral(300, 200, 1, 10, 51, (float)3.5, 4, (float)10.0, (float)140.0, false, 0, mediumRedProjectileImage));
        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 32, (float)60.0, (float)4.0, smallPurpleProjectileImage, 31, 0));

        Game.projectileScript.addInstruction(new InstructionMakeSpiral(300, 200, 1, 10, 51, (float)3.5, 4, (float)10.0, (float)40.0, true, 0, mediumRedProjectileImage));
        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 32, (float)-30.0, (float)4.0, smallPurpleProjectileImage, 31, 0));
        Game.projectileScript.addInstruction(new InstructionMakeSpiral(300, 200, 1, 10, 51, (float)3.5, 4, (float)10.0, (float)140.0, false, 0, mediumRedProjectileImage));
        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 32, (float)-60.0, (float)4.0, smallPurpleProjectileImage, 31, 0));

        Game.projectileScript.addInstruction(new InstructionWait(500));

        Game.projectileScript.addInstruction(new InstructionMakeCircle(150, 350, 16, (float)0.0, (float)2.0, smallPurpleProjectileImage, 31, 0));
        //Game.projectileScript.addInstruction(new InstructionWait(1000));
        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 350, 16, (float)0.0, (float)2.0, smallPurpleProjectileImage, 31, 0));
        Game.projectileScript.addInstruction(new InstructionMakeCircle(450, 350, 16, (float)0.0, (float)2.0, smallPurpleProjectileImage, 31, 0));
        Game.projectileScript.addInstruction(new InstructionWait(1000));

        Game.projectileScript.addInstruction(new InstructionMakeCircle(150, 100, 8, (float)0.0, (float)2.5, verySmallPurpleProjectileImage, 21, 0));
        Game.projectileScript.addInstruction(new InstructionMakeCircle(450, 100, 8, (float)0.0, (float)2.5, verySmallPurpleProjectileImage, 21, 0));
        Game.projectileScript.addInstruction(new InstructionWait(100));

        Game.projectileScript.addInstruction(new InstructionMakeCircle(150, 150, 8, (float)10.0, (float)2.5, verySmallPurpleProjectileImage, 21, 0));
        Game.projectileScript.addInstruction(new InstructionMakeCircle(450, 150, 8, (float)-10.0, (float)2.5, verySmallPurpleProjectileImage, 21, 0));
        Game.projectileScript.addInstruction(new InstructionWait(100));

        Game.projectileScript.addInstruction(new InstructionMakeCircle(150, 200, 8, (float)20.0, (float)2.5, verySmallPurpleProjectileImage, 21, 0));
        Game.projectileScript.addInstruction(new InstructionMakeCircle(450, 200, 8, (float)-20.0, (float)2.5, verySmallPurpleProjectileImage, 21, 0));
        Game.projectileScript.addInstruction(new InstructionWait(100));

        Game.projectileScript.addInstruction(new InstructionMakeCircle(150, 250, 8, (float)30.0, (float)2.5, verySmallPurpleProjectileImage, 21, 0));
        Game.projectileScript.addInstruction(new InstructionMakeCircle(450, 250, 8, (float)-30.0, (float)2.5, verySmallPurpleProjectileImage, 21, 0));
        Game.projectileScript.addInstruction(new InstructionWait(100));

        Game.projectileScript.addInstruction(new InstructionMakeCircle(150, 300, 8, (float)40.0, (float)2.5, verySmallPurpleProjectileImage, 21, 0));
        Game.projectileScript.addInstruction(new InstructionMakeCircle(450, 300, 8, (float)-40.0, (float)2.5, verySmallPurpleProjectileImage, 21, 0));
        Game.projectileScript.addInstruction(new InstructionWait(100));

        Game.projectileScript.addInstruction(new InstructionMakeCircle(150, 350, 8, (float)50.0, (float)2.5, verySmallPurpleProjectileImage, 21, 0));
        Game.projectileScript.addInstruction(new InstructionMakeCircle(450, 350, 8, (float)-50.0, (float)2.5, verySmallPurpleProjectileImage, 21, 0));
        Game.projectileScript.addInstruction(new InstructionWait(100));

        Game.projectileScript.addInstruction(new InstructionMakeCircle(150, 400, 8, (float)60.0, (float)2.5, verySmallPurpleProjectileImage, 21, 0));
        Game.projectileScript.addInstruction(new InstructionMakeCircle(450, 400, 8, (float)-60.0, (float)2.5, verySmallPurpleProjectileImage, 21, 0));
        Game.projectileScript.addInstruction(new InstructionWait(100));

        Game.projectileScript.addInstruction(new InstructionMakeCircle(150, 450, 8, (float)70.0, (float)2.5, verySmallPurpleProjectileImage, 21, 0));
        Game.projectileScript.addInstruction(new InstructionMakeCircle(450, 450, 8, (float)-70.0, (float)2.5, verySmallPurpleProjectileImage, 21, 0));
        Game.projectileScript.addInstruction(new InstructionWait(100));

        Game.projectileScript.addInstruction(new InstructionMakeCircle(150, 500, 8, (float)80.0, (float)2.5, verySmallPurpleProjectileImage, 21, 0));
        Game.projectileScript.addInstruction(new InstructionMakeCircle(450, 500, 8, (float)-80.0, (float)2.5, verySmallPurpleProjectileImage, 21, 0));
        Game.projectileScript.addInstruction(new InstructionWait(100));

        Game.projectileScript.addInstruction(new InstructionMakeCircle(150, 550, 8, (float)0.0, (float)2.5, verySmallPurpleProjectileImage, 21, 0));
        Game.projectileScript.addInstruction(new InstructionMakeCircle(450, 550, 8, (float)0.0, (float)2.5, verySmallPurpleProjectileImage, 21, 0));

        Game.projectileScript.addInstruction(new InstructionWait(2500));

        Game.projectileScript.addInstruction(new InstructionMakeSpiral(300, 200, 24, 20, 21, (float)5.0, 3, (float)0.0, (float)0.0, true, 0, verySmallPurpleProjectileImage));

        Game.projectileScript.addInstruction(new InstructionMakeSpiral(300, 200, 24, 20, 21, (float)5.0, 3, (float)1.5, (float)0.0, true, 0, verySmallPurpleProjectileImage));
        Game.projectileScript.addInstruction(new InstructionWait(100));

        Game.projectileScript.addInstruction(new InstructionMakeSpiral(300, 200, 24, 20, 21, (float)5.0, 3, (float)1.5, (float)80.0, false, 0, verySmallPurpleProjectileImage));
        Game.projectileScript.addInstruction(new InstructionWait(100));

        Game.projectileScript.addInstruction(new InstructionMakeSpiral(300, 200, 24, 20, 21, (float)5.0, 3, (float)1.5, (float)0.0, true, 0, verySmallPurpleProjectileImage));
        Game.projectileScript.addInstruction(new InstructionWait(100));

        Game.projectileScript.addInstruction(new InstructionMakeSpiral(300, 200, 24, 20, 21, (float)5.0, 3, (float)1.5, (float)80.0, false, 0, verySmallPurpleProjectileImage));
        Game.projectileScript.addInstruction(new InstructionWait(1000));



        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 24, (float)0.0, (float)2.0, verySmallPurpleProjectileImage, 21, 0));
        Game.projectileScript.addInstruction(new InstructionWait(200));
        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 24, (float)40.0, (float)2.0, verySmallPurpleProjectileImage, 21, 0));
        Game.projectileScript.addInstruction(new InstructionWait(200));
        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 24, (float)80.0, (float)2.0, verySmallPurpleProjectileImage, 21, 0));

        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 18, (float)-30.0, (float)2.8, smallPurpleProjectileImage, 31, 0));
        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 14, (float)0.0, (float)3.2, mediumRedProjectileImage, 51, 0));
        Game.projectileScript.addInstruction(new InstructionWait(200));

        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 24, (float)0.0, (float)2.0, verySmallRedProjectileImage, 21, 0));
        Game.projectileScript.addInstruction(new InstructionWait(200));
        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 24, (float)-40.0, (float)2.0, verySmallRedProjectileImage, 21, 0));
        Game.projectileScript.addInstruction(new InstructionWait(200));
        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 24, (float)-80.0, (float)2.0, verySmallRedProjectileImage, 21, 0));

        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 18, (float)-30.0, (float)2.8, smallPurpleProjectileImage, 31, 0));
        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 14, (float)0.0, (float)3.2, mediumRedProjectileImage, 51, 0));
        Game.projectileScript.addInstruction(new InstructionWait(200));



        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 24, (float)0.0, (float)2.0, verySmallPurpleProjectileImage, 21, 0));
        Game.projectileScript.addInstruction(new InstructionWait(200));
        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 24, (float)40.0, (float)2.0, verySmallPurpleProjectileImage, 21, 0));
        Game.projectileScript.addInstruction(new InstructionWait(200));
        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 24, (float)80.0, (float)2.0, verySmallPurpleProjectileImage, 21, 0));
        //Game.projectileScript.addInstruction(new InstructionWait(250));

        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 18, (float)-30.0, (float)2.8, smallPurpleProjectileImage, 31, 0));
        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 14, (float)0.0, (float)3.2, mediumRedProjectileImage, 51, 0));
        Game.projectileScript.addInstruction(new InstructionWait(200));

        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 24, (float)0.0, (float)2.0, verySmallRedProjectileImage, 21, 0));
        Game.projectileScript.addInstruction(new InstructionWait(200));
        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 24, (float)-40.0, (float)2.0, verySmallRedProjectileImage, 21, 0));
        Game.projectileScript.addInstruction(new InstructionWait(200));
        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 24, (float)-80.0, (float)2.0, verySmallRedProjectileImage, 21, 0));

        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 18, (float)-30.0, (float)2.8, smallPurpleProjectileImage, 31, 0));
        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 14, (float)0.0, (float)3.2, mediumRedProjectileImage, 51, 0));
        Game.projectileScript.addInstruction(new InstructionWait(200));



        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 24, (float)0.0, (float)2.0, verySmallPurpleProjectileImage, 21, 0));
        Game.projectileScript.addInstruction(new InstructionWait(200));
        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 24, (float)40.0, (float)2.0, verySmallPurpleProjectileImage, 21, 0));
        Game.projectileScript.addInstruction(new InstructionWait(200));
        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 24, (float)80.0, (float)2.0, verySmallPurpleProjectileImage, 21, 0));
        //Game.projectileScript.addInstruction(new InstructionWait(250));

        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 18, (float)-30.0, (float)2.8, smallPurpleProjectileImage, 31, 0));
        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 14, (float)0.0, (float)3.2, mediumRedProjectileImage, 51, 0));
        Game.projectileScript.addInstruction(new InstructionWait(200));

        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 24, (float)0.0, (float)2.0, verySmallRedProjectileImage, 21, 0));
        Game.projectileScript.addInstruction(new InstructionWait(200));
        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 24, (float)-40.0, (float)2.0, verySmallRedProjectileImage, 21, 0));
        Game.projectileScript.addInstruction(new InstructionWait(200));
        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 24, (float)-80.0, (float)2.0, verySmallRedProjectileImage, 21, 0));

        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 18, (float)-30.0, (float)2.8, smallPurpleProjectileImage, 31, 0));
        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 14, (float)0.0, (float)3.2, mediumRedProjectileImage, 51, 0));
        Game.projectileScript.addInstruction(new InstructionWait(200));



        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 24, (float)0.0, (float)2.0, verySmallPurpleProjectileImage, 21, 0));
        Game.projectileScript.addInstruction(new InstructionWait(200));
        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 24, (float)40.0, (float)2.0, verySmallPurpleProjectileImage, 21, 0));
        Game.projectileScript.addInstruction(new InstructionWait(200));
        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 24, (float)80.0, (float)2.0, verySmallPurpleProjectileImage, 21, 0));
        //Game.projectileScript.addInstruction(new InstructionWait(250));

        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 18, (float)-30.0, (float)2.8, smallPurpleProjectileImage, 31, 0));
        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 14, (float)0.0, (float)3.2, mediumRedProjectileImage, 51, 0));
        Game.projectileScript.addInstruction(new InstructionWait(200));

        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 24, (float)0.0, (float)2.0, verySmallRedProjectileImage, 21, 0));
        Game.projectileScript.addInstruction(new InstructionWait(200));
        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 24, (float)-40.0, (float)2.0, verySmallRedProjectileImage, 21, 0));
        Game.projectileScript.addInstruction(new InstructionWait(200));
        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 24, (float)-80.0, (float)2.0, verySmallRedProjectileImage, 21, 0));

        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 18, (float)-30.0, (float)2.8, smallPurpleProjectileImage, 31, 0));
        Game.projectileScript.addInstruction(new InstructionMakeCircle(300, 200, 14, (float)0.0, (float)3.2, mediumRedProjectileImage, 51, 0));
        Game.projectileScript.addInstruction(new InstructionWait(200));

    }

    @Override
    public void close() {

    }

    @Override
    public void render(Graphics graphics) {
        Game.projectileManager.drawProjectiles(graphics);
    }

    @Override
    public void simulate() {
        Game.projectileScript.executeInstruction();
        Game.projectileManager.moveProjectiles();
    }

    @Override
    public void setName(String name) {

    }

}
