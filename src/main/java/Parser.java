import java.util.Scanner;

public class Parser {

    /**
     * Parses the command from user input and calls on corresponding action
     *
     * @param line user input
     * @param tasks the task list to modify
     *
     * @return the output from the action for the ui to display
     */
    public String parseCommand(String line, TaskList tasks) throws DukeException {
        if (line.equals("bye")) {
            return null;
        } else if (line.equals("list")) {
            return tasks.list();
        } else if (line.startsWith("mark")) {
            return tasks.mark(line);
        } else if (line.startsWith("unmark")) {
            return tasks.unmark(line);
        } else if (line.startsWith("todo")) {
            return tasks.createTodo(line);
        } else if (line.startsWith("event")) {
            return tasks.createEvent(line);
        } else if (line.startsWith("deadline")) {
            return tasks.createDeadline(line);
        } else if (line.startsWith("delete")) {
            return tasks.delete(line);
        } else if (line.startsWith("find")) {
            return tasks.find(line);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
