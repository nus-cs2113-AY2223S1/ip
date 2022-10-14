/**
 * Class used to parse user input
 */
public class Parser {

    /**
     * Get the first part of the user input
     * @param userInput
     * @return String of what type the task is
     */
    public static String TaskType(String userInput) {
        String taskType = userInput.substring(0, userInput.indexOf(' ')); //list, mark, unmark etc.
        return taskType;
    }

    /**
     * Get the string of the second part of the user input
     * @param userInput
     * @param taskType
     * @return String of the second part of the user input
     */
    public static String UserInputNewValue(String userInput, String taskType) {
        String userInputNewValue = userInput.substring(taskType.length());
        //firstly does it assuming that it is todo, so no date , todo borrow book -> borrow book
        return userInputNewValue;
    }


    /**
     * Get the value of which item in list to mark or unmark
     * @param userInput
     * @return int of which item in list to mark/unmark
     */
    public static int MarkedValue(String userInput) {
        int markedValue = Integer.parseInt(userInput.substring(userInput.indexOf(' ')).replaceAll("\\s+", "")) - 1;
        //used just for mark and unmark
        return markedValue;
    }

    /**
     * Returns the type of command that user inputted
     * @param taskType user input
     * @return Command type
     */
    public static Commands CommandType(String taskType) {
        Commands commandType = Commands.valueOf(taskType.toUpperCase());
        return commandType;
    }






}
