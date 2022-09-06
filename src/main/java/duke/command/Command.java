package duke.command;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class Command {
    static void tryAddEvent(Task[] tasks, String line){
        try{
            if(line.replaceFirst("event", "").trim().equals("")){
                throw new DukeException();
            }
            addEvent(tasks, line);
        } catch (DukeException e){
            Task.printHorizontalLine();
            System.out.println("     T_T OOPS!!! The description of an event cannot be empty.");
            Task.printHorizontalLine();
        }
    }
    static void addEvent(Task[] tasks, String line) {
        String[] DescriptionAt = line.replaceFirst("event ", "").split(" /at ");
        String eventDescription = DescriptionAt[0];
        String at = DescriptionAt[1];
        tasks[Event.getNumberOfTasks()] = new Event(eventDescription, at);
        int eventId = Task.getNumberOfTasks() - 1;
        tasks[eventId].printNewTask();
    }
    static void tryAddDeadline(Task[] tasks, String line){
        try{
            if(line.replaceFirst("deadline", "").trim().equals("")){
                throw new DukeException();
            }
            addDeadline(tasks, line);
        } catch (DukeException e){
            Task.printHorizontalLine();
            System.out.println("     T_T OOPS!!! The description of a deadline cannot be empty.");
            Task.printHorizontalLine();
        }
    }
    private static void addDeadline(Task[] tasks, String line) {
        String[] descriptionBy = line.replaceFirst("deadline ", "").split(" /by ");
        String deadlineDescription = descriptionBy[0];
        String by = descriptionBy[1];
        tasks[Deadline.getNumberOfTasks()] = new Deadline(deadlineDescription, by);
        int deadlineId = Task.getNumberOfTasks() - 1;
        tasks[deadlineId].printNewTask();
    }

    static void tryAddTodo(Task[] tasks, String line){
        try{
            if(line.replaceFirst("todo", "").trim().equals("")){
                throw new DukeException();
            }
            addTodo(tasks, line);
        } catch (DukeException e){
            Task.printHorizontalLine();
            System.out.println("     T_T OOPS!!! The description of a todo cannot be empty.");
            Task.printHorizontalLine();
        }
    }

    private static void addTodo(Task[] tasks, String line) {
        String todoDescription = line.replaceFirst("todo ", "");
        tasks[Todo.getNumberOfTasks()] = new Todo(todoDescription);
        int todoId = Todo.getNumberOfTasks() - 1;
        tasks[todoId].printNewTask();
    }
    static void tryUnmarkTask(Task[] tasks, String line){
        try{
            if(line.replaceFirst("unmark", "").trim().equals("")){
                throw new DukeException();
            }
            unmarkTask(tasks, line.split(" "));
        } catch (DukeException e){
            Task.printHorizontalLine();
            System.out.println("     T_T OOPS!!! The description of an unmark cannot be empty.");
            Task.printHorizontalLine();
        }
    }
    private static void unmarkTask(Task[] tasks, String[] parsedInput) {
        int unmarkId = Integer.parseInt(parsedInput[1]) - 1;
        tasks[unmarkId].setNotDone();
        tasks[unmarkId].printUnmark();
    }

    static void tryMarkTask(Task[] tasks, String line){
        try{
            if(line.replaceFirst("mark", "").trim().equals("")){
                throw new DukeException();
            }
            markTask(tasks, line.split(" "));
        } catch (DukeException e){
            Task.printHorizontalLine();
            System.out.println("     T_T OOPS!!! The description of a mark cannot be empty.");
            Task.printHorizontalLine();
        }
    }

    private static void markTask(Task[] tasks, String[] parsedInput) {
        int markId = Integer.parseInt(parsedInput[1]) - 1;
        tasks[markId].setDone();
        tasks[markId].printMark();
    }
}
