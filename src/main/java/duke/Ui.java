package duke;

public class Ui {

    public void showLoadingError() {
        System.out.println("Error loading data from file");
    }

    public void showCreateParentFolderError() {
        System.out.println("Error creating parent folder(s)");
    }

    public void showCreateFileSuccess(String filepath) {
        System.out.printf("File created at %s\n", filepath);
    }

    public void showFileExistsError(String filepath) {
        System.out.printf("File already exists at %s\n", filepath);
    }

    public void showCreateFileError(String filepath) {
        System.out.printf("Error creating file: Could not create file at %s\n", filepath);
    }

    public void showUpdateFileError() {
        System.out.println("Error updating file");
    }

    public void showAddSuccess(String string) {
        System.out.printf("Got it. I've added this task:\n%s\n", string);
    }

    public void showDeleteSuccess(String string) {
        System.out.printf("Noted. I've removed this task:\n%s\n", string);
    }

    public void showSizeOfList(int size) {
        System.out.printf("Now you have %d tasks in the list.\n", size);
    }

    public void showIndexError(int size) {
        System.out.printf("Error: enter an integer between 1 - %d\n", size);
    }

    public void showEmptyListError() {
        System.out.println("Error: list is empty!");
    }

    public void showList() {
        System.out.println("Here are the tasks in your list:");
    }

    public void showListItem(int index, String string) {
        System.out.printf("%d. %s\n", index, string);
    }

    public void showTaskAlreadyDoneError(String string) {
        System.out.printf("This task is already done:\n%s\n", string);
    }

    public void showTaskAlreadyNotDoneError(String string) {
        System.out.printf("This task is already not done:\n%s\n", string);
    }

    public void showUnmarkSuccess(String string) {
        System.out.printf("OK, I've marked this task as not done yet:\n%s\n", string);
    }

    public void showMarkSuccess(String string) {
        System.out.printf("Nice! I've marked this task as done:\n%s\n", string);
    }

    public void showDeleteUsage() {
        System.out.println("Usage: delete <integer>");
    }

    public void showTodoUsage() {
        System.out.println("Usage: todo <task>");
    }

    public void showDeadlineUsage() {
        System.out.println("Usage: deadline <task> /by <date>");
    }

    public void showEventUsage() {
        System.out.println("Usage: event <task> /at <date>");
    }

    public void showMarkUsage() {
        System.out.println("Usage: mark <integer>");
    }

    public void showUnmarkUsage() {
        System.out.println("Usage: unmark <integer>");
    }

    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public void greet() {
        printLine();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        printLine();
    }

    public void showInvalidCommand() {
        System.out.println("Enter a valid command.");
        printLine();
    }

    public void showUnknownError() {
        System.out.println("Unknown error.");
        printLine();
    }



}