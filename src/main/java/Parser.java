/**
 * Parser class
 * Parses any input entered by the user
 * Contains different kinds of methods to parse the input for different types of operations
 */
public class Parser {
    /**
     * Finds the position from input text of the task that needs to be marked or unmarked
     * @param inputText the text from which the position needs to be extracted
     *                  In String format
     *                  In the format entered by the user in the CLI
     * @return returns an integer containing the position of the task which has to be marked or unmarked
     */
    public static int getPositionFromInput(String inputText){
        return Integer.parseInt(inputText.substring(inputText.length()-1));
    }

    /**
     * Finds the position from the input text of the task that needs to be deleted
     * @param inputText the text from which the position needs to be extracted
     *                  In String format
     *                  In the format entered by the user in the CLI
     * @return returns an integer containing the position of the task which has to be deleted
     */
    public static int getDeletePosition(String inputText){
        int pos = Integer.parseInt(inputText.substring(7,8));
        return pos;
    }

    /**
     * Finds the type of task (todo, deadline, or event) from the input text
     * @param inputText the text from which the type of task needs to be extracted
     *                  In String format
     *                  In the format entered by the user in the CLI
     * @return returns a String specifying the type of task
     * If the task is todo, returns "todo"
     * If the task is a deadline, returns "deadline"
     * If the task is an event, returns "event"
     */
    public static String getTaskType(String inputText){
        String[] splitText = inputText.split(" ");
        return splitText[0];
    }

    /**
     * Parses the find command entered by user from the input text to extract the word which the user wants to look for
     * if the command has only the find word and the remaining part of it is empty, it throws an InvalidCommandException
     * if the command has mmore than one word to search for, it throws an InvalidCommandException.
     * @param inputText a string containing the entire input given by the user using CLI
     * @return a String containing the word the user is searching for
     *
     */
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

    /**
     * Parses the input text from the user to find the command type given by the user
     * If the command is empty, it throws a NUllCommandException
     * @param inputText a string containing the entire input given by the user using CLI
     * @return returns a string giving the exact type of command entered by the user
     */
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
