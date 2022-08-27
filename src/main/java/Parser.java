public class Parser {
    private static final String TODO_PHRASE = "todo";
    private static final String DEADLINE_PHRASE = "deadline";
    private static final String EVENT_PHRASE = "event";

    public Task parseInput(String type, String input) {
        int descriptionIndex;
        String description;
        Task newTask;

        switch (type) {
        case TODO_PHRASE:
            descriptionIndex = input.indexOf(TODO_PHRASE);

            // add one to remove space
            description =
                    input.substring(descriptionIndex + TODO_PHRASE.length() + 1, input.length());

            newTask = new Todo(description);
            break;
        case DEADLINE_PHRASE:
            final String BY_PHRASE = "/by";
            descriptionIndex = input.indexOf(DEADLINE_PHRASE);
            int byIndex = input.indexOf(BY_PHRASE);

            // add one to remove space and minus one to remove space
            description =
                    input.substring(descriptionIndex + DEADLINE_PHRASE.length() + 1, byIndex - 1);
            String by = input.substring(byIndex + BY_PHRASE.length() + 1, input.length());

            newTask = new Deadline(description, by);
            break;
        case EVENT_PHRASE:
            final String AT_PHRASE = "/at";
            descriptionIndex = input.indexOf(EVENT_PHRASE);
            int atIndex = input.indexOf(AT_PHRASE);

            // add one to remove space and minus one to remove space
            description =
                    input.substring(descriptionIndex + EVENT_PHRASE.length() + 1, atIndex - 1);
            String at = input.substring(atIndex + AT_PHRASE.length() + 1, input.length());

            newTask = new Event(description, at);
            break;

        default:
            description = input;
            newTask = new Task(description);
            break;
        }

        return newTask;
    }
}
