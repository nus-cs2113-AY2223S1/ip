public abstract class Parser {
    public static Task parseTask(String type, String input) {
        int descriptionIndex;
        String description;
        Task newTask;

        switch (type) {
        case InputManager.TODO_PHRASE:
            descriptionIndex = input.indexOf(InputManager.TODO_PHRASE);

            // add one to remove space
            description = input.substring(descriptionIndex + InputManager.TODO_PHRASE.length() + 1,
                    input.length());

            newTask = new Todo(description);
            break;
        case InputManager.DEADLINE_PHRASE:
            descriptionIndex = input.indexOf(InputManager.DEADLINE_PHRASE);
            int byIndex = input.indexOf(InputManager.BY_PHRASE);

            // add one to remove space
            // minus one to remove space
            description = input.substring(
                    descriptionIndex + InputManager.DEADLINE_PHRASE.length() + 1, byIndex - 1);
            String by =
                    input.substring(byIndex + InputManager.BY_PHRASE.length() + 1, input.length());

            newTask = new Deadline(description, by);
            break;
        case InputManager.EVENT_PHRASE:
            descriptionIndex = input.indexOf(InputManager.EVENT_PHRASE);
            int atIndex = input.indexOf(InputManager.AT_PHRASE);

            // add one to remove space
            // minus one to remove space
            description = input.substring(descriptionIndex + InputManager.EVENT_PHRASE.length() + 1,
                    atIndex - 1);
            String at =
                    input.substring(atIndex + InputManager.AT_PHRASE.length() + 1, input.length());

            newTask = new Event(description, at);
            break;
        default:
            // add new Task by default
            description = input;
            newTask = new Todo(description);
            break;
        }

        return newTask;
    }

    public static int parseTaskNumber(String type, String input) {
        String taskNumString;
        int taskNumInt;

        switch (type) {
        case InputManager.MARK_PHRASE:
            taskNumString = input.substring(InputManager.MARK_PHRASE.length() + 1);
            taskNumInt = Integer.parseInt(taskNumString);
            break;
        case InputManager.UNMARK_PHRASE:
            taskNumString = input.substring(InputManager.UNMARK_PHRASE.length() + 1);
            taskNumInt = Integer.parseInt(taskNumString);
            break;

        default:
            taskNumInt = 1;
            break;
        }

        // zero-index
        taskNumInt -= 1;

        return taskNumInt;
    }
}
