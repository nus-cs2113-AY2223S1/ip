import duke.Deadline;
import duke.Event;
import duke.Task;
import duke.ToDo;

import java.io.IOException;
import java.util.ArrayList;

public class Parser {
    public String userInput;
    public ArrayList<Task> tasks;

    public Parser(String userInput, ArrayList<Task> tasks){
        this.userInput = userInput;
        this.tasks = tasks;
    }

    public static void implementUserInstruction(ArrayList<Task> taskList, String userInput, boolean isDone) {
        String[] userInputSplit = userInput.split(" ");
        switch(userInputSplit[0]){
            case "list":
                TaskList.printTaskList(taskList, Task.numOfTasks);
                break;

            case "mark":
                TaskList.markTask(taskList, userInputSplit);
                break;

            case "todo":
                try{
                    TaskList.addTodo(taskList, userInputSplit, isDone);
                }
                catch(EmptyDescriptionException e){
                    System.out.println(e);
                }

                break;

            case "event":
                try{
                    TaskList.addEvent(taskList, userInputSplit, isDone);
                }
                catch(StringIndexOutOfBoundsException e){
                    System.out.println(e.toString());
                };
                break;

            case "deadline":
                TaskList.addDeadline(taskList, userInputSplit, isDone);
                break;


            case "unmark":
                TaskList.unmarkTask(taskList, userInputSplit);
                break;

            case "delete":
                int deleteIndex = Integer.parseInt(userInputSplit[1]) - 1;
                TaskList.deleteTask(taskList, deleteIndex);
                break;

            default:
                UI.printDontKnowMessage();
        }

        try {
            Storage.saveTasks(taskList, "src/main/Contents");
        }
        catch (IOException e){
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static String formatTaskInfo(Task task){
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
}
