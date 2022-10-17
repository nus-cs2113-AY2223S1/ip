package Duke;

public class Parser {
    /**
     * Returns type of input entered by user
     * @param input
     * @return types as string if recognized. Empty string if type is not recognized
     */
    String getType(String input) {
        String type;
        if (input.indexOf(' ') == -1) {
            type = input;
        } else {
            type = input.substring(0, input.indexOf(' '));
        }
        if (type.equals("event")) {
            return "event";
        } else if (type.equals("deadline")) {
            return "deadline";
        } else if (type.equals("todo")) {
            return "todo";
        } else if (input.equals("bye")) {
            return "bye";
        } else if (type.equals("mark")) {
            return "mark";
        } else if (type.equals("unmark")) {
            return "unmark";
        } else if (type.equals("list")) {
            return "list";
        } else if (type.equals("bye")) {
            return "bye";
        } else if (type.equals("delete")) {
            return "delete";
        } else if (type.equals("find")) {
            return "find";
        } else {
            return "";
        }
    }

    /**
     * Returns description of deadline type task
     * @param input
     * @return deadline description
     */
    String getDeadlineDescription(String input) {
        if (input.indexOf("/by") == -1) {
            return "";
        }
        String description = input.substring(input.indexOf(' '), input.indexOf("/by"));
        return description.trim();
    }

    /**
     * Returns description of event type task
     * @param input
     * @return event deadline
     */

    String getEventDescription(String input) {
        if (input.indexOf("/at") == -1) {
            return "";
        }
        String description = input.substring(input.indexOf(' '), input.indexOf("/at"));
        return description.trim();
    }

    /**
     * Returns description of todo type task
     * @param input
     * @return todo description
     */
    String getTodoDescription(String input) {
        if ((input.indexOf(' ')) == -1) {
            return "";
        }
        String description = input.substring(input.indexOf(' '));
        return description.trim();
    }

    /**
     * Returns time of deadline task
     * @param input
     * @return deadline time
     */

    String getDeadlineTime(String input) {
        if (input.indexOf("/by") == -1) {
            return "";
        }
        String by = input.substring(input.indexOf("/by") + 3);
        return by.trim();
    }

    /**
     * Returns time of event task
     * @param input
     * @return event time
     */

    String getEventTime(String input) {
        if (input.indexOf("/at") == -1) {
            return "";
        }
        String at = input.substring(input.indexOf("/at") + 3);
        return at.trim();
    }

    /**
     * Returns the taskNo for delete, mark and unmark functions
     * @param input
     * @return -1 if number not detected. The number otherwise.
     */

    int findTaskNo(String input) {
        if(input.indexOf(' ') == -1) {
            return -1;
        }
        String taskNoAsString = input.substring(input.indexOf(' ')).trim();
        int taskNo = -1;
        try {
            taskNo = Integer.parseInt(taskNoAsString);
        } catch (NumberFormatException e) {
            taskNo = -1;
        } finally {
            return taskNo;
        }
    }

    /**
     * Returns searchstring for find functioj
     * @param input
     * @return searchstring without leading spaces if available. Empty string otherwise
     */
    String findSearchString(String input) {
        if(input.length() <= 4) {
            return "";
        }
        String search = input.substring(4);
        return search.trim();
    }
}
