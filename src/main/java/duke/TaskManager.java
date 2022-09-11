package duke;

import java.util.ArrayList;
import java.util.List;
import duke.tasks.*;

public class TaskManager {
    public static final String END_OF_LINE = "____________________\n";
    private static List<Task> tasks = new ArrayList<Task>();

    public void addNewTodo(String taskName, boolean toPrint) {
        tasks.add(new Todos(taskName));
        if (toPrint) {
            System.out.println("Added new todo task: " + taskName + "\n " + tasks.get(tasks.size() - 1).toString()
                    + "\nYou have " + tasks.size() + " tasks in the list." + tasks);
        }
    }

    public void addNewDeadline(String taskName, String toBeDoneBy, boolean toPrint) {
        tasks.add(new Deadlines(taskName, toBeDoneBy));
        if (toPrint) {
            System.out.println("Added new deadline task: " + taskName + "\n " + tasks.get(tasks.size() - 1).toString()
                    + "\nYou have " + tasks.size() + " tasks in the list." + END_OF_LINE);
        }
    }

    public void addNewEvent(String taskName, String happeningAt, boolean toPrint) {
        tasks.add(new Events(taskName, happeningAt));
        if (toPrint) {
            System.out.println("Added new event task: " + taskName + "\n " + tasks.get(tasks.size() - 1).toString()
                    + "\nYou have " + tasks.size() + " tasks in the list." + END_OF_LINE);
        }
    }

    public void listTasks() {
        System.out.println("Here is your list of tasks.");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
        System.out.print(END_OF_LINE);
    }

    public void markTasks(boolean toMark, int taskIndex, boolean toPrint) throws DukeException.IllegalMarkTargetException {
        String responseLine;
        if (taskIndex < 1 || taskIndex > tasks.size()) {
            throw new DukeException.IllegalMarkTargetException();
        }
        if (toMark) {
            if (tasks.get(taskIndex - 1).getIsDone()) {
                System.out.println("This task has already been marked done!" + END_OF_LINE);
            }
            tasks.get(taskIndex - 1).setIsDone();
            responseLine = tasks.get(taskIndex - 1).getTaskName()
                    + " has been marked as done!" + END_OF_LINE;
        } else {
            if (!tasks.get(taskIndex - 1).getIsDone()) {
                System.out.println("This task has already been unmarked!" + END_OF_LINE);
                return;
            }
            tasks.get(taskIndex - 1).setIsNotDone();
            responseLine = tasks.get(taskIndex - 1).getTaskName()
                    + " has been unmarked!" + END_OF_LINE;
        }
        if (toPrint) {
            System.out.println(responseLine);
        }
    }

    public int getNumberOfTasks() {
        return tasks.size();
    }

    public String saveTaskList() {
        String data = "";
        for (int i = 0; i < tasks.size(); i++) {
            String type = tasks.get(i).getType();
            if (type.equals("T")) {
                Todos temp = (Todos) tasks.get(i);
                data += (type + "|" + temp.getTaskName() + "|" + (temp.getIsDone() ? "1" : "0") + "\n");
            } else if (type.equals("D")) {
                Deadlines temp = (Deadlines) tasks.get(i);
                data += (type + "|" + temp.getTaskName() + "|" + (temp.getIsDone() ? "1" : "0") + "|"
                        + Deadlines.getToBeDoneBy() + "\n");
            } else {
                Events temp = (Events) tasks.get(i);
                data += (type + "|" + temp.getTaskName() + "|" + (temp.getIsDone() ? "1" : "0") + "|"
                        + Events.getHappeningAt() + "\n");
            }
        }
        return data;
    }
}