package handler;

import ui.UI;
import exception.DukeException;

public class InputHandler {

    private static final String TODO = "todo";
    private static final String LIST = "list";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String BYE = "bye";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String DELETE = "delete";

    public static void handleInput() {
        boolean exit = false;
        while (!exit) {
            String input = UI.getInput();
            String command = getCommand(input);
            switch (command) {
            case (BYE):
                TaskHandler.handleBye();
                exit = true;
                break;
            case (TODO):
                try {
                    TaskHandler.handleTodo(getAction(input));
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("todo what?");
                }
                break;
            case (LIST):
                TaskHandler.handleList();
                break;
            case (DEADLINE):
                try {
                    TaskHandler.handleDeadline(getAction(input));
                } catch (DukeException e) {
                    System.out.println("deadline missing some info...");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("deadline what?");
                }
                break;
            case (EVENT):
                try {
                    TaskHandler.handleEvent(getAction(input));
                } catch (DukeException e) {
                    System.out.println("event missing some info...");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("event what?");
                }
                break;
            case (MARK):
                try {
                    TaskHandler.handleMark(getAction(input));
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("mark what");
                }
                break;
            case (UNMARK):
                try {
                    TaskHandler.handleUnmark(getAction(input));
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("unmark what");
                }
                break;
            case (DELETE):
                try {
                    TaskHandler.handleDelete(getAction(input));
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("delete what?");
                }
                break;
            default:
                TaskHandler.handleInvalid(input);
            }
//            if (input.startsWith(BYE)) {
//                TaskHandler.handleBye();
//                break;
//            } else if (input.startsWith(TODO)) {
//                try {
//                    TaskHandler.handleTodo(input);
//                } catch (DukeException e) {
//                    System.out.println("todo what?");
//                }
//            } else if (input.startsWith(LIST)) {
//                TaskHandler.handleList();
//            } else if (input.startsWith(DEADLINE)) {
//                try {
//                    TaskHandler.handleDeadline(input);
//                } catch (DukeException e) {
//                    System.out.println("deadline missing some info...");
//                }
//            } else if (input.startsWith(EVENT)) {
//                try {
//                    TaskHandler.handleEvent(input);
//                } catch (DukeException e) {
//                    System.out.println("event missing some info...");
//                }
//            } else if (input.startsWith(MARK)) {
//                try {
//                    TaskHandler.handleMark(input);
//                } catch (DukeException e) {
//                    System.out.println("mark what");
//                }
//            } else if (input.startsWith(UNMARK)) {
//                try {
//                    TaskHandler.handleUnmark(input);
//                } catch (DukeException e) {
//                    System.out.println("unmark what");
//                }
//            } else if (input.startsWith(DELETE)) {
//
//            }
//            else {
//                TaskHandler.handleInvalid(input);
//            }
        }
    }

    private static String getCommand(String input) {
        return input.split(" ", 2)[0].trim();
    }

    private static String getAction(String input) throws ArrayIndexOutOfBoundsException {
        return input.split(" ", 2)[1].trim();
    }

}
