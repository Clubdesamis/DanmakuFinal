import java.util.Hashtable;

public class ScriptHeader {

    public Hashtable<String, String> metadata;

    public ScriptHeader(){
        metadata = new Hashtable<String, String>();
    }

    public String getAttribute(String name){
        if(metadata.get(name) == null){
            return "???";
        }
        else{
            return metadata.get(name);
        }
    }
    public void setAttribute(String name, String content){
        metadata.put(name, content);
    }
}