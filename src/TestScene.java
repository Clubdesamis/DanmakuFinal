import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.awt.Image.SCALE_DEFAULT;

public class TestScene implements Scene {

    private String name;
    private Image testImage1;
    private int x = 100;
    private int y = 100;

    public TestScene() {

    }

    public void init() throws IOException {
        File directory = new File("Scripts");
        File[] files = directory.listFiles();
        for(int i = 0; i < files.length; i++){
            //System.out.println(files[i].getName());
        }
    }

    public void close(){
        testImage1 = null;
    }

    public void render(Graphics graphics){

    }
    public void simulate(){

    }

    public void setName(String name){
        this.name = name;
    }

}