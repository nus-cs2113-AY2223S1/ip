import duke.Deadline;
import duke.Event;
import duke.Task;
import duke.ToDo;

import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;


public class Duke {

    public static final String BYE = "bye";

    public static void main(String[] args) {
        printWelcomeMessage();
        ArrayList<Task> taskList = new ArrayList<Task>();
        try {
            readFile(taskList, "src/main/Contents");
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }

        String userInput = getUserInput();
        while(!userInput.equals(BYE)){
            implementUserInstruction(taskList, userInput, false);
            userInput = getUserInput();
        }

        printByeMessage();
    }

    public static void readFile(ArrayList<Task> taskList, String filePath) throws FileNotFoundException{
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()){
            String nextInstruction = s.nextLine();
            String[] instSplit = nextInstruction.split(" \\| ");
            String deformattedInst;
            boolean isDone = instSplit[1].equals("0") ? false : true;
            switch (instSplit[0]){
            case "T":
                deformattedInst = String.format("todo %s", instSplit[2]);
                break;
            case "D":
                deformattedInst = String.format("deadline %s /by %s", instSplit[2], instSplit[3]);
                break;
            case "E":
                deformattedInst = String.format("event %s /at %s", instSplit[2], instSplit[3]);
                break;
            default:
                deformattedInst = "Wrong input";
            }


            implementUserInstruction(taskList, deformattedInst, isDone);
        }
    }


    private static void implementUserInstruction(ArrayList<Task> taskList, String userInput, boolean isDone) {
        String[] userInputSplit = userInput.split(" ");
        switch(userInputSplit[0]){
        case "list":
            printTaskList(taskList, Task.numOfTasks);
            break;

        case "mark":
            markTask(taskList, userInputSplit);
            break;

        case "todo":
            try{
                addTodo(taskList, userInputSplit, isDone);
            }
            catch(EmptyDescriptionException e){
                System.out.println(e);
            }

            break;

        case "event":
            try{
                addEvent(taskList, userInputSplit, isDone);
            }
            catch(StringIndexOutOfBoundsException e){
                System.out.println(e.toString());
            };
            break;

        case "deadline":
            addDeadline(taskList, userInputSplit, isDone);
            break;


        case "unmark":
            unmarkTask(taskList, userInputSplit);
            break;

        case "delete":
            // refactor delete method
            int deleteIndex = Integer.parseInt(userInputSplit[1]) - 1;
            deleteTask(taskList, deleteIndex);
            break;

        default:
            printDontKnowMessage();
        }

        try {
            saveTasks(taskList, "src/main/Contents");
        }
        catch (IOException e){
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static void markTask(ArrayList<Task> taskList, String[] userInputSplit) {
        int markDoneIndex = Integer.parseInt(userInputSplit[1]) - 1;
        taskList.get(markDoneIndex).setIsDone(true);
        printSetDoneMessage(taskList.get(markDoneIndex));
    }

    private static void unmarkTask(ArrayList<Task> taskList, String[] userInputSplit) {
        int markNotDoneIndex = Integer.parseInt(userInputSplit[1]) - 1;
        taskList.get(markNotDoneIndex).setIsDone(false);
        printSetNotDoneMessage(taskList.get(markNotDoneIndex));
    }


    private static void saveTasks(ArrayList<Task> taskList, String filePath) throws IOException{
        FileWriter fw = new FileWriter(filePath);
        fw.write("");
        fw.close();
        String formattedResult;
        FileWriter fwa = new FileWriter(filePath, true);
        for (Task task : taskList){
            formattedResult = formatTaskInfo(task);
            fwa.write(formattedResult);
        }

        fwa.close();
    }

    private static String formatTaskInfo(Task task){
        String type;
        int isDone = task.getIsDone() ? 1 : 0;
        String time;
        String description = task.getDescription();
        String fromattedResult;
        if (task instanceof ToDo) {
            type = "T";
            fromattedResult = String.format("%s | %d | %s\n", type, isDone, description);
        }
        else if (task instanceof Deadline) {
            type = "D";
            time = ((Deadline) task).getDeadline();
            fromattedResult = String.format("%s | %d | %s | %s\n", type, isDone, description, time);
        }
        else if (task instanceof Event) {
            type = "E";
            time = ((Event) task).getTime();
            fromattedResult = String.format("%s | %d | %s | %s\n", type, isDone, description, time);
        }
        else {fromattedResult = "Wrong Format\n";}

    return fromattedResult;
    }


    private static void deleteTask(ArrayList<Task> taskList, int deleteIndex) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Noted. I've removed this task:");
        System.out.println(String.format("       %s", taskList.get(deleteIndex).toString()));
        taskList.remove(deleteIndex);
        Task.numOfTasks --;
        System.out.println("     Now you have " + Task.numOfTasks + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    private static void addTodo(ArrayList<Task> taskList, String[] userInputSplit, boolean isDone) throws EmptyDescriptionException {
        if (userInputSplit.length < 2){
            throw new EmptyDescriptionException();
        }
        String[] inputArrayWithoutType = Arrays.copyOfRange(userInputSplit,1, userInputSplit.length);
        String description = String.join(" ", inputArrayWithoutType);
        ToDo newToDo = new ToDo(description);
        newToDo.setIsDone(isDone);
        taskList.add(newToDo);
        Task.numOfTasks ++;
        printEchoInput(newToDo);
    }

    private static void addEvent(ArrayList<Task> taskList, String[] userInputSplit, boolean isDone) throws StringIndexOutOfBoundsException{

        String[] inputArrayWithoutType = Arrays.copyOfRange(userInputSplit, 1, userInputSplit.length);
        String inputWithoutType = String.join(" ", inputArrayWithoutType);
        if (inputWithoutType.length() < 3){
            throw new StringIndexOutOfBoundsException();
        }
        String description = inputWithoutType.split(" /at ")[0];
        String time = inputWithoutType.split(" /at ")[1];
        Event newEvent = new Event(description, time);
        newEvent.setIsDone(isDone);
        taskList.add(newEvent);
        Task.numOfTasks ++;
        printEchoInput(newEvent);
    }
    // add exception

    private static void addDeadline(ArrayList<Task> taskList, String[] userInputSplit, boolean isDone) {
        String[] inputArrayWithoutType = Arrays.copyOfRange(userInputSplit,1, userInputSplit.length);
        String inputWithoutType = String.join(" ", inputArrayWithoutType);
        String description = inputWithoutType.split(" /by ")[0];
        String deadline = inputWithoutType.split(" /by ")[1];
        Deadline newDeadline = new Deadline(description, deadline);
        taskList.add(newDeadline);
        newDeadline.setIsDone(isDone);
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
    public static void printTaskList(ArrayList<Task> taskList, int count){
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (int i=0; i<count; i++){
            Task currTask = taskList.get(i);

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
