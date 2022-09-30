public class Parser {

    public static int getPositionFromInput(String inputText){
        return Integer.parseInt(inputText.substring(inputText.length()-1));
    }

    public static int getDeletePosition(String inputText){
        int pos = Integer.parseInt(inputText.substring(7,8));
        return pos;
    }

    public static String getTaskType(String inputText){
        String[] splitText = inputText.split(" ");
        return splitText[0];
    }

    public static String parseFindCommand(String inputText){
        String word = "";
        String[] splitText = inputText.split(" ");
        try {
            if (splitText.length != 2 || splitText[1] == null || splitText[1] == "") {
                throw new InvalidCommandException();
            } else {
                word =  splitText[1];
            }
        } catch (InvalidCommandException e) {
            System.out.println("Please enter one valid word to find");
        }
        return word;
    }
    public static String parseCommand(String inputText){
        String command = "";
        try {
            String[] splitText = inputText.split(" ");
            if(splitText.length == 0 || splitText[0].length() == 0) {
                throw new NullCommandException();
            }else if(splitText[0].equals("bye")){
                command = "bye";
            }else if (splitText[0].equals("list")) {
                command = "list";
            }else if ((splitText[0].equals("mark"))  ) {
               command = "mark";
            }else if (splitText[0].equals("unmark") ) {
                command = "unmark";
            }else if(splitText[0].equals("delete")){
               command = "delete";
            }else if(splitText[0].equals("find")){
                command = "find";
            }
            else{
                command = "task";
            }
        } catch(NullCommandException e){
            System.out.println("Empty command. Please try again.");
        }
        return command;
    }
}
