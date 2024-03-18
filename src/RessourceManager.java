import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Hashtable;

public class RessourceManager {
    public Hashtable<String, BufferedImage> textures;

    public RessourceManager(){
        textures = new Hashtable<String, BufferedImage>();

        loadTextures();
    }

    private void loadTextures(){
        File directory = new File(Constants.TEXTURE_FOLDER);
        File[] files = directory.listFiles();
        try{
            for(int i = 0; i < files.length; i++){
                System.out.println(files[i].getName());
                addTexture(files[i].getName(), ImageIO.read(new File(Constants.TEXTURE_FOLDER + '/' + files[i].getName())));
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
}