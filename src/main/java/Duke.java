import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {


    public static void main(String[] args) throws UnknownInputException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");

        //tasks is an array list collection of task objects
        List<Task> tasks = new ArrayList<>();
        String input = " ";
        String description;
        Scanner in = new Scanner(System.in);
        Boolean isMarked;
        input = in.nextLine();
        input = input.trim();

        // duke runs until a "bye" is entered
        while (tasks.size() < 100 && !input.equals("bye")) {
            String [] splitInput = input.split(" ");
            isMarked = Boolean.FALSE;



            // list commands duke to list all the tasks stored and their completion status
            // try at the start cos of the errors possibly
            try {
                switch (splitInput[0]) {
                    case "list":
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println(i + 1 + ". " + tasks.get(i));
                        }
                        System.out.println();
                        break;
                    case "mark":
                        // mark x commands duke to mark the corresponding task as completed
                        isMarked = Boolean.TRUE;
                        System.out.println("Nice! I've marked this task as done: ");
                        // without the break, both mark and unmark will trigger the similar process

                    case "unmark":
                        // unmark x commands duke to mark the corresponding task as uncompleted
                        // Exceptions could occur
                        String numericString = input.substring(input.indexOf(" ") + 1);
                        int markedNum = Integer.parseInt(numericString) - 1;
                        tasks.get(markedNum).setStatus(isMarked);
                        if (!isMarked) {
                            System.out.println("Oh no :( I've marked it as uncompleted: ");
                        }
                        System.out.println("  " + tasks.get(markedNum) + "\n");
                        break;

                    case "todo":
                        if (splitInput.length == 1) {
                            throw new EmptyDescriptionException();
                        }
                        description = input.substring(input.indexOf(" ") + 1);
                        Task td = new Todo(description);
                        // this function does all the printing
                        taskPrint(tasks, td);
                        break;

                    case "deadline":
                        if (splitInput.length == 1) {
                            throw new EmptyDescriptionException();
                        }
                        description = input.substring(input.indexOf(" ") + 1, input.indexOf(" /by "));
                        String by = input.substring(input.indexOf("/by ") + 4);
                        Task d = new Deadline(description, by);
                        taskPrint(tasks, d);
                        break;

                        //StringIndexOutOfBoundsException

                    case "event":
                        if (splitInput.length == 1) {
                            throw new EmptyDescriptionException();
                        }
                        description = input.substring(input.indexOf(" ") + 1, input.indexOf(" /at "));
                        String at = input.substring(input.indexOf("/at ") + 4);
                        Task e = new Event(description, at);
                        taskPrint(tasks, e);
                        break;

                    case "blah":
                    case "idk":
                        throw new UnknownInputException();


                    default:
                        // other calls causes duke to add the user-input to tasks
                        Task t = new Task(input);
                        tasks.add(t);
                        System.out.println("added: " + input + "\n");
                        break;
                }
            }
            catch (UnknownInputException e) {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-( \n");
            }

            catch (StringIndexOutOfBoundsException e) {
                System.out.println("OOPS!!! You must have a ' /at ' for events OR a ' /by ' for deadlines!!\n");
            }

            catch (EmptyDescriptionException e) {
                if (splitInput[0] == "event") {
                    System.out.println("OOPS!!! The description of an " + splitInput[0] + " cannot be empty.\n");
                } else {
                    System.out.println("OOPS!!! The description of a " + splitInput[0] + " cannot be empty.\n");
                }
            }

            // input after the try, catch and switch case to obtain the next input statement
            input = in.nextLine();
            input = input.trim();

        }

        System.out.println("Bye good friend! Hope to see you again soon!\n" + logo);
    }

    /**
     * Method for printing Tasks adding
     * @param tasks
     * @param t
     * @return
     */
    public static void taskPrint(List<Task> tasks, Task t) {
        tasks.add(t);
        System.out.println("Got it. I've added this task: \n" + t);
        System.out.println("Now you have " + tasks.size() + " tasks in the list\n");
    }
}
