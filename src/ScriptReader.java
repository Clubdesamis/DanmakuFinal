import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class ScriptReader {

    public ArrayList<ScriptHeader> headers;

    public ScriptReader(){
        headers = new ArrayList<ScriptHeader>();

        readAllHeaders();
    }

    public void readAllHeaders(){
        File directory = new File(Constants.SCRIPT_FOLDER);
        File[] files = directory.listFiles();
        Scanner scanner;
        Boolean insideHeader = false;
        try{
            for(int i = 0; i < files.length; i++){
                scanner = new Scanner(files[i]);
                ScriptHeader header = new ScriptHeader();
                while(scanner.hasNextLine()){
                    String line = scanner.nextLine();
                    if(insideHeader){
                        if(line.equals(Constants.META_END_TAG)){
                            insideHeader = false;
                            break;
                        }
                        line = filter(line, '\t');
                        String[] lineWords = getWords(line);
                        for(int j = 0; j < Constants.META_ARGUMENTS.length; j++){
                            if(lineWords[0].equals(Constants.META_ARGUMENTS[j])){

                                String attributeName = lineWords[0];
                                String attributeContent = "";
                                for(int k = 1; k < lineWords.length; k++){
                                    attributeContent += (lineWords[k] + ' ');
                                }
                                header.setAttribute(attributeName, attributeContent);
                            }
                        }
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
        for(int i = 0; i < headers.size(); i++){
            System.out.println(headers.get(i).toString());
        }
    }

    public void loadScript(String scriptName){
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
            return;
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
                        for(int j = 1; j < script.get(i).l j++){
                            arguments.add(getWordAtIndex(script.get(i), j));
                        }
                        switch(getWordAtIndex(script.get(i), 0)){
                            case Constants.INSTRUCTION_WAIT_TAG -> {
                                InstructionWait.build();
                                break;
                            }
                            case Constants.INSTRUCTION_MAKE_CIRCLE_TAG -> {

                                break;
                            }
                            case Constants.INSTRUCTION_MAKE_SPIRAL_TAG -> {

                                break;
                            }
                        }
                        container.addInstruction(new InstructionMakeCircle(200, 200, 32, (float)30.0, (float)4.0, Game.ressourceManager.getTexture("smallPurpleProjectile.png"), 31, 0));
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