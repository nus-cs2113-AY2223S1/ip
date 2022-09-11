import exception.InvalidArgumentsException;
import exception.InvalidCommandException;
import exception.NotEnoughArgumentsException;
import exception.TaskDoesNotExistException;
import task.TaskList;
import java.util.Scanner;

/*
* End programme: bye
* List tasks: List
* Add todo: todo (name)
* Add deadline: deadline (name) /by (time)
* Add event: event (name) /at (time)
* Mark/Unmark: Mark/Unmark (task number)
*/

public class Duke {
    public static final String COMMAND_UNMARKED = "unmark";
    public static final String COMMAND_MARKED = "mark";
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_LIST = "list";
    public static void main(String[] args) {
        TaskList list = new TaskList();
        Scanner in = new Scanner(System.in);
        list.start();
        while (in.hasNextLine()) {
            String input = in.nextLine();
            if (input.equals(COMMAND_BYE)) {
                list.end();
                break;
            } else if (input.equals(COMMAND_LIST)) {
                list.printList();
            } else if (input.contains(COMMAND_MARKED) || input.contains(COMMAND_UNMARKED)) {
                try {
                    list.markStatus(input);
                } catch (TaskDoesNotExistException e){
                    System.out.println("Task does not exist. Please try again.");
                    TaskList.printLine();
                }
            } else {
                try {
                    list.addTask(input);
                } catch (InvalidArgumentsException e) {
                    System.out.println("Invalid arguments detected. Please try again.");
                    TaskList.printLine();
                } catch (InvalidCommandException e) {
                    System.out.println("Invalid command. Please try again.");
                    TaskList.printLine();
                } catch (NotEnoughArgumentsException e) {
                    System.out.println("Not enough arguments entered. Please try again.");
                    TaskList.printLine();
                }
            }
        }
    }
}
