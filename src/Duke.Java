
public class Duke extends Constants{
    public static final String DEFAULT_STORAGE_FILEPATH = "./data.txt";
    private final Ui ui;
    private final TaskList taskList;
    private final Storage storage;
    private final Parser parse;
    public Duke() {
        ui = new Ui();
        parse = new Parser();
        storage = new Storage(DEFAULT_STORAGE_FILEPATH);
        taskList = new TaskList(storage.loadFileData());
    }
    public void run() {
        ui.greet();
        String input = ui.userCommand();
        while (!input.equals(BYE)) {
            String[] get = input.split(" ", 2);
            if (get.length == 1) {
                try {
                    switch (get[TASK]) {
                        case LIST:
                            taskList.list();
                            break;
                        case UNMARK:
                            throw new Error(UNMARK_ERROR);
                        case MARK:
                            throw new Error(MARK_ERROR);
                        case DEADLINE:
                            throw new Error(DELETE_ERROR);
                        default:
                            throw new Error(INVALID_COMMAND);
                    }
                }
                catch (Error error) {
                    System.out.println(error.getMessage());
                }
            }
            else {
                switch (get[TASK]) { //if input more than 1
                    case LIST:
                        ui.listMessage();
                        taskList.list();
                        break;
                    case UNMARK:
                        try {
                            int unmarkIndex = Integer.parseInt(get[NUMBER]);
                            taskList.unmark(unmarkIndex);
                            storage.writeToFile(taskList);
                        }
                        catch (IndexOutOfBoundsException error) {
                            System.out.println(OUT_OF_BOUNDS);
                        }
                        catch (NumberFormatException error) {
                            System.out.println(NOT_INTEGER);
                        }
                        break;

                    case MARK:
                        try {
                            int markIndex = Integer.parseInt(get[NUMBER]);
                            taskList.mark(markIndex);
                            storage.writeToFile(taskList);
                        }
                        catch (IndexOutOfBoundsException error) {
                            System.out.println(OUT_OF_BOUNDS);
                        }
                        catch (NumberFormatException error) {
                            System.out.println(NOT_INTEGER);
                        }
                        break;

                    case DELETE:
                        try {
                            int deleteIndex = Integer.parseInt(get[NUMBER]);
                            taskList.delete(deleteIndex);
                            storage.writeToFile(taskList);
                        }
                        catch (IndexOutOfBoundsException error) {
                            System.out.println(OUT_OF_BOUNDS);
                        }
                        catch (NumberFormatException error) {
                            System.out.println(NOT_INTEGER);
                        }
                        break;

                    case DEADLINE:
                    case EVENT:
                    case TODO:
                        try {
                            Task task = parse.task(get[TASK], get[DETAILS]);
                            taskList.addTask(task);
                            storage.appendToFile(task.fileFormat());
                        }
                        catch (Error error) {
                            System.out.println(error.getMessage());
                        }
                        break;
                    default:
                        System.out.println(INVALID_COMMAND);
                        break;
                }
            }
            input = ui.userCommand();
        }
        ui.exit();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
