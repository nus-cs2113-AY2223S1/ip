package parser;

import exceptions.EmptyDescriptionException;
import exceptions.IndexOutOfBoundsException;
import taskType.Deadline;
import taskType.Event;
import taskType.Task;
import taskType.ToDo;
import taskList.TaskList;
import ui.Ui;

import java.util.ArrayList;

/**
 * The class instantiate a parser object which translate user input so that the program can
 * understand, and then invoke specific function in TaskList to implement the user command.
 */
public class Parser {
    public static final int DONE = 1;
    public static final int NOTDONE = 0;
    public String userInput;
    public ArrayList<Task> tasks;

    public Parser(String userInput, ArrayList<Task> tasks){
        this.userInput = userInput;
        this.tasks = tasks;
    }

    /**
     * Execute the user command. Translate the user command first and find out
     * which specific command should be implemented, then allocate invoke correspond
     * method to execute the task.
     * @param taskList the target taskList to store the result of ececution.
     * @param userInput the user command which is going to be executed.
     * @param isDone if the target task is already done.
     */
    public static void implementUserInstruction(ArrayList<Task> taskList, String userInput, boolean isDone) {
        String[] userInputSplit = userInput.split(" ");
        switch(userInputSplit[0]){
        case "list":
            TaskList.printTaskList(taskList);
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
            catch(IndexOutOfBoundsException e){
                System.out.println(e);
            };
            break;

        case "deadline":
            try {
                TaskList.addDeadline(taskList, userInputSplit, isDone);
            }
            catch (IndexOutOfBoundsException e){
                System.out.println(e);
            }
            break;


        case "unmark":
            TaskList.unmarkTask(taskList, userInputSplit);
            break;

        case "delete":
            TaskList.deleteTask(taskList, userInputSplit);
            break;

        case "find":
            TaskList.findTask(taskList, userInputSplit);
            break;

        default:
            Ui.printDontKnowMessage();
        }


    }

    /**
     * format the given task in order to store it in hard disk in a specified format.
     * @param task the task which is about to be stored into hard disk.
     * @return formatted result of the task.
     */
    public static String formatTaskInfo(Task task){
        String type;
        int isDone = task.getIsDone() ? DONE : NOTDONE;
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
