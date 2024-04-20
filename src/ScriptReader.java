import java.io.File;
import java.util.*;

public class ScriptReader {

    //public ArrayList<ScriptHeader> headers;
    public Hashtable<String, String> metadata;


    public ScriptReader(){
        //headers = new ArrayList<ScriptHeader>();
        metadata = new Hashtable<String, String>();

        readAllHeaders();
    }

    public ArrayList<ScriptHeader> readAllHeaders(){
        ArrayList<ScriptHeader> headers = new ArrayList<ScriptHeader>();
        File directory = new File(Constants.SCRIPT_FOLDER);
        File[] files = directory.listFiles();
        Scanner scanner;
        Boolean insideHeader = false;
        try{
            for(int i = 0; i < files.length; i++){
                scanner = new Scanner(files[i]);
                ScriptHeader header = new ScriptHeader();
                header.setAttribute("FileName", files[i].getName());
                System.out.println("FileName" + "   " + header.getAttribute("FileName"));
                while(scanner.hasNextLine()){
                    String line = scanner.nextLine();
                    if(insideHeader){
                        if(line.equals(Constants.META_END_TAG)){
                            insideHeader = false;
                            break;
                        }
                        line = filter(line, '\t');
                        String[] lineWords = getWords(line);
                        String name = lineWords[0];
                        String data = "";

                        for(int j = 1; j < lineWords.length; j++){
                            data += lineWords[j];
                            if(j < lineWords.length - 1){
                                data += " ";
                            }
                        }
                        header.setAttribute(name, data);
                        System.out.println(name + "   " + header.getAttribute(name));

                    }
                    else{
                        if(line.equals(Constants.META_TAG)){
                            insideHeader = true;
                        }
                    }
                }
                scanner.close();
                headers.add(header);
            }
        }
        catch(Exception e){}

        System.out.println("Successfully read " + headers.size() + " headers");
        return headers;
    }

