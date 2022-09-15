package duke;
import duke.task.Deadline;
import duke.task.Todo;
import duke.task.Event;
import duke.task.Task;

import java.util.ArrayList;
public class List {
    public static ArrayList<duke.task.Task> tasks = new ArrayList<>();
    public static final String deadline = "deadline";
    public static final String todo = "todo";
    public static final String event = "event";
    public static final String by = " /by ";
    public static final String at = " /at ";
    public static final String space = " ";

    public List() {

    }

    public int getListSize() {
        return tasks.size();
    }

    public void addTask(String input) {
        try{
            translateTask(input);
            printTaskAdded();
        } catch (DukeException e){
            Message.printError();
        }
    }
    public static void printTaskDeleted(Task taskToDelete) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println(taskToDelete);
        System.out.println("Now you have "+ tasks.size() + "tasks in the list.");
    }

    public void deleteTask(int index) throws DukeException {
        try{
            Task taskToDelete = tasks.get(index - 1);
            tasks.remove(index - 1);
            printTaskDeleted(taskToDelete);
        } catch (IndexOutOfBoundsException e){
            throw new DukeException();
        }
    }

    public void printList() {
        Message.printHorizontalLine();
        System.out.println("Here are the tasks in your list:");
        for (int i=0; i < tasks.size(); i ++) {
            System.out.print(i + 1 + ". ");
            System.out.println(tasks.get(i));
        }
        Message.printHorizontalLine();
    }

    public void markItemDone(int i) {
        tasks.get(i - 1).markDone();
    }

    public void unmarkItemDone(int i) {
        tasks.get(i - 1).unmarkDone();
    }

    public void translateTask(String input) throws DukeException {
        String[] divideByFirstSpace = input.split(space, 2);
        if (divideByFirstSpace.length < 2 || divideByFirstSpace[1].equals("")){
            throw new DukeException();
        }
        switch (divideByFirstSpace[0]) {
            case deadline: {
                String[] details = divideByFirstSpace[1].split(by);
                tasks.add(new Deadline(details[0], details[1]));
                break;
            }
            case event: {
                String[] details = divideByFirstSpace[1].split(at);
                tasks.add(new Event(details[0], details[1]));
                break;
            }
            case todo:
                tasks.add(new Todo(divideByFirstSpace[1]));
                break;
            default:
                throw new DukeException();
        }
    }

    public void printTaskAdded() {
        Message.printHorizontalLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + (tasks.size()) + " tasks in the list.");
        Message.printHorizontalLine();
    }

}
