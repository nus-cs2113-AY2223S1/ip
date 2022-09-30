
import UI.Commands;
import UI.FileHandler;
import UI.Parser;
import UI.UI;

public class Duke {

    public static void main(String[] args) {
        /*
         * Setting up data file and taskList
         */
        FileHandler.initFiles();
        FileHandler.loadList();

        boolean isExitting = false;

        /*
         * Prints Logo, Welcome message, Command list
         */
        UI.greetUser();
        
        while (isExitting == false){
            //Prompt for Input
            UI.printLine();
            System.out.print("You: ");

            //Getting Input
            Parser.readUserInput();
            String cmd = Parser.getCommand();

            //Responding
            switch (cmd) {
                case ("list"): //List all tasks
                    Commands.runList();
                    break;

                case ("bye"): //Exit program
                    isExitting = true;
                    Commands.runExit();
                    break;

                case ("mark"): //Mark task as done
                    Commands.runMark();
                    break;

                case ("unmark"): //Mark task as not done
                    Commands.runUnmark();
                    break;

                case ("todo"): //Add todo to list
                    Commands.runToDo();
                    break;

                case ("deadline"): //Add deadline to list
                    Commands.runDeadline();
                    break;

                case ("event"): //Add event to list
                    Commands.runEvent();
                    break;
                
                case ("delete"): //Remove task from list
                    Commands.runDelete();
                    break;

                case ("find"): //finds tasks based on keyword
                    Commands.runFind();
                    break;

                default: //unknown command
                    UI.printLine();
                    System.out.println("Sorry, I don't understand what that means :(\n "
                                        + "Please refer to command list! ");
                    break;
            }
        }
    }
}