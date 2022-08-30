public class InputHandler {

    private static final String TODO = "todo";
    private static final String LIST = "list";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String BYE = "bye";
    private static final String MARK = "add";
    private static final String UNMARK = "unmark";

    public static void handleInput() {
        while (true) {
            String input = UI.getInput();
            if (input.startsWith(BYE)) {
                break;
            } else if (input.startsWith(TODO)) {
                //do something
            } else if (input.startsWith(LIST)) {
                //do something
            } else if (input.startsWith(DEADLINE)) {
                //do something
            } else if (input.startsWith(EVENT)) {
                //do something
            } else if (input.startsWith(MARK)) {
                //do something
            } else if (input.startsWith(UNMARK)) {
                //do something
            } else {

            }
        }
    }
}
