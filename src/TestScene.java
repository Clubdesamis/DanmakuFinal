import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TestScene implements Scene {

    private String name;
    private BufferedImage testImage1;
    private int x = 100;
    private int y = 100;

    public TestScene() {

    }

    public void init() throws IOException {
        testImage1 = ImageIO.read(new File("marisa.JPG"));
    }

    public void close(){
        testImage1 = null;
    }

    public void render(Graphics graphics){
        //System.out.println("TestScene render called in " + name);
        graphics.drawImage(testImage1, x, y, null);
    }
    public void simulate(){
        //System.out.println("TestScene simulate called in " + name);
        x++;
        y++;
    }

    public void setName(String name){
        this.name = name;
    }

}
