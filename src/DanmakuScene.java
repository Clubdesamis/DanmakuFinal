import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DanmakuScene implements Scene{

    public DanmakuScene() throws IOException {

    }

    public void init() throws IOException {
        
        Game.projectileScript.addInstruction(Game.scriptReader.loadScript("Script1.club"));

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
