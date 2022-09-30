package duke.utilityfunctions;

public class Command {
    public boolean isValidCommand(String s){
        String[] validCommandArray = {"bye", "list","unmark","mark","todo","deadline","event", "delete"};
        for(int i = 0; i < validCommandArray.length; i++) {
            if(s.equals(validCommandArray[i])){
                return true;
            }
        }
        return false;
    }
    public int choiceParser(String input){
        String[] inputWords = input.split(" ");
        int choice = Integer.parseInt(inputWords[1]);
        return choice;
    }

}
