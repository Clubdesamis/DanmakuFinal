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

    public void setContents(Hashtable<String, String> metadata){
        this.metadata = metadata;
    }

    public Hashtable<String, String> getContents(){
        return this.metadata;
    }

    public void setAttribute(String name, String content){
        metadata.put(name, content);
    }
}