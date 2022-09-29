package Duke;

import Duke.task.Deadline;
import Duke.task.Event;
import Duke.task.Task;
import Duke.task.Todo;
import java.util.ArrayList;

/**
 * TaskList contains the task list and handles all operations relating to the list
 */
public class TaskList {
    public ArrayList<Task> list= new ArrayList<>();
    public Boolean isLast = false;
    public static String fileLocation = "data/duke.txt";

    /**
     * Check if a task has been marked as done
     * @param task Task to be checked
     * @param isDone Marker signifying if task is done or not
     */
    public static void checkMarked (Task task, String isDone) {
        if (isDone == "true") {
            task.isDone = true;
        }
    }

    /**
     * Converts the index of the task from String to Integer
     * @param commandActual String containing index of task
     * @return Index of task as an integer
     */
    public static int getIndex (String commandActual) {
        return Integer.parseInt(commandActual) - 1;
    }

    /**
     * Prints out all tasks currently stored in the list
     * @param list List of tasks
     */
    public static void listTasks (ArrayList<Task> list) {
        Ui.drawLine();
        System.out.println("Here are the tasks in your list:");
        int cnt = list.size();
        for (int i = 0; i < cnt; i++) {
            System.out.println((i + 1) + "." + list.get(i).toString());
        }
    }

    /**
     * Unmark task as done and write to local file
     * @param list List of tasks
     * @param index Index of task to be unmarked
     */
    public static void setUnmarked (ArrayList<Task> list, String index) {
        try {
            int indexToUnmark = getIndex(index);
            Task unmarking = list.get(indexToUnmark);
            unmarking.setUnDone();
            list.set(indexToUnmark, unmarking);
            Ui.drawLine();
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println("\t" + list.get(indexToUnmark).toString());
            Storage.writeToFile(fileLocation, list);
        } catch (IndexOutOfBoundsException e) {
            Ui.showIndexOutOfBounds();
        }
    }

    /**
     * Mark task as done and write to local file
     * @param list List of tasks
     * @param index Index of task to be unmarked
     */
    public static void setMarked (ArrayList<Task> list, String index) {
        try {
            int indexToMark = getIndex(index);
            Task marking = list.get(indexToMark);
            marking.setDone();
            list.set(indexToMark, marking);
            Ui.drawLine();
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("\t" + list.get(indexToMark).toString());
            Storage.writeToFile(fileLocation, list);
        } catch (IndexOutOfBoundsException e) {
            Ui.showIndexOutOfBounds();
        }
    }

    /**
     * Add a new ToDo task and write to local file
     * @param list List of tasks
     * @param description Description of task
     * @throws EmptyToDo Error if description of task is empty
     */
    public static void newToDo (ArrayList<Task> list, String description) throws EmptyToDo {
        Ui.drawLine();
        if (description == null) {
            throw new EmptyToDo();
        }
        Todo newTodo = new Todo(description);
        list.add(newTodo);
        System.out.println(" Got it. I've added this task:");
        System.out.println("\t" + newTodo.toString());
        System.out.println(" Now you have " + list.size() + " tasks in the list.");
        Storage.writeToFile(fileLocation, list);
    }

    /**
     * Add a new Deadline task and write to local file
     * @param list List of tasks
     * @param commandActual User input containing description and deadline of task
     */
    public static void newDeadline (ArrayList<Task> list, String commandActual) {
        Ui.drawLine();
        String arr2[] = commandActual.split("/by ", 2);
        String desc = arr2[0];
        String deadline = arr2[1];
        Deadline newDeadline = new Deadline(desc, deadline);
        list.add(newDeadline);
        System.out.println(" Got it. I've added this task:");
        System.out.println("\t" + newDeadline.toString());
        System.out.println(" Now you have " + list.size() + " tasks in the list.");
        Storage.writeToFile(fileLocation, list);
    }

    /**
     * Add a new Event task and write to local file
     * @param list List of tasks
     * @param commandActual User input containing description and date of event
     */
    public static void newEvent (ArrayList<Task> list, String commandActual) {
        Ui.drawLine();
        String event[] = commandActual.split("/at ", 2);
        String name = event[0];
        String time = event[1];
        Event newEvent = new Event(name, time);
        list.add(newEvent);
        System.out.println(" Got it. I've added this task:");
        System.out.println("\t" + newEvent.toString());
        System.out.println(" Now you have " + list.size() + " tasks in the list.");
        Storage.writeToFile(fileLocation, list);
    }

    /**
     * Delete specified task
     * @param list List of tasks
     * @param index Index of task to delete
     */
    public static void deleteTask (ArrayList<Task> list, String index) {
        try {
            int indexToDelete = Integer.parseInt(index) - 1;
            Task toDelete = list.get(indexToDelete);
            Ui.drawLine();
            System.out.println("Noted. I've removed this task:");
            System.out.println("\t" + toDelete.toString());
            list.remove(indexToDelete);
            System.out.println(" Now you have " + list.size() + " tasks in the list.");
            Storage.writeToFile(fileLocation, list);
        } catch (IndexOutOfBoundsException e) {
            Ui.showIndexOutOfBounds();
        }
    }

    /**
     * Find tasks containing a specified keyword
     * @param list List of tasks to search
     * @param keyWord Keyword to search
     */
    public static void findTasks (ArrayList<Task> list, String keyWord) {
        int size = list.size();
        int numberOfTasks = 1;
        Ui.drawLine();
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < size; i++) {
            Task currTask = list.get(i);
            if (currTask.description.contains(keyWord)) {
                System.out.println(numberOfTasks + "." + list.get(i).toString());
                numberOfTasks++;
            }
        }
    }

    /**
     * Modifies the task list according to the user command given
     * @param commandType Type of command to be executed
     * @param commandActual Description and deadline of command, if any
     * @param fileLocation Path of local file to store command
     * @throws IllegalCommand Error if unsupported command is issued
     */
    public void executeTask (String commandType, String commandActual, String fileLocation) throws IllegalCommand {
        try {
            switch (commandType) {
            case "unmark":
                setUnmarked(list,commandActual);
                break;
            case "mark":
                setMarked(list,commandActual);
                break;
            case "todo":
                newToDo(list, commandActual);
                break;
            case "deadline":
                newDeadline(list,commandActual);
                break;
            case "event":
                newEvent(list,commandActual);
                break;
            case "list":
                listTasks(list);
                break;
            case "delete":
                deleteTask(list, commandActual);
                break;
            case "find":
                findTasks(list, commandActual);
                break;
            case "bye":
                isLast = true;
                break;
            default:
                throw new IllegalCommand();
            }
        } catch (EmptyToDo e) {
            Ui.showEmptyToDo();
        } catch (IllegalCommand e) {
            Ui.showIllegalCommand();
        }
    }

}
