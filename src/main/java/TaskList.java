import duke.Deadline;
import duke.Event;
import duke.Task;
import duke.ToDo;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The class instantiate a taskList which can store and manipulate tasks that user created.
 * the class allow an application to add, delete, find, mark, unmark tasks as well as print
 * all tasks from the taskList in a specific format.
 */
public class TaskList {
    public static final int TODOLENGTH = 2;
    public static final int TODO_LENGTH = TODOLENGTH;
    public static final int EVENTCONSTANT = 3;
    public static final int EVENTLENGTH = EVENTCONSTANT;
    public static final int EVENT_LENGTH = EVENTLENGTH;
    public static final int FROM = 1;
    public static final int DEADLINELENGTH = 3;
    public static final int DESCRIPTION_INDEX = 0;
    public static final int TIME_INDEX = 1;
    public static final int OFFSET = 1;
    public ArrayList<Task> taskList;

    public TaskList(){
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> loadedList){
        taskList = new ArrayList<>(loadedList);
    }

    /**
     * Add an Todo type task into taskList.
     * @param taskList target task list
     * @param userInputSplit user input after being processed by the parser
     * @param isDone if the Todo task is already done.
     * @throws EmptyDescriptionException when the description of Todo task is empty.
     */
    public static void addTodo(ArrayList<Task> taskList, String[] userInputSplit, boolean isDone) throws EmptyDescriptionException {
        if (userInputSplit.length < TODO_LENGTH){
            throw new EmptyDescriptionException();
        }
        String[] inputArrayWithoutType = Arrays.copyOfRange(userInputSplit, FROM, userInputSplit.length);
        String description = String.join(" ", inputArrayWithoutType);
        ToDo newToDo = new ToDo(description);
        newToDo.setIsDone(isDone);
        taskList.add(newToDo);
        Task.numOfTasks ++;
        Ui.printEchoInput(newToDo);
    }

    /**
     * Add an Event type task into taskList.
     * @param taskList target task list
     * @param userInputSplit user input after being processed by the parser
     * @param isDone if the Event task is already done.
     * @throws IndexOutOfBoundsException when user does not include the time of the event task.
     */
    public static void addEvent(ArrayList<Task> taskList, String[] userInputSplit, boolean isDone) throws IndexOutOfBoundsException {

        String[] inputArrayWithoutType = Arrays.copyOfRange(userInputSplit, FROM, userInputSplit.length);
        if (inputArrayWithoutType.length < EVENT_LENGTH){
            throw new IndexOutOfBoundsException();
        }
        String inputWithoutType = String.join(" ", inputArrayWithoutType);
        String description = inputWithoutType.split(" /at ")[DESCRIPTION_INDEX];
        String time = inputWithoutType.split(" /at ")[TIME_INDEX];
        Event newEvent = new Event(description, time);
        newEvent.setIsDone(isDone);
        taskList.add(newEvent);
        Task.numOfTasks ++;
        Ui.printEchoInput(newEvent);
    }
    // add exception

    /**
     * Add an Deadline type task into taskList.
     * @param taskList target task list
     * @param userInputSplit user input after being processed by the parser
     * @param isDone if the Deadline task is already done.
     * @throws IndexOutOfBoundsException when user does not include the deadline of the Deadline task.
     */
    public static void addDeadline(ArrayList<Task> taskList, String[] userInputSplit, boolean isDone) throws IndexOutOfBoundsException {
        String[] inputArrayWithoutType = Arrays.copyOfRange(userInputSplit,FROM, userInputSplit.length);
        if (inputArrayWithoutType.length < DEADLINELENGTH){
            throw new IndexOutOfBoundsException();
        }
        String inputWithoutType = String.join(" ", inputArrayWithoutType);
        String description = inputWithoutType.split(" /by ")[DESCRIPTION_INDEX];
        String deadline = inputWithoutType.split(" /by ")[TIME_INDEX];
        Deadline newDeadline = new Deadline(description, deadline);
        taskList.add(newDeadline);
        newDeadline.setIsDone(isDone);
        Task.numOfTasks ++;
        Ui.printEchoInput(newDeadline);
    }
    // add exception

    /**
     * Find all tasks in the taskList through given keyword.
     * @param taskList taskList which is going to be searched.
     * @param userInputSplit user command after being processed by the parser, contain the keyword to be searched.
     */
    public static void findTask(ArrayList<Task> taskList, String[] userInputSplit){
        String[] contentSpilt = Arrays.copyOfRange(userInputSplit,FROM, userInputSplit.length);
        String Content = String.join(" ", contentSpilt);
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the matching tasks in your list:");
        int index = 1;
        for (int i=0; i<Task.numOfTasks; i++){
            Task currTask = taskList.get(i);
            if (currTask.getDescription().contains(Content)){
                System.out.println("     " + index + "." + currTask);
                index ++;
            }
        }
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Delete a task through its index from the taskList.
     * @param taskList target taskList which contains task to be deleted
     * @param userInputSplit user command after being processed by the parser contain the task index to be deleted.
     */
    public static void deleteTask(ArrayList<Task> taskList, String[] userInputSplit) {
        int deleteIndex = Integer.parseInt(userInputSplit[FROM]) - OFFSET;
        System.out.println("    ____________________________________________________________");
        System.out.println("     Noted. I've removed this task:");
        System.out.println(String.format("       %s", taskList.get(deleteIndex).toString()));
        taskList.remove(deleteIndex);
        Task.numOfTasks --;
        System.out.println("     Now you have " + Task.numOfTasks + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Display all tasks in the taskList contain their description, if it has been done, and their time/deadline.
     * @param taskList target taskList to be displayed.
     */
    public static void printTaskList(ArrayList<Task> taskList){
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (int i=0; i<Task.numOfTasks; i++){
            Task currTask = taskList.get(i);

            int index = i + 1;
            System.out.println("     " + index + "." + currTask.toString());
        }
        System.out.println("    ____________________________________________________________");
    }

    /**
     * set isDone parameter of a task to be True.
     * @param taskList taskList contains the target task.
     * @param userInputSplit user command after being processed by the parser, contains the index of the target task
     * in the taskList.
     */
    public static void markTask(ArrayList<Task> taskList, String[] userInputSplit) {
        int markDoneIndex = Integer.parseInt(userInputSplit[FROM]) - OFFSET;
        taskList.get(markDoneIndex).setIsDone(true);
        Ui.printSetDoneMessage(taskList.get(markDoneIndex));
    }

    /**
     * set isDone parameter of a task to be False.
     * @param taskList taskList contains the target task.
     * @param userInputSplit user command after being processed by the parser, contains the index of the target task
     * in the taskList.
     */
    public static void unmarkTask(ArrayList<Task> taskList, String[] userInputSplit) {
        int markNotDoneIndex = Integer.parseInt(userInputSplit[FROM]) - OFFSET;
        taskList.get(markNotDoneIndex).setIsDone(false);
        Ui.printSetNotDoneMessage(taskList.get(markNotDoneIndex));
    }


}
