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
            processNewTodo(command, taskManager);
            break;
        case COMMAND_DEADLINES:
            processNewDeadline(command, taskManager);
            break;
        case COMMAND_EVENTS:
            processNewEvent(command, taskManager);
            break;
        case COMMAND_MARK:
            processMark(command, taskManager);
            break;
        case COMMAND_UNMARK:
            processUnmark(command, taskManager);
            break;
        default:
            System.out.println("Invalid command");
            break;
        }

        return true;
    }

    private static void processUnmark(String command, TaskManager taskManager) {
        String[] arrOfCommand = command.split(" ");
        if (arrOfCommand.length > 1 && isInteger(arrOfCommand[1])) {
            taskManager.markTasks(false, Integer.parseInt(arrOfCommand[1]));
        } else {
            System.out.println("Please input a valid unmark");
        }
    }

    private static void processMark(String command, TaskManager taskManager) {
        String[] arrOfCommand = command.split(" ");
        if (arrOfCommand.length > 1 && isInteger(arrOfCommand[1])) {
            taskManager.markTasks(true, Integer.parseInt(arrOfCommand[1]));
        } else {
            System.out.println("Please input a valid mark");
        }
    }

    private static void processNewEvent(String command, TaskManager taskManager) {
        int spacePosition = command.indexOf(" ");
        int dividerPosition = command.indexOf("/");
        if(spacePosition < 0 || dividerPosition < 0 || dividerPosition + 2 > command.length()) {
            System.out.println("Please input a valid event");
            return;
        }
        String taskName = command.substring(spacePosition + 1, dividerPosition);
        String at = command.substring(dividerPosition + 1);
        taskManager.addNewEvent(taskName, at);
    }

    private static void processNewDeadline(String command, TaskManager taskManager) {
        int spacePosition = command.indexOf(" ");
        int dividerPosition = command.indexOf("/");
        if(spacePosition < 0 || dividerPosition < 0 || dividerPosition + 2 > command.length()) {
            System.out.println("Please input a valid deadline");
            return;
        }
        String taskName = command.substring(spacePosition + 1, dividerPosition);
        String by = command.substring(dividerPosition + 1);
        taskManager.addNewDeadline(taskName, by);
    }

    private static void processNewTodo(String command, TaskManager taskManager) {
        int spacePosition = command.indexOf(" ");
        if(spacePosition < 0) {
            System.out.println("Please input a valid todo");
            return;
        }
        taskManager.addNewTodo(command.substring(spacePosition + 1));
    }

    private static String extractKeyword(String command) {
        String[] arrOfCommand = command.split(" ");
        return arrOfCommand[0];
    }
}