    public InstructionContainer loadScript(String scriptName){
        File scriptFile = new File(Constants.SCRIPT_FOLDER + '/' + scriptName);
        boolean insideHeader = false;
        Scanner scanner = null;
        ArrayList<String> instructionStrings = new ArrayList<String>();
        InstructionContainer instructions = new InstructionSequence();
        try{
            scanner = new Scanner(scriptFile);
        }
        catch(Exception e){
            System.out.println("File could not be opened");
            return null;
        }

        getToTag(scanner, Constants.SCRIPT_TAG);
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            line = filter(line, '\t');
            if(!line.isEmpty()){
                switch(line.charAt(0)){
                    case '/':{
                        //System.out.println("Comment detected");
                        break;
                    }
                    default:{
                        if(line.equals(Constants.SCRIPT_END_TAG)){
                            instructions = buildScript(instructionStrings, Constants.INSTRUCTION_SEQUENCE_INTERNAL_ID);

                        }
                        //System.out.println("Adding line " + line + " to script");
                        instructionStrings.add(line);
                        break;
                    }
                }
            }

        }
        return instructions;

    }

    public InstructionContainer buildScript(ArrayList<String> script, int InstructionInternalId){
        Stack<String> tags = new Stack<String>();
        Stack<Integer> index = new Stack<Integer>();
        ArrayList<String> subScript = new ArrayList<String>();
        InstructionContainer container = null;
        container = new InstructionLoop();

        switch(InstructionInternalId){
            case Constants.INSTRUCTION_SEQUENCE_INTERNAL_ID -> {
                container = new InstructionSequence();
                break;
            }
            case Constants.INSTRUCTION_LOOP_INTERNAL_ID -> {
                container = new InstructionLoop();
                break;
            }
            case Constants.INSTRUCTION_PARALLEL_INTERNAL_ID -> {
                container = new InstructionParallel();
                break;
            }
            default -> {
                 System.out.println("Invalid instruction id argument passed in buildScript!");
            }
        }
        System.out.println("\n\n\n\n\n");
        for(int i = 0; i < script.size(); i++){
            System.out.println(script.get(i));
        }

        for(int i = 0; i < script.size(); i++){
            switch(getWordAtIndex(script.get(i), 0)){
                case Constants.LOOP_TAG -> {
                    tags.push(script.get(i));
                    index.push(i);
                    break;
                }
                case Constants.LOOP_END_TAG -> {
                    String tempString = tags.pop();
                    int tempIndex = index.pop();
                    if(tags.size() == 0){
                        System.out.println("\n\n\n\n\n" + container.getInstructionId());
                        for(int j = tempIndex + 1; j < i; j++){
                            subScript.add(script.get(j));
                            System.out.println(script.get(j));
                        }

                        int iterationCount = Integer.parseInt(getWordAtIndex(tempString, 1));

                        InstructionContainer tempInstruction = buildScript(new ArrayList<String>(subScript), Constants.INSTRUCTION_LOOP_INTERNAL_ID);
                        ((InstructionLoop)tempInstruction).setIterationCount(iterationCount);
                        container.addInstruction(tempInstruction);

                        subScript.clear();
                    }
                    break;
                }
                case Constants.PARALLEL_TAG -> {
                    tags.push(script.get(i));
                    index.push(i);
                    break;
                }
                case Constants.PARALLEL_END_TAG -> {
                    String tempString = tags.pop();
                    int tempIndex = index.pop();
                    if(tags.size() == 0){
                        System.out.println("\n\n\n\n\n" + container.getInstructionId());
                        for(int j = tempIndex + 1; j < i; j++){
                            subScript.add(script.get(j));
                            System.out.println(script.get(j));
                        }
                        container.addInstruction(buildScript(new ArrayList<String>(subScript), Constants.INSTRUCTION_PARALLEL_INTERNAL_ID));
                        subScript.clear();
                    }
                    break;
                }
                case Constants.SEQUENCE_TAG -> {
                    tags.push(script.get(i));
                    index.push(i);
                    break;
                }
                case Constants.SEQUENCE_END_TAG -> {
                    String tempString = tags.pop();
                    int tempIndex = index.pop();
                    if(tags.size() == 0){
                        System.out.println("\n\n\n\n\n" + container.getInstructionId());
                        for(int j = tempIndex + 1; j < i; j++){
                            subScript.add(script.get(j));
                            System.out.println(script.get(j));
                        }
                        container.addInstruction(buildScript(new ArrayList<String>(subScript), Constants.INSTRUCTION_SEQUENCE_INTERNAL_ID));
                        subScript.clear();
                    }
                    break;
                }
                default -> {
                    if(tags.size() == 0){
                        System.out.println(script.get(i));
                        ArrayList<String> arguments = new ArrayList<String>();
                        for(int j = 1; j < script.get(i).length(); j++){
                            if(getWordAtIndex(script.get(i), j) == ""){
                                break;
                            }
                            arguments.add(getWordAtIndex(script.get(i), j));
                        }
                        String[] argsArray = new String[arguments.size()];
                        arguments.toArray(argsArray);
                        switch(getWordAtIndex(script.get(i), 0)){
                            case Constants.INSTRUCTION_WAIT_TAG -> {
                                container.addInstruction(InstructionWait.build(argsArray));
                                break;
                            }
                            case Constants.INSTRUCTION_MAKE_CIRCLE_TAG -> {
                                container.addInstruction(InstructionMakeCircle.build(argsArray));
                                break;
                            }
                            case Constants.INSTRUCTION_MAKE_SPIRAL_TAG -> {
                                container.addInstruction(InstructionMakeSpiral.build(argsArray));
                                break;
                            }
                            case Constants.INSTRUCTION_MOVE_ENEMY_TAG -> {
                                container.addInstruction(InstructionMoveEnemy.build(argsArray));
                                break;
                            }
                        }
                        //container.addInstruction(new InstructionMakeCircle(200, 200, 32, (float)30.0, (float)4.0, Game.ressourceManager.getTexture("smallPurpleProjectile.png"), 0));
                    }
                }
            }
        }

        return container;
    }

    private boolean getToTag(Scanner scanner, String tag){

        boolean insideHeader = false;

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
                if(line.equals(tag)){
                    insideHeader = true;
                    return insideHeader;
                }
        }
        return insideHeader;

    }

    private String filter(String string, char character){
        String temp = "";
        for(int i = 0; i < string.length(); i++){
            if(string.charAt(i) != character){
                temp += string.charAt(i);
            }
        }
        return temp;
    }

    private String getWordAtIndex(String string, int index){
        int wordIndex = -1;
        String tempString = "";
        for(int i = 0; i < string.length(); i++){
            if(string.charAt(i) != ' '){
                wordIndex++;
                while((i < string.length()) && (string.charAt(i) != ' ')){
                    if(wordIndex == index){
                        tempString += string.charAt(i);
                    }
                    i++;
                }
                if(wordIndex == index){
                    return tempString;
                }
            }
        }
        return tempString;
    }

    private String[] getWords(String string){
        LinkedList<String> list = new LinkedList<String>();
        int wordIndex = -1;
        int length = string.length();
        String tempString = "";

        for(int i = 0; i < length; i++){
            if(string.charAt(i) != ' '){
                wordIndex++;
                while((i < length) && (string.charAt(i) != ' ')){
                    tempString += string.charAt(i);
                    i++;
                }
                list.add(new String(tempString));
                tempString = "";
            }
        }
        String[] temp = new String[list.size()];
        list.toArray(temp);
        return temp;
    }

}