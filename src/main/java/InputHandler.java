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
                try {
                    TaskHandler.handleTodo(input);
                } catch (DukeException e) {
                    System.out.println("todo what?");
                }
            } else if (input.startsWith(LIST)) {
                TaskHandler.handleList();
            } else if (input.startsWith(DEADLINE)) {
                try {
                    TaskHandler.handleDeadline(input);
                } catch (DukeException e) {
                    System.out.println("deadline missing some info...");
                    ;
                }
            } else if (input.startsWith(EVENT)) {
                try {
                    TaskHandler.handleEvent(input);
                } catch (DukeException e) {
                    System.out.println("event missing some info...");
                    ;
                }
            } else if (input.startsWith(MARK)) {
                try {
                    TaskHandler.handleMark(input);
                } catch (DukeException e) {
                    System.out.println("mark what");
                    ;
                }
            } else if (input.startsWith(UNMARK)) {
                try {
                    TaskHandler.handleUnmark(input);
                } catch (DukeException e) {
                    System.out.println("unmark what");
                }
            } else {
                TaskHandler.handleInvalid(input);
            }
        }
    }
}
