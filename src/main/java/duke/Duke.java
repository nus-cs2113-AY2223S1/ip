package duke;
import java.util.Scanner;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidCommandException;
public class Duke {
    public static void generateTaskStatus(String taskIcon, String statusIcon, String description) {
        System.out.println("\t[" + taskIcon + "]" + "[" + statusIcon + "] " + description);
    }
    public static void drawLine() {
        System.out.println("------------------------------------");
    }
    public static void addedMsg() {
        System.out.println("Got it. I've added this task:");
    }

    public static void taskCountReminder(int noOfTasks) {
        System.out.printf("Now you have %d tasks in the list.\n",noOfTasks);
    }

    public static boolean isValidCommand(String s){
        String[] validCommandArray = {"bye", "list","unmark","mark","todo","deadline","event"};
        for(int i = 0; i < validCommandArray.length; i++) {
            if(s.equals(validCommandArray[i])){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        drawLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        drawLine();
        Scanner in = new Scanner(System.in);
        String input;
        Task[] taskArray = new Task[100];
        int addCount = 0;
        do {
            input = in.nextLine();
            try {
                String[] inputStrings = input.split(" ");
                if(!isValidCommand(inputStrings[0])){
                    throw new InvalidCommandException();
                }
                if(inputStrings.length == 1 && !inputStrings[0].equals("list")){
                    throw new EmptyDescriptionException(inputStrings[0]);
                }
            } catch (InvalidCommandException e){
                drawLine();
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                drawLine();
                continue;
            }
            catch(EmptyDescriptionException e) {
                drawLine();
                System.out.println("OOPS!!! The description of a " + e.getMessage() + " cannot be empty.");
                drawLine();
                continue;
            }
            if(input.equals("bye")){
                drawLine();
                System.out.println("Bye. Hope to see you again soon!");
                drawLine();
            } else if (input.equals("list")){
                drawLine();
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= addCount; i++){
                    generateTaskStatus(taskArray[i-1].getTaskIcon(), taskArray[i-1].getStatusIcon(), taskArray[i-1].getDescription());
                }
                drawLine();
            } else if (input.contains("unmark")){
                String[] inputWords = input.split(" ");
                int choiceToUnMark = Integer.parseInt(inputWords[1]);
                taskArray[choiceToUnMark - 1].unMarkTask();
                System.out.println("OK, I've marked this task as not done yet:");
                generateTaskStatus(taskArray[choiceToUnMark-1].getTaskIcon(), taskArray[choiceToUnMark-1].getStatusIcon(),taskArray[choiceToUnMark-1].getDescription() );
                drawLine();

            } else if (input.contains("mark")){
                String[] inputWords = input.split(" ");
                int choiceToMark = Integer.parseInt(inputWords[1]);
                taskArray[choiceToMark - 1].markTask();
                System.out.println("Nice! I've marked this task as done:");
                generateTaskStatus(taskArray[choiceToMark-1].getTaskIcon(), taskArray[choiceToMark-1].getStatusIcon(), taskArray[choiceToMark-1].getDescription());
                drawLine();
            } else if(input.contains("todo")) {
                taskArray[addCount] = new Todo(input);
                drawLine();
                addedMsg();
                generateTaskStatus(taskArray[addCount].getTaskIcon(), taskArray[addCount].getStatusIcon(), taskArray[addCount].getDescription());
                addCount +=1;
                taskCountReminder(addCount);
                drawLine();
            } else if (input.contains("deadline")) {
                taskArray[addCount] = new Deadline(input);
                drawLine();
                addedMsg();
                generateTaskStatus(taskArray[addCount].getTaskIcon(), taskArray[addCount].getStatusIcon(), taskArray[addCount].getDescription());
                addCount +=1;
                taskCountReminder(addCount);
                drawLine();
            } else if (input.contains("event")) {
                taskArray[addCount] = new Event(input);
                drawLine();
                addedMsg();
                generateTaskStatus(taskArray[addCount].getTaskIcon(), taskArray[addCount].getStatusIcon(), taskArray[addCount].getDescription());
                addCount +=1;
                taskCountReminder(addCount);
                drawLine();
            }
            else {
                taskArray[addCount] = new Task(input);
                drawLine();
                addedMsg();
                generateTaskStatus(taskArray[addCount].getTaskIcon(), taskArray[addCount].getStatusIcon(), taskArray[addCount].getDescription());
                addCount +=1;
                taskCountReminder(addCount);
                drawLine();
            }
        } while (!input.equals("bye"));
    }
}
