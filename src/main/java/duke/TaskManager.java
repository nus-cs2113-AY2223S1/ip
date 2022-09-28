package duke;

import duke.data.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A class that contains an ArrayList of tasks and that contains methods to handle
 * operations of the different task and the list
 */
public class TaskManager {

    public static ArrayList<Task> Tasks;

    /**
     * Initializes the TaskManager class which has an ArrayList of tasks and
     * also calls the method from Storage class to load tasks saved to the
     * save file onto the current list
     */
    public TaskManager() {
        this.Tasks = new ArrayList<Task>();
        Storage.loadTasks(Tasks);
    }

    /**
     * Prints out texts, other UI and the task to signify that the task has been
     * successfully added
     * @param task the task to be signified to the user that it has been successfully
     *             added
     */
    public void printCompletion(Task task) {
        System.out.println("I have added this task!");
        System.out.println(task);
        System.out.println("Okay loser! You now have " + Tasks.size() + " in the list. Get to work!");
        UI.printLine();
    }

    /**
     * stores the task represented in the text into the Tasks list
     * @param text the text that contains a string which names the task to
     *             be stored in the list
     */
    public void storeTodo(String text) {
        UI.printLine();
        try {
            Todo newTodo = new Todo(Parser.parseTodo(text));
            Tasks.add(newTodo);
            Storage.saveTasks(Tasks);
            printCompletion(newTodo);
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Much ado about nothing");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * stores the deadline represented in the text into the Tasks list
     * @param text the text that contains a string which names the deadline to
     *             be stored in the list and the deadline time associated with it
     */
    public void storeDeadline(String text) {
        UI.printLine();
        try {
            Deadline newDeadline = new Deadline(Parser.parseDeadline(text), Parser.parseDeadlineDate(text));
            Tasks.add(newDeadline);
            Storage.saveTasks(Tasks);
            printCompletion(newDeadline);
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Deadlines, that's all life's about, but you gotta tell me which!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * stores the Event represented in the text into the Tasks list
     * @param text the text that contains a string which names the event to
     *             be stored in the list and the event time associated with it
     */
    public void storeEvent(String text) {
        UI.printLine();
        try {
            Event newEvent = new Event(Parser.parseEvent(text), Parser.parseEventDate(text));
            Tasks.add(newEvent);
            Storage.saveTasks(Tasks);
            printCompletion(newEvent);
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Which event? An uneventful life is the key to longevity!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Prints the task currently contained in the Tasks list, numbered with
     * 1 based indexing
     */
    public void printTasks() {
        UI.printLine();
        System.out.println("It just keeps piling up");
        int count = 1;
        for (Task task : Tasks) {
            System.out.println(count + "." + task);
            count++;
        }
        if (count==1) {
            System.out.println("Maybe find something else to do?");
        }
        UI.printLine();
    }

    /**
     * Gets the Task at the position(1 based indexing) in the Tasks list
     * @param position the index (1 based indexing) of the task in the Tasks list
     * @return the task specified at the position
     */
    public Task getTask(String position) {
        return Tasks.get(Integer.valueOf(position) - 1);
    }

    /**
     * Marks the task specified at the position in the list as done
     * @param text contains the command mark and the position of the task to be marked
     * @throws IOException
     */
    public void markTasks(String text) throws IOException {
        try {
            String position=Parser.parseMark(text);
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Mark?!! Mark what?!");
            return;
        }
        try {
            String position=Parser.parseMark(text);
            Integer.parseInt(position);
        }
        catch (NumberFormatException e) {
            String position=Parser.parseMark(text);
            System.out.println(position+"?! You know I only take numbers!");
            return;
        }
        try {
            String position=Parser.parseMark(text);
            Task task=getTask(position);
        }
        catch (IndexOutOfBoundsException e) {
            String position=Parser.parseMark(text);
            System.out.println("You got no task at "+position+" you bozo!");
            return;
        }
        UI.printLine();
        System.out.println("I've marked this task as done, now go do something else!:");
        String position=Parser.parseMark(text);
        getTask(position).setDone();
        Storage.saveTasks(Tasks);
        System.out.println(getTask(position));
        UI.printLine();
    }

    public void unmarkTasks(String text) throws IOException {
        try {
            String position=Parser.parseUnmark(text);
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Unmark?!! Unark what?!");
            return;
        }
        try {
            String position=Parser.parseUnmark(text);
            Integer.parseInt(position);
        }
        catch (NumberFormatException e) {
            String position=Parser.parseUnmark(text);
            System.out.println(position+"?! You know I only take numbers!");
            return;
        }
        try {
            String position=Parser.parseUnmark(text);
            Task task=getTask(position);
        }
        catch (IndexOutOfBoundsException e) {
            String position=Parser.parseUnmark(text);
            System.out.println("You got no task at "+position+" you bozo!");
            return;
        }

        UI.printLine();
        System.out.println("I've marked this task as not done, get working!:");
        String position=Parser.parseUnmark(text);
        getTask(position).setNotDone();
        Storage.saveTasks(Tasks);
        System.out.println(getTask(position));
        UI.printLine();
    }

    public void deleteTasks(String text) throws IOException {
        try {
            String position=Parser.parseDelete(text);
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Delete?!! Delete what?!");
            return;
        }
        try {
            String position=Parser.parseDelete(text);
            Integer.parseInt(position);
        }
        catch (NumberFormatException e) {
            String position=Parser.parseDelete(text);
            System.out.println(position+"?! You know I only take numbers!");
            return;
        }
        try {
            String position=Parser.parseDelete(text);
            Task task=getTask(position);
        }
        catch (IndexOutOfBoundsException e) {
            String position=Parser.parseDelete(text);
            System.out.println("You got no task at "+position+" you bozo!");
            return;
        }

        UI.printLine();
        System.out.println("I've deleted this task, lucky!:");
        String position=Parser.parseDelete(text);
        Tasks.remove(Integer.valueOf(position) - 1);
        Storage.saveTasks(Tasks);
        System.out.println("You now have "+Tasks.size()+" tasks left. Yay!");
        UI.printLine();
    }

    public static void addFromStorage(Task task) {
        Tasks.add(task);
    }

    public void findTasks (String text) {
        try {
            String find = Parser.parseFind(text);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Find?!! Find what?!");
            return;
        }
        UI.printLine();
        System.out.println("Look what I found:");
        int count = 1;
        String find = Parser.parseFind(text);
        for (Task task : Tasks) {
            if (task.getDescription().contains(find)){
                System.out.println(count + "." + task);
                count++;
            }
        }
        if (count==1) {
            System.out.println("Just about nothin'");
        }
        UI.printLine();
    }
}


