public class InputHandler {

    private static final String TODO = "todo";
    private static final String LIST = "list";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String BYE = "bye";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";

    public static void handleInput() {
        while (true) {
            String input = UI.getInput();
            if (input.startsWith(BYE)) {
                TaskHandler.handleBye();
                break;
            } else if (input.startsWith(TODO)) {
                TaskHandler.handleTodo(input);
            } else if (input.startsWith(LIST)) {
                TaskHandler.handleList();
            } else if (input.startsWith(DEADLINE)) {
                TaskHandler.handleDeadline(input);
            } else if (input.startsWith(EVENT)) {
                TaskHandler.handleEvent(input);
            } else if (input.startsWith(MARK)) {
                TaskHandler.handleMark(input);
            } else if (input.startsWith(UNMARK)) {
                TaskHandler.handleUnmark(input);
            } else {
                TaskHandler.handleInvalid(input);
            }
        }
    }
}
