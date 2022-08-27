public class Duke {

    //Zhou Zhou


    public static void main(String[] args) {
        UI console = new UI();
        TaskManager taskManager = new TaskManager();
        String input = UI.input();
        while (!input.equals("bye")) {
            String[] words = input.split(" ");
            String arguments = input.substring(words[0].length());
            switch (words[0]) {
            case "help":
                UI.response(Command.HELP);
                break;
            case "please":
                UI.response(Command.PLEASE);
                break;
            case "list":
                UI.response(Command.LIST);
                break;
            case "mark":
                TaskManager.markAsDone(Integer.parseInt(arguments.trim()));
                UI.response(Command.MARK, TaskManager.Tasks[Integer.parseInt(arguments.trim())].listTask());
                break;
            case "unmark":
                TaskManager.markAsNotDone(Integer.parseInt(arguments.trim()));
                UI.response(Command.UNMARK, TaskManager.Tasks[Integer.parseInt(arguments.trim())].listTask());
                break;
            case "todo":
                TaskManager.addTask(Command.TODO, arguments.trim());
                UI.response(Command.ADD, TaskManager.Tasks[TaskManager.getTasksCount()].listTask());
                break;
            case "event":
                TaskManager.addTask(Command.EVENT, arguments.trim());
                UI.response(Command.ADD, TaskManager.Tasks[TaskManager.getTasksCount()].listTask());
                break;
            case "deadline":
                TaskManager.addTask(Command.DEADLINE, arguments.trim());
                UI.response(Command.ADD, TaskManager.Tasks[TaskManager.getTasksCount()].listTask());
                break;
            default:
                UI.response(Command.INVALID);
                break;
            }
            input = UI.input();
        }
        UI.response(Command.BYE);
    }
}
