package duke;

public class Duke {
    protected static TaskList dukeList = new TaskList();
    protected static Storage storage;
    public static final String markDone = "mark";
    public static final String delete = "delete";
    public static final String bye = "bye";
    public static final String find = "find";
    public static final String list = "list";
    public static final String space = " ";
    public static final String unmarkDone = "unmark";
    public static final String filename = "duke.txt";
    public static final String emptyString = "";

    public static void run() throws DukeException {
        try {
            storage = new Storage();
            Storage.openOrCreateFile();
            Storage.uploadDataToList();
        } catch (DukeException e){
            Message.printSystemError();
        }
        Message.printLogo();
        Message.printGreeting();
        Ui.input();
    }

    public static void main(String[] args)  {
        try {
            run();
        } catch (DukeException e) {
            Message.printUnknownError();
        }
    }
}
