package duke.common;

public enum InfoMessages {
    MESSAGE_INFO_DIVIDER("____________________________________________________________"),
    MESSAGE_INFO_WELCOME("Kon'nichiwa! Doraemon desu. What can I do for you?"),
    MESSAGE_INFO_LIST_EMPTY("Doraemon's 4D pocket is empty. Go take a rest, Nobita!"),
    MESSAGE_INFO_LIST("Here are the tasks stored in Doraemon's 4D pocket:"),
    MESSAGE_INFO_LIST_FILTERED("Here are the tasks that match your search expression in Doraemon's 4D pocket:"),
    MESSAGE_INFO_LIST_UNFILTERED("There are no tasks in Doraemon's 4D pocket that match your search expression."),
    MESSAGE_INFO_TASK_ADDED("Nobita, wake up. Here's your new task:"),
    MESSAGE_INFO_TASK_MARKED("Subarashi! Good job in completing your task, Nobita:"),
    MESSAGE_INFO_TASK_UNMARKED("Gambate Nobita, complete it soon! Don't procrastinate:"),
    MESSAGE_INFO_TASK_COUNT("Now you have %d tasks in Doraemon's 4D pocket."),
    MESSAGE_INFO_TASK_DELETED("Nobita, sugoi! One task has been deleted from Doraemon's 4D pocket."),
    MESSAGE_INFO_GOODBYE("Sayonara. Hope to see you again soon!");

    public final String message;

    InfoMessages(String message) {
        this.message = message;
    }

    public String toString() {
        return message;
    }
}
