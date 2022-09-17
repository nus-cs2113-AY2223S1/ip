import duke.Deadline;
import duke.Event;
import duke.Task;
import duke.ToDo;

import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class Duke {

    public static final String BYE = "bye";

    public static void main(String[] args) {
        printWelcomeMessage();
        ArrayList<Task> taskLists = new ArrayList<Task>();


        String userInput = getUserInput();

        while(!userInput.equals("bye")){
            String[] userInputSplit = userInput.split(" ");
            switch(userInputSplit[0]){
            case "list":
                printTaskList(taskLists, Task.numOfTasks);
                break;

            case "mark":
                int markDoneIndex = Integer.parseInt(userInputSplit[1]) - 1;
                taskLists.get(markDoneIndex).setIsDone(true);
                printSetDoneMessage(taskLists.get(markDoneIndex));
                break;

            case "todo":
                try{
                    addTodo(taskLists, userInputSplit);
                }
                catch(EmptyDescriptionException e){
                    System.out.println(e);
                }

                break;

            case "event":
                addEvent(taskLists, userInputSplit);
                break;

            case "deadline":
                addDeadline(taskLists, userInputSplit);
                break;


            case "unmark":
                int markNotDoneIndex = Integer.parseInt(userInputSplit[1]) - 1;
                taskLists.get(markNotDoneIndex).setIsDone(false);
                printSetNotDoneMessage(taskLists.get(markNotDoneIndex));
                break;

            case "delete":
                int deleteIndex = Integer.parseInt(userInputSplit[1]) - 1;
                deleteTask(taskLists, deleteIndex);
                break;

                default:
                printDontKnowMessage();
            }
            userInput = getUserInput();
        }

        printByeMessage();
    }

    private static void deleteTask(ArrayList<Task> taskLists, int deleteIndex) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Noted. I've removed this task:");
        System.out.println(String.format("       %s", taskLists.get(deleteIndex).toString()));
        taskLists.remove(deleteIndex);
        Task.numOfTasks --;
        System.out.println("     Now you have " + Task.numOfTasks + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    private static void addTodo(ArrayList<Task> taskLists, String[] userInputSplit) throws EmptyDescriptionException {
        if (userInputSplit.length < 2){
            throw new EmptyDescriptionException();
        }
        String[] inputArrayWithoutType = Arrays.copyOfRange(userInputSplit,1, userInputSplit.length);
        String description = String.join(" ", inputArrayWithoutType);
        ToDo newToDo = new ToDo(description);
        taskLists.add(newToDo);
        Task.numOfTasks ++;
        printEchoInput(newToDo);
    }

    private static void addEvent(ArrayList<Task> taskLists, String[] userInputSplit) {
        String[] inputArrayWithoutType = Arrays.copyOfRange(userInputSplit,1, userInputSplit.length);
        String inputWithoutType = String.join(" ", inputArrayWithoutType);
        String description = inputWithoutType.split(" /at ")[0];
        String time = inputWithoutType.split(" /at ")[1];
        Event newEvent = new Event(description, time);
        taskLists.add(newEvent);
        Task.numOfTasks ++;
        printEchoInput(newEvent);
    }
    // add exception

    private static void addDeadline(ArrayList<Task> taskLists, String[] userInputSplit) {
        String[] inputArrayWithoutType = Arrays.copyOfRange(userInputSplit,1, userInputSplit.length);
        String inputWithoutType = String.join(" ", inputArrayWithoutType);
        String description = inputWithoutType.split(" /by ")[0];
        String deadline = inputWithoutType.split(" /by ")[1];
        Deadline newDeadline = new Deadline(description, deadline);
        taskLists.add(newDeadline);
        Task.numOfTasks ++;
        printEchoInput(newDeadline);
    }
    // add exception

    private static void printDontKnowMessage(){
        String dontKnowMessage = "    ____________________________________________________________\n"
                        + "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                        + "    ____________________________________________________________";
        System.out.println(dontKnowMessage);
    }

    public static String getUserInput(){
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        return line;
    }

    public static void printEchoInput(Task task){
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task:");
        System.out.println(String.format("       %s", task.toString()));
        System.out.println(String.format("     Now you have %d tasks in the list.", Task.numOfTasks));
        System.out.println("    ____________________________________________________________");
    }

    public static void printWelcomeMessage() {
        String welcomeMessage =
                "    ____________________________________________________________\n"
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + "    ____________________________________________________________";
        System.out.println(welcomeMessage);
    }

    public static void printByeMessage() {
        String byeMessage =
                "    ____________________________________________________________\n"
                + "     Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________";
        System.out.println(byeMessage);
    }

    // replace count
    public static void printTaskList(ArrayList<Task> taskLists, int count){
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (int i=0; i<count; i++){
            Task currTask = taskLists.get(i);

            int index = i + 1;
            System.out.println("     " + index + "." + currTask.toString());
        }
        System.out.println("    ____________________________________________________________");
    }

    public static void printSetDoneMessage(Task task){
        System.out.println("    ____________________________________________________________");
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + task.toString());
        System.out.println("    ____________________________________________________________");
    }

    public static void printSetNotDoneMessage(Task task){
        System.out.println("    ____________________________________________________________");
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("       " + task.toString());
        System.out.println("    ____________________________________________________________");
    }


}
