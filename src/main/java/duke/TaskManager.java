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
        for (Task task : Tasks) {
            System.out.println(count + "." + task);
            count++;
        }
        if (count==1) {
            System.out.println("Maybe find something else to do?");
        }
        UI.printLine();
    }

    public Task getTask(String position) {
        return Tasks.get(Integer.valueOf(position) - 1);
    }

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
            String position = Parser.parseFind(text);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Find?!! Find what?!");
            return;
        }
        UI.printLine();
        System.out.println("Look what I found:");
        int count = 1;
        for (Task task : Tasks) {
            if (task.getDescription().contains(text)){
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


