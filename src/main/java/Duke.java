import dukeExceptionsPackage.*;
import dukeTasksPackage.*;
import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private static final String COMMAND_BYE = "bye";
    private static final String MESSAGE_BYE = "Duke: Bye. Hope to see you again soon!";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DELETE = "delete";
    private static final String MESSAGE_DELETE = "Duke: Noted. I have deleted the task below: ";

    public static void main(String[] args) {
        String line;
        ArrayList<Task> tasksList = new ArrayList<Task>();
        int count = 0;
        Scanner in = new Scanner(System.in);
        System.out.println("Duke: Hello! I'm Duke\n" + "Duke: What can I do for you?");

        while (true) {
            line = in.nextLine();
            String[] words = line.split(" ");
            switch (words[0]) {
            case COMMAND_BYE:
                System.out.println(MESSAGE_BYE);
                break;
            case COMMAND_LIST:
                try {
                    if (tasksList.isEmpty()) {
                        throw new EmptyListException(line);
                    } else {
                        System.out.println("Here are the tasks for today: ");
                        for (int i = 0; i < tasksList.size(); i++) {
                            System.out.println(tasksList.get(i));
                        }
                    }
                } catch (EmptyListException e) {
                    System.out.println(e.getExceptionMessage());
                }
                break;
            case COMMAND_MARK:
                int itemNumber = Integer.parseInt(line.split(" ")[1]) - 1;
                try {
                    if (itemNumber < 0 || itemNumber > tasksList.size() - 1) {
                        throw new IllegalTaskNumber("this task does not exist");
                    } else {
                        Task toBeChanged = tasksList.get(itemNumber);
                        if (toBeChanged.isDone) {
                            throw new IllegalMarkingException("already marked");
                        } else {
                            toBeChanged.markAsDone(toBeChanged);
                        }
                    }
                } catch (IllegalMarkingException e) {
                    System.out.println(e.getExceptionMessage());
                } catch (IllegalTaskNumber f) {
                    System.out.println(f.getExceptionMessage());
                }
                break;
            case COMMAND_UNMARK:
                itemNumber = Integer.parseInt(line.split(" ")[1]) - 1;
                try {
                    if (itemNumber < 0 || itemNumber > tasksList.size() - 1) {
                        throw new IllegalTaskNumber("this task does not exist");
                    } else {
                        Task toBeChanged = tasksList.get(itemNumber);
                        if (toBeChanged.isDone) {
                            toBeChanged.markAsUndone(toBeChanged);
                        } else {
                            throw new IllegalUnmarkingException("still unmarked");
                        }
                    }
                } catch (IllegalUnmarkingException e) {
                    System.out.println(e.getExceptionMessage());
                } catch (IllegalTaskNumber f) {
                    System.out.println(f.getExceptionMessage());
                }
                break;
            case COMMAND_TODO:
                try {
                    if (line.equals("todo")) {
                        throw new EmptyDescriptionException(line);
                    } else {
                        String description = line.substring(line.indexOf("todo") + 5);
                        if (description.length() < 1) {
                            throw new EmptyDescriptionException(description);
                        } else {
                            Todo td = new Todo(description);
                            tasksList.add(td);
                            System.out.println("Got it. I've added this task: " + System.lineSeparator() +
                                    td + System.lineSeparator() + "Now you have " + tasksList.size() + " tasks in the list");
                        }
                    }
                } catch (EmptyDescriptionException e){
                    System.out.println(e.getExceptionMessage());
                }
                break;
            case COMMAND_DEADLINE:
                try {
                    if (line.equals("deadline")) {
                        throw new EmptyDescriptionException(line);
                    } else {
                        String message = line.substring(line.indexOf("deadline") + 9);
                        if (message.length() < 1) {
                            throw new EmptyDescriptionException(message);
                        } else if (message.indexOf('/') == -1 || !message.contains("by")) {
                            throw new UnrecognisedDeadlineException(message);
                        } else {
                            String description = line.substring(line.indexOf("deadline") + 9, line.indexOf("/"));
                            String by = line.substring(line.indexOf("by") + 3);
                            Deadline d = new Deadline(description, by);
                            tasksList.add(d);
                            System.out.println("Got it. I've added this task: " + System.lineSeparator() +
                                    d + System.lineSeparator() + "Now you have " + tasksList.size() + " tasks in the list");
                        }
                    }
                } catch (EmptyDescriptionException e) {
                    System.out.println(e.getExceptionMessage());
                } catch (UnrecognisedDeadlineException f) {
                    System.out.println(f.getExceptionMessage());
                }
                break;
            case COMMAND_EVENT:
                try {
                    if (line.equals("event")) {
                        throw new EmptyDescriptionException(line);
                    } else {
                        String message = line.substring(line.indexOf("event") + 6);
                        if (message.length() < 1) {
                            throw new EmptyDescriptionException(message);
                        } else if (message.indexOf('/') == -1 || !message.contains("at")) {
                            throw new UnrecognisedEventException(message);
                        } else {
                            String description = line.substring(line.indexOf("event") + 6, line.indexOf("/"));
                            String time = line.substring(line.indexOf("at") + 3);
                            Event e = new Event(description, time);
                            tasksList.add(e);
                            System.out.println("Got it. I've added this task: " + System.lineSeparator() +
                                    e + System.lineSeparator() + "Now you have " + tasksList.size() + " tasks in the list.");
                        }
                    }
                } catch (EmptyDescriptionException e) {
                    System.out.println(e.getExceptionMessage());
                } catch (UnrecognisedEventException f) {
                    System.out.println(f.getExceptionMessage());
                }
                break;
            case COMMAND_DELETE:
                itemNumber = Integer.parseInt(line.split(" ")[1]) - 1;
                Task toBeRemoved = tasksList.get(itemNumber);
                tasksList.remove(itemNumber);
                System.out.println(MESSAGE_DELETE);
                System.out.println(toBeRemoved.toString());
                System.out.println("You now have " + tasksList.size() + " tasks in the list.");
                break;
            default:
                try {
                    String message = "error";
                    throw new UnrecognisedInput(message);
                } catch (UnrecognisedInput f) {
                    System.out.println(f.getExceptionMessage());
                }
                break;
            }
        }
    }
}
