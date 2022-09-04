public class Parser {
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_TODOS = "todo";
    private static final String COMMAND_DEADLINES = "deadline";
    private static final String COMMAND_EVENTS = "event";

    private static boolean isInteger(String toCheck) {
        try {
            int intValue = Integer.parseInt(toCheck);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isOnline(String command, TaskManager taskManager) {
        String keyword = extractKeyword(command);
        switch(keyword) {
        case COMMAND_BYE:
            System.out.println("Bye and see you again soon!");
            return false;
        case COMMAND_LIST:
            taskManager.listTasks();
            break;
        case COMMAND_TODOS:
            try {
                processNewTodo(command, taskManager);
            } catch (DukeException.IllegalTodoException e) {
                System.out.println("Please enter a name for the todo task!");
            }
            break;
        case COMMAND_DEADLINES:
            try {
                processNewDeadline(command, taskManager);
            } catch (DukeException.IllegalDeadlineFormatException e) {
                System.out.println("Please enter a deadline task followed by a '/' and indicate a deadline");
            } catch (DukeException.IllegalDeadlineDateException e) {
                System.out.println("Please enter a deadline to complete the task!");
            }
            break;
        case COMMAND_EVENTS:
            try {
                processNewEvent(command, taskManager);
            } catch (DukeException.IllegalEventFormatException e) {
                System.out.println("Please enter an event task followed by a '/' and indicate when");
            } catch (DukeException.IllegalEventDateException e) {
                System.out.println("Please enter when this event is happening!");
            }
            break;
        case COMMAND_MARK:
            try {
                processMarking(command, taskManager, true);
            } catch (DukeException.IllegalNoMarkIndexException e) {
                System.out.println("Please enter a task number to be marked");
            }
            break;
        case COMMAND_UNMARK:
            try {
                processMarking(command, taskManager, false);
            } catch (DukeException.IllegalNoMarkIndexException e) {
                System.out.println("Please enter a task number to be marked");
            }
            break;
        default:
            System.out.println("Invalid command");
            break;
        }

        return true;
    }
    private static void processMarking(String command, TaskManager taskManager, boolean toMark)
            throws DukeException.IllegalNoMarkIndexException {
        String[] arrOfCommand = command.split(" ");

        if (arrOfCommand.length <= 1 || !isInteger(arrOfCommand[1])) {
            throw new DukeException.IllegalNoMarkIndexException();
        }

        try {
            taskManager.markTasks(toMark, Integer.parseInt(arrOfCommand[1]));
        } catch (DukeException.IllegalMarkTargetException e) {
            System.out.println("Index of task is out of range");
        }
    }

    private static void processNewEvent(String command, TaskManager taskManager)
            throws DukeException.IllegalEventFormatException, DukeException.IllegalEventDateException {
        int spacePosition = command.indexOf(" ");
        int dividerPosition = command.indexOf("/");

        if(spacePosition < 0 || dividerPosition < 0) {
            throw new DukeException.IllegalEventFormatException();
        } else if (dividerPosition + 2 > command.length()) {
            throw new DukeException.IllegalEventDateException();
        }

        String taskName = command.substring(spacePosition + 1, dividerPosition);
        String at = command.substring(dividerPosition + 1);
        taskManager.addNewEvent(taskName, at);
    }

    private static void processNewDeadline(String command, TaskManager taskManager)
            throws DukeException.IllegalDeadlineFormatException, DukeException.IllegalDeadlineDateException {
        int spacePosition = command.indexOf(" ");
        int dividerPosition = command.indexOf("/");

        if(spacePosition < 0 || dividerPosition < 0) {
            throw new DukeException.IllegalDeadlineFormatException();
        } else if (dividerPosition + 2 > command.length()) {
            throw new DukeException.IllegalDeadlineDateException();
        }

        String taskName = command.substring(spacePosition + 1, dividerPosition);
        String by = command.substring(dividerPosition + 1);
        taskManager.addNewDeadline(taskName, by);
    }

    private static void processNewTodo(String command, TaskManager taskManager)
            throws DukeException.IllegalTodoException {
        int spacePosition = command.indexOf(" ");
        if(spacePosition < 0) {
            throw new DukeException.IllegalTodoException();
        }
        taskManager.addNewTodo(command.substring(spacePosition + 1));
    }

    private static String extractKeyword(String command) {
        String[] arrOfCommand = command.split(" ");
        return arrOfCommand[0];
    }
}