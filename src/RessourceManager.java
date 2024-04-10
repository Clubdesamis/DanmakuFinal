import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Hashtable;

public class RessourceManager {
    public Hashtable<String, BufferedImage> textures;
    public Hashtable<String, BufferedImage> backSprites;
    public Hashtable<String, Font> fonts;

    public RessourceManager(){
        textures = new Hashtable<String, BufferedImage>();
        backSprites = new Hashtable<String, BufferedImage>();

        fonts = new Hashtable<String, Font>();

        loadTextures();
        loadBackSprites();
        loadFonts();
    }

    private void loadTextures(){
        File directory = new File(Constants.TEXTURE_FOLDER);
        File[] files = directory.listFiles();
        try{
            for(int i = 0; i < files.length; i++){
                //System.out.println(files[i].getName());
                addTexture(files[i].getName(), ImageIO.read(new File(Constants.TEXTURE_FOLDER + '/' + files[i].getName())));
            }
        }
        catch(Exception e){}
    }

    private void loadBackSprites(){
        File directory = new File(Constants.BACK_SPRITE_FOLDER);
        File[] files = directory.listFiles();

        try{
            for(int i = 0; i < files.length; i++){
                String tempPath = Constants.BACK_SPRITE_FOLDER + '/' + files[i].getName();
                File frameFolder = new File(tempPath);
                File[] frames = frameFolder.listFiles();
                for(int j  = 0; j < frames.length; j++){
                    int e = 1;
                }
            }

        }
        catch(Exception e){}
    }

    public void addTexture(String name, BufferedImage image){
        textures.put(name, image);
    }

    public BufferedImage getTexture(String name){
        return textures.get(name);
    }

    public void loadFonts(){
        addFont("sign_in", new Font("Arial", Font.BOLD, 22));
        addFont("sign_in_label", new Font("Arial", Font.BOLD, 16));
        addFont("credentials", new Font("Arial", Font.PLAIN, 16));
        addFont("large_font", new Font("Arial", Font.PLAIN, 60));
    }

    public void addFont(String name, Font font){
        fonts.put(name, font);
    }

    public Font getFont(String name){
        return fonts.get(name);
    }
}