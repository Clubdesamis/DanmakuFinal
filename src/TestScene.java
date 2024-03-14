import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TestScene implements Scene {

    private String name;
    private BufferedImage testImage1;

    public TestScene() throws IOException {
        testImage1 = ImageIO.read(new File("marisa.JPG"));
    }

    public void init(){

    }

    public void render(Graphics graphics){
        System.out.println("TestScene render called in " + name);
        graphics.drawImage(testImage1, 100, 100, null);
    }
    public void simulate(){
        System.out.println("TestScene simulate called in " + name);
    }
    public void close(){

    }

    public void setName(String name){
        this.name = name;
    }

}
