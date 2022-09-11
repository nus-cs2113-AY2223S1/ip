package duke.text;

public enum InfoText {
    INFO_DASH("    ____________________________________________________________"),
    INFO_GREET("    Kon'nichiwa! Doraemon desu. What can I do for you?"),
    INFO_FILE("    Nobita, take note that all entries will be read from and written into %s."),
    INFO_LIST("    Here are the tasks stored in Doraemon's 4D pocket:"),
    INFO_TASK_ADDED("    Nobita, wake up. Here's your new task:"),
    INFO_TASK_MARKED("    Subarashi! Good job in completing your task, Nobita:"),
    INFO_TASK_UNMARKED("    Gambate Nobita, complete it soon! Don't procrastinate:"),
    INFO_TASK_COUNT("    Now you have %d tasks in Doraemon's 4D pocket."),
    INFO_TASK_DELETED("    Nobita, sugoi! One task has been deleted from Doraemon's 4D pocket."),
    INFO_BYE("    Sayonara. Hope to see you again soon!");

    public final String text;

    InfoText(String text) {
        this.text = text;
    }

    public String toString() {
        return text;
    }
}
