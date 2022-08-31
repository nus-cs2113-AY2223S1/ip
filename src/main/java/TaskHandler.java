public class TaskHandler {
    private static final String BYE_MESSAGE = "Bye! ;)";

    public static void handleBye() {
        System.out.println(BYE_MESSAGE);
    }

    public static void handleTodo(String input) {
        String[] splitInput = input.split(" ", 2);
        Todo todo = new Todo(splitInput[1]);
        TaskList.addTodo(todo);
    }

    public static void handleList() {
        TaskList.printList();
    }

    public static void handleMark(String input) {
        String[] splitInput = input.split(" ", 2);
        int target = Integer.parseInt(splitInput[1]);
        TaskList.markTarget(target);

    }

    public static void handleUnmark(String input) {
        String[] splitInput = input.split(" ", 2);
        int target = Integer.parseInt(splitInput[1]);
        TaskList.unmarkTarget(target);
    }

    public static void handleDeadline(String input) {
        String[] splitInput = input.split(" ", 2);
        String[] splitCommand = splitInput[1].split(" /by ", 2); // 0 is description, 1 is by
        Deadline deadline = new Deadline(splitCommand[0], splitCommand[1]);
        TaskList.addDeadLine(deadline);
    }

    public static void handleEvent(String input) {
        String[] splitInput = input.split(" ", 2);
        String[] splitCommand = splitInput[1].split(" /at ", 2); // 0 is description, 1 is by
        Event event = new Event(splitCommand[0], splitCommand[1]);
        TaskList.addEvent(event);
    }

    public static void handleInvalid(String input) {
        System.out.println("\"" + input + "\"" + " is not a valid command, please try again!");
    }

}
