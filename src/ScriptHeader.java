public class ScriptHeader {

    public String name;
    public String creatorName;
    public String difficulty;


    public ScriptHeader(){
        this.name = "???";
        this.creatorName = "???";
        this.difficulty = "???";
    }

    public String toString(){
        return name + '\n' + creatorName + '\n' + difficulty;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setCreatorName(String creatorName){
        this.creatorName = creatorName;
    }

    public void setDifficulty(String difficulty){
        this.difficulty = difficulty;
    }
    public void setAttribute(String attributeName, String attributeContent){

        switch(attributeName){
            case "Name":{
                this.name = attributeContent;
                break;
            }
            case "Difficulty":{
                this.difficulty = attributeContent;
                break;
            }
            case "Creator":{
                this.creatorName = attributeContent;
                break;
            }
        }
    }
}