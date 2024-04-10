import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DanmakuScene implements Scene{


    private Insets insets;

    public DanmakuScene() throws IOException {

    }

    public void init() throws IOException {

        insets = Game.window.getInsets();
        //Game.projectileScript.addInstruction(Game.scriptReader.loadScript("Script1.club"));

    }

    @Override
    public void close() {

    }

    @Override
    public void render(Graphics graphics) {

        //graphics.drawImage(Game.ressourceManager.getTexture("UIBackground1.png"), 822 + insets.left, 0 + insets.top, null);
        graphics.drawImage(Game.ressourceManager.getTexture("Background3.png"), 0 + insets.left, 0 + insets.top, null);

        Game.projectileManager.drawProjectiles(graphics);

        graphics.drawImage(Game.ressourceManager.getTexture("UIBackground3.png"), 700 + insets.left, 0 + insets.top, null);
        graphics.drawImage(Game.ressourceManager.getTexture("LoginShade.png"), 700 + insets.left, 0 + insets.top, null);


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
