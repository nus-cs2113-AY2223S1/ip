package duke;

import duke.data.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;
import java.util.ArrayList;

public class TaskManager {

    public static ArrayList<Task> Tasks;
    public TaskManager() {
        this.Tasks = new ArrayList<Task>();
        Storage.loadTasks(Tasks);
    }

    public void printCompletion(Task task) {
        System.out.println("I have added this task!");
        System.out.println(task);
        System.out.println("Okay loser! You now have " + Tasks.size() + " in the list. Get to work!");
        UI.printLine();
    }
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

    public void printTasks() {
        UI.printLine();
        System.out.println("It just keeps piling up");
        int count = 1;
        for (Task i : Tasks) {
            System.out.println(count + "." + i);
            count++;
        }
        UI.printLine();
    }

    public void markTasks(String text) throws IOException {
        String[] result = text.split(" ");
        try {
            String x=result[1];
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Mark?!! Mark what?!");
            return;
        }
        try {
            Integer.parseInt(result[1]);
        }
        catch (NumberFormatException e) {
            System.out.println(result[1]+"?! You know I only take numbers!");
            return;
        }
        try {
            Task x=Tasks.get(Integer.valueOf(result[1]) - 1);
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("You got no task at "+result[1]+" you bozo!");
            return;
        }
        UI.printLine();
        System.out.println("I've marked this task as done, now go do something else!:");
        Tasks.get(Integer.valueOf(result[1]) - 1).setDone();
        Storage.saveTasks(Tasks);
        System.out.println(Tasks.get(Integer.valueOf(result[1]) - 1));
        UI.printLine();
    }

    public void unmarkTasks(String text) throws IOException {
        String[] result = text.split(" ");
        try {
            String x=result[1];
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Unmark?!! Unark what?!");
            return;
        }
        try {
            Integer.parseInt(result[1]);
        }
        catch (NumberFormatException e) {
            System.out.println(result[1]+"?! You know I only take numbers!");
            return;
        }
        try {
            Task x=Tasks.get(Integer.valueOf(result[1]) - 1);
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("You got no task at "+result[1]+" you bozo!");
            return;
        }

        UI.printLine();
        System.out.println("I've marked this task as not done, get working!:");
        Tasks.get(Integer.valueOf(result[1]) - 1).setNotDone();
        Storage.saveTasks(Tasks);
        System.out.println(Tasks.get(Integer.valueOf(result[1]) - 1));
        UI.printLine();
    }

    public void deleteTasks(String text) throws IOException {
        String[] result = text.split(" ");
        try {
            String x=result[1];
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Delete?!! Delete what?!");
            return;
        }
        try {
            Integer.parseInt(result[1]);
        }
        catch (NumberFormatException e) {
            System.out.println(result[1]+"?! You know I only take numbers!");
            return;
        }
        try {
            Task x=Tasks.get(Integer.valueOf(result[1]) - 1);
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("You got no task at "+result[1]+" you bozo!");
            return;
        }
        UI.printLine();
        System.out.println("I've deleted this task, lucky!:");
        System.out.println(Tasks.get(Integer.valueOf(result[1]) - 1));
        Tasks.remove(Integer.valueOf(result[1]) - 1);
        Storage.saveTasks(Tasks);
        System.out.println("You now have "+Tasks.size()+" tasks left. Yay!");
        UI.printLine();
    }

    public static void addFromStorage(Task task) {
        Tasks.add(task);
    }
}